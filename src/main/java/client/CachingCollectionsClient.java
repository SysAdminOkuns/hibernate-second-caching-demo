package client;

import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import entity.Guide;
import entity.Student;

public class CachingCollectionsClient {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(
				"hello-world");

		Statistics stats = emf.unwrap(SessionFactory.class).getStatistics();
		stats.setStatisticsEnabled(true);

		EntityManager em1 = emf.createEntityManager();
		em1.getTransaction().begin();

		Guide guide1 = em1.find(Guide.class, 1L);
		int size1 = guide1.getStudents().size();

		em1.getTransaction().commit();
		em1.close();

		EntityManager em2 = emf.createEntityManager();
		em2.getTransaction().begin();

		Guide guide2 = em2.find(Guide.class, 1L);
		int size2 = guide2.getStudents().size();

		em2.getTransaction().commit();
		em2.close();

		System.out.println("Guide: " + stats.getSecondLevelCacheStatistics("entity.Guide"));
		System.out.println("Student: " + stats.getSecondLevelCacheStatistics("entity.Student"));
		System.out.println(guide2.getStudents());
		emf.close();

	}
}














