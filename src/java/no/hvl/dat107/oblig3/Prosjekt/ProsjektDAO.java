package no.hvl.dat107.oblig3.Prosjekt;

import jakarta.persistence.*;
import no.hvl.dat107.oblig3.Ansatt.Ansatt;
import no.hvl.dat107.oblig3.Ansatt.AnsattDAO;
import no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse.Prosjektdeltakelse;
import no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse.ProsjektdeltakelseDAO;

import java.util.ArrayList;
import java.util.List;

public class ProsjektDAO implements ProsjektDAOInterface {
    private final EntityManagerFactory emf;
    public ProsjektDAO() {
        emf = Persistence.createEntityManagerFactory("oblig3PersistenceUnit");
    }

    @Override
    public void lagreProsjekt(Prosjekt a) {
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
    public Prosjekt finnProsjektMedId(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Prosjekt.class, id ); //Henter ut på primærnøkkel
        } catch (NoResultException e){
            return null;
        }  finally {
            em.close();
        }
    }

    @Override
    public Prosjekt finnProsjektMedNavn(String ProsjektNavn) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery(
                            "SELECT a from Prosjekt a WHERE a.prosjektnavn = :ProsjektNavn", Prosjekt.class).
                    setParameter("ProsjektNavn", ProsjektNavn).getSingleResult(); //Henter ut på primærnøkkel
        } catch (NoResultException e){
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Prosjekt> hentAlleProsjekter() {
        EntityManager em = emf.createEntityManager();
        String jpqlQuery = "SELECT a FROM Prosjekt as a order by a.id";

        try {
            TypedQuery<Prosjekt> query = em.createQuery(jpqlQuery, Prosjekt.class);
            return query.getResultList(); //Henter ut basert på spørring
        } finally {
            em.close();
        }
    }

    @Override
    public void oppdaterProsjekt(int id, String nyttProsjektNavn, String nySjef, String nyBeskrivelse) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Prosjekt a = em.find(Prosjekt.class, id); //Finne rad som skal oppdateres
            if(nyttProsjektNavn != null) {
                a.setProsjektnavn(nyttProsjektNavn);
            }
            if(nySjef != null) {
                a.setSjef(nySjef);
            }
            if (nyBeskrivelse != null) {
                a.setBeskrivelse(nyBeskrivelse);
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
    public List<Ansatt> getMedlemmer(Integer prosjektId) {
        ProsjektdeltakelseDAO pdDAO = new ProsjektdeltakelseDAO();
        AnsattDAO anDAO = new AnsattDAO();

        List<Prosjektdeltakelse> res = pdDAO.getDeltakereIProsjekt(prosjektId);

        List<Ansatt> result = new ArrayList<>();

        for (Prosjektdeltakelse prosjektdeltakelse : res) {
            result.add(anDAO.finnAnsattMedId(prosjektdeltakelse.getAnsattid()));
        }

        return result;
    }

    public void leggTilTotalTimer(Integer id, Integer timer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Prosjekt a = em.find(Prosjekt.class, id); //Finne rad som skal oppdateres
            if(timer != null) {
                a.setTimetall(timer);
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
    public String getProsjektInfo(Integer prosjektId) {
        return this.finnProsjektMedId(prosjektId).toString();
    }

    @Override
    public void slettProsjekt(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Prosjekt a = em.find(Prosjekt.class, id); //Finne rad som skal slettes
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
