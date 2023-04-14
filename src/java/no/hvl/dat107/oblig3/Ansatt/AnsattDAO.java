package no.hvl.dat107.oblig3.Ansatt;

import java.util.List;

import jakarta.persistence.*;
import no.hvl.dat107.oblig3.Avdeling.AvdelingDAO;
import no.hvl.dat107.oblig3.Avdeling.AvdelingDAOInterface;

public class AnsattDAO implements AnsattDAOInterface {

	private final EntityManagerFactory emf;
	
	public AnsattDAO() {
		this.emf = Persistence.createEntityManagerFactory("oblig3PersistenceUnit");
	}
	
	@Override
	public void opprettAnsatt(Ansatt a) {
		
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
							"SELECT a from Ansatt a WHERE a.brukernavn = :brukernavn", Ansatt.class).
					setParameter("brukernavn", brukernavn).getSingleResult(); //Henter ut på primærnøkkel
		} catch (NoResultException e) {
			return null;
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
	public void oppdaterAnsatt(int id, String brukernavn, String fornavn, String etternavn, Integer avdeling, String stilling, Integer maanedsLonn) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			Ansatt a = em.find(Ansatt.class, id); //Finne rad som skal oppdateres
			if(brukernavn != null) {
				a.setBrukernavn(brukernavn);
			}
			if(fornavn != null) {
				a.setFornavn(fornavn);
			}
			if(etternavn != null) {
				a.setEtternavn(etternavn);
			}
			if (avdeling != null) {
				a.setAvdeling(avdeling);
			}
			if(stilling != null) {
				a.setStilling(stilling);
			}
			if(maanedsLonn != 0) {
				a.setMaanedsLonn(maanedsLonn);
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
			a.setStilling(nyStilling);
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
			a.setMaanedsLonn(nyLonn);
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

	@Override
	public boolean erSjef(Integer ansattId) {
		Ansatt ansatt = finnAnsattMedId(ansattId);
		AvdelingDAOInterface avDAO = new AvdelingDAO();

		return avDAO.finnAvdelingMedId(ansatt.getAvdeling()).getAvdelingSjef().equals(ansatt.getBrukernavn());
	}

	@Override
	public void oppdaterAvdeling(int ansattId, int avdelingId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try{
			tx.begin();

			Ansatt a = em.find(Ansatt.class, ansattId); //Finne rad som skal oppdateres
			a.setAvdeling(avdelingId);
			tx.commit();
		} catch (Throwable e){
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
}
