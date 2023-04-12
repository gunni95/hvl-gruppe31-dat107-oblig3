package no.hvl.dat107.oblig3.Iterasjon1.Avdeling;

import jakarta.persistence.*;
import no.hvl.dat107.oblig3.Iterasjon1.Ansatt.Ansatt;

import java.util.List;

public class AvdelingDAO implements AvdelingDAOInterface {
    private EntityManagerFactory emf;
    public AnsattDAO() {
        emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
    }

    @Override
    public void lagreAvdeling(Avdeling a) {
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
    public Avdeling finnAvdelingMedId(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Avdeling.class, id ); //Henter ut på primærnøkkel
        } finally {
            em.close();
        }
    }

    @Override
    public Avdeling finnAvdelingMedNavn(String avdelingNavn) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Avdeling.class, avdelingNavn); //Henter ut på primærnøkkel
        } finally {
            em.close();
        }
    }

    @Override
    public List<Avdeling> hentAlleAvdelinger() {
        EntityManager em = emf.createEntityManager();
        String jpqlQuery = "SELECT a FROM Avdeling as a order by a.avdelingId";

        try {
            TypedQuery<Avdeling> query = em.createQuery(jpqlQuery, Avdeling.class);
            return query.getResultList(); //Henter ut basert på spørring
        } finally {
            em.close();
        }
    }

    @Override
    public void oppdaterAvdeling(int id, String nyttAvdelingNavn, String nySjef) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Avdeling a = em.find(Avdeling.class, id); //Finne rad som skal oppdateres
            if(nyttAvdelingNavn != null) {
                a.setAvdelingNavn(nyttAvdelingNavn);
            }
            if(nySjef != null) {
                a.setAvdelingSjef(nySjef);
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
    public void slettAvdeling(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Avdeling a = em.find(Avdeling.class, id); //Finne rad som skal slettes
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
