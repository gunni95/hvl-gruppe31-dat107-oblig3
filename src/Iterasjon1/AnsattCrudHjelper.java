package Iterasjon1;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AnsattCrudHjelper implements AnsattCrudInterface {

	private EntityManagerFactory emf;
	
	public AnsattCrudHjelper() {
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
	}
	
	@Override
	public void lagreAnsatt(Ansatt a) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(a); //Oppretter en ny rad i databasen
			tx.commit();
		
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
	
	@Override
	public Ansatt hentAnsatt(int id) {

		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Ansatt.class, id); //Henter ut på primærnøkkel
		} finally {
			em.close();
		}
	}
	
	@Override
	public List<Ansatt> hentAlleAnsatte() {

		EntityManager em = emf.createEntityManager();
		String jpqlQuery = "SELECT a FROM Ansatt as a order by a.id";

		try {
			TypedQuery<Ansatt> query = em.createQuery(jpqlQuery, Ansatt.class);
			return query.getResultList(); //Henter ut basert på spørring
		} finally {
			em.close();
		}
	}
	
	@Override
	public List<Ansatt> hentAlleAnsatteNQ() {

		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Ansatt> query = em.createNamedQuery("hentAllePersoner", Ansatt.class);
			return query.getResultList(); //Henter ut basert på spørring
		} finally {
			em.close();
		}
	}
	
	@Override
	public void oppdaterAnsatt(int id, String Brukernavn, String Fornavn, String Etternavn, String Stilling, int MaanedsLonn) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			Ansatt a = em.find(Ansatt.class, id); //Finne rad som skal oppdateres
			if(Brukernavn != null) {
				a.setBrukernavn(Brukernavn);
			}
			if(Fornavn != null) {
				a.setFornavn(Fornavn);
			}
			if(Etternavn != null) {
				a.setEtternavn(Etternavn);
			}
			if(Stilling != null) {
				a.setStilling(Stilling);
			}
			if(MaanedsLonn != 0) {
				a.setMaanedsLonn(MaanedsLonn);
			}
		
			tx.commit();
			
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

	@Override
	public void slettAnsatt(int id) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			Ansatt a = em.find(Ansatt.class, id); //Finne rad som skal slettes
			em.remove(a); //Slette rad som tilsvarer managed oject a
			
			tx.commit();
		
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
}
