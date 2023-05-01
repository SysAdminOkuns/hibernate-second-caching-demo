package client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Guide;

public class SecondLevelCachingClient {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(
				"hello-world");
		
		EntityManager em1 = emf.createEntityManager();
		em1.getTransaction().begin();

		//Is Guide[id=2] available in second-level cache?
		System.out.println(emf.getCache().contains(Guide.class, 2L));
		Guide guide1 = em1.find(Guide.class, 2L);

		em1.getTransaction().commit();
		em1.close(); 
		
		EntityManager em2 = emf.createEntityManager();
		em2.getTransaction().begin();

		//Is Guide[id=2] available in second-level cache?
		System.out.println(emf.getCache().contains(Guide.class, 2L));
		Guide guide2 = em2.find(Guide.class, 2L);
		
		em2.getTransaction().commit();
		em2.close();
		emf.close();

	}
}














