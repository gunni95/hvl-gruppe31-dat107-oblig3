package no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse;

import jakarta.persistence.*;
import no.hvl.dat107.oblig3.Prosjekt.ProsjektDAO;

import java.util.List;

public class ProsjektdeltakelseDAO implements ProsjektdeltakelseDAOInterface {
    private final EntityManagerFactory emf;
    public ProsjektdeltakelseDAO() {
        emf = Persistence.createEntityManagerFactory("oblig3PersistenceUnit");
    }

    @Override
    public void opprettProsjektdeltakelse(Prosjektdeltakelse p) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(p); //Oppretter en ny rad i databasen
            tx.commit();

        } catch (Throwable e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private Prosjektdeltakelse getProsjektdeltakelse(EntityManager em, Integer prosjektId, Integer ansattId) {
        try {
            return em.createQuery(
                            "SELECT a from Prosjektdeltakelse a WHERE a.prosjektid = :prosjektid AND a.ansattid = :ansattid", Prosjektdeltakelse.class).
                    setParameter("prosjektid", prosjektId).setParameter("ansattid", ansattId).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    @Override
    public Prosjektdeltakelse getProsjektdeltakelse(Integer prosjektId, Integer ansattId) {
        EntityManager em = emf.createEntityManager();

        Prosjektdeltakelse result = getProsjektdeltakelse(em, prosjektId, ansattId);

        em.close();

        return result;
    }

    @Override
    public List<Prosjektdeltakelse> getDeltakereIProsjekt(Integer prosjektId) {
        EntityManager em = emf.createEntityManager();

        List<Prosjektdeltakelse> prosjektdeltakelseList = em.createQuery(
                        "SELECT a from Prosjektdeltakelse a WHERE a.prosjektid = :prosjektid", Prosjektdeltakelse.class).
                setParameter("prosjektid", prosjektId).getResultList();

        em.close();

        return prosjektdeltakelseList;
    }

    @Override
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

    @Override
    public void leggTilTimer(Integer prosjektId, Integer ansattId, Integer antallTimer){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();;

        try{
            tx.begin();
            Prosjektdeltakelse l = getProsjektdeltakelse(em, prosjektId, ansattId);
            if (l == null) {
                throw new Exception("Ansatt er ikke registrert i prosjekt");
            }

            if (antallTimer != null) {
                l.setProsjekttimer(l.getProsjekttimer() + antallTimer);

                ProsjektDAO prDAO = new ProsjektDAO();
                prDAO.leggTilTotalTimer(l.getProsjektid(), antallTimer);
            }
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
