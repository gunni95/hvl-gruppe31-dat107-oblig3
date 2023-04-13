package no.hvl.dat107.oblig3.Avdeling;

import jakarta.persistence.*;
import no.hvl.dat107.oblig3.Ansatt.Ansatt;

import java.util.List;

public class AvdelingDAO implements AvdelingDAOInterface {
    private final EntityManagerFactory emf;
    public AvdelingDAO() {
        emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
    }

    @Override
    public void lagreProsjekt(Avdeling a) {
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
        } catch (NoResultException e){
            return null;
        }  finally {
            em.close();
        }
    }

    @Override
    public Avdeling finnAvdelingMedNavn(String avdelingNavn) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery(
                            "SELECT a from Prosjekt a WHERE a.navn = :avdelingNavn", Avdeling.class).
                    setParameter("avdelingNavn", avdelingNavn).getSingleResult(); //Henter ut på primærnøkkel
        } catch (NoResultException e){
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Avdeling> hentAlleAvdelinger() {
        EntityManager em = emf.createEntityManager();
        String jpqlQuery = "SELECT a FROM Prosjekt as a order by a.id";

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
    public List<Ansatt> getAnsatte(Integer id) {
        EntityManager em = emf.createEntityManager();
        String jpqlQuery = "SELECT a FROM Ansatt as a WHERE a.avdeling = :id";

        try {
            TypedQuery<Ansatt> query = em.createQuery(jpqlQuery, Ansatt.class).setParameter("id", id);
            return query.getResultList(); //Henter ut basert på spørring
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
