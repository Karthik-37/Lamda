package com.onetomany;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class OneToMany {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			emf = Persistence.createEntityManagerFactory("corebanking");
			entityManager = emf.createEntityManager();
transaction = entityManager.getTransaction();


			transaction.begin();

			Customer customer = new Customer();
			//customer.setId(1);
			customer.setCname("Karthikeyan");
			customer.setContactno("1532789232");

			Account a1 = new Account();
			//a1.setId(1);
			a1.setAccount("CUB");
			a1.setStatus("Success");
			a1.setCustomer(customer);
			
			Account a2 = new Account();
			//a2.setId(2);
			a2.setAccount("HDFC");
			a2.setStatus("Success");
			a2.setCustomer(customer);

		  List<Account> list = new ArrayList<Account>();
			list.add(a1);
			list.add(a2);
             customer.setAccountList(list);
			
			
			entityManager.persist(customer);
			transaction.commit();
			entityManager.close();
			System.out.println("Customer details successfully added....");
		}


			catch (Exception e) {
				System.out.println(e);
				transaction.rollback();
			} finally {
				if (transaction.isActive()) {
					transaction.commit();
				}
				if (entityManager != null) {
					entityManager.close();
				}
				if (emf != null) {
					emf.close();
				}
			}
		}

}
