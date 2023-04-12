package no.hvl.dat107.oblig3.Iterasjon1.Ansatt;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AnsattDAO implements AnsattDAOInterface {

	private EntityManagerFactory emf;
	
	public AnsattDAO() {
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
	public Ansatt finnAnsattMedId(int id) {

		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Ansatt.class, id); //Henter ut på primærnøkkel
		} finally {
			em.close();
		}
	}

	@Override
	public Ansatt finnAnsattMedBrukernavn(String brukernavn) {

		EntityManager em = emf.createEntityManager();

		try {
			return em.createQuery(
							"SELECT a from Ansatt a WHERE a.Brukernavn = :brukernavn", Ansatt.class).
					setParameter("brukernavn", brukernavn).getSingleResult(); //Henter ut på primærnøkkel
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
	public void oppdaterStilling(int id, String nyStilling){

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try{
			tx.begin();

			Ansatt a = em.find(Ansatt.class, id); //Finne rad som skal oppdateres
			if(nyStilling != null && nyStilling != "0") {
				a.setStilling(nyStilling);
			}
			tx.commit();
		} catch (Throwable e){
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

	@Override
	public void oppdaterLonn(int id, int nyLonn){

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try{
			tx.begin();

			Ansatt a = em.find(Ansatt.class, id); //Finne rad som skal oppdateres
			if(nyLonn != 0) {
				a.setMaanedsLonn(nyLonn);
			}
			tx.commit();
		} catch (Throwable e){
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
