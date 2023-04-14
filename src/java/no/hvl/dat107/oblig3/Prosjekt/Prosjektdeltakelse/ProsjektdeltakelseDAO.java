package no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import no.hvl.dat107.oblig3.Avdeling.Avdeling;
import no.hvl.dat107.oblig3.Prosjekt.ProsjektDAO;

public class ProsjektdeltakelseDAO {
    private final EntityManagerFactory emf;
    public ProsjektdeltakelseDAO() {
        emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
    }
    public Avdeling oppdaterProsjektdeltakelseRolle(int ansattid, int prosjektId, String nyRolle){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            Prosjektdeltakelse l = em.createQuery(
                            "SELECT a from Prosjektdeltakelse a WHERE a.prosjektId = :prosjektId AND a.ansattId = :ansattId", Prosjektdeltakelse.class).
                    setParameter("prosjektId", prosjektId).setParameter("ansattId", ansattid).getSingleResult();
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
    public void leggTilTimer(int ansattId, int antallTimer){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();;

        try{
            tx.begin();
            Prosjektdeltakelse l = em.find(Prosjektdeltakelse.class, ansattId);

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
