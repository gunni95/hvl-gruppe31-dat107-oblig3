package no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import no.hvl.dat107.oblig3.Prosjekt.ProsjektDAO;

public class ProsjektdeltakelseDAO {
    private final EntityManagerFactory emf;
    public ProsjektdeltakelseDAO() {
        emf = Persistence.createEntityManagerFactory("oblig3PersistenceUnit");
    }

    private Prosjektdeltakelse getProsjektdeltakelse(EntityManager em, Integer prosjektId, Integer ansattId) {
        return em.createQuery(
                        "SELECT a from Prosjektdeltakelse a WHERE a.prosjektId = :prosjektId AND a.ansattId = :ansattId", Prosjektdeltakelse.class).
                setParameter("prosjektId", prosjektId).setParameter("ansattId", ansattId).getSingleResult();
    }
    public Prosjektdeltakelse getProsjektdeltakelse(Integer prosjektId, Integer ansattId) {
        EntityManager em = emf.createEntityManager();

        return getProsjektdeltakelse(em, prosjektId, ansattId);
    }

    public Prosjektdeltakelse oppdaterProsjektdeltakelseRolle(Integer prosjektId, Integer ansattId, String nyRolle){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            Prosjektdeltakelse l = getProsjektdeltakelse(em, prosjektId, ansattId);
            l.setRolle(nyRolle);
            //unfin
            tx.commit();
        } catch(Throwable e){
            e.printStackTrace();
            tx.rollback();
        } finally{
            em.close();
        }
        return null;
    }
    public void leggTilTimer(Integer prosjektId, Integer ansattId, Integer antallTimer){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();;

        try{
            tx.begin();
            Prosjektdeltakelse l = getProsjektdeltakelse(em, prosjektId, ansattId);
            l.setProsjekttimer(antallTimer);

            ProsjektDAO prDAO = new ProsjektDAO();
            prDAO.leggTilTotalTimer(l.getProsjektId(), antallTimer);
            //unfin
            tx.commit();
        } catch(Throwable e){
            e.printStackTrace();
            tx.rollback();
        } finally{
            em.close();
        }
    }

}
