package no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProsjektdeltakelseDAO {
    private final EntityManagerFactory emf;
    public ProsjektdeltakelseDAO() {
        emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
    }
    public void oppdaterProsjektdeltakelseRolle(int ansattid, int prosjetkid, String nyRolle){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            Prosjektdeltakelse l = em.find(Prosjektdeltakelse.class, ansattid);
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
