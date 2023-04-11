package no.hvl.dat107.oblig3.Iterasjon1.Avdeling;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class AvdelingDAO implements AvdelingDAOInterface {
    private EntityManagerFactory emf;
    public AnsattDAO() {
        emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
    }

    @Override
    public void lagreAvdeling(Avdeling a) {

    }

    @Override
    public Avdeling finnAvdelingMedId(int id) {
        return null;
    }

    @Override
    public Avdeling finnAvdelingMedNavn(String avdelingNavn) {
        return null;
    }

    @Override
    public List<Avdeling> hentAlleAvdelinger() {
        return null;
    }

    @Override
    public void oppdaterAvdeling(int id, String nyttAvdelingNavn, String nySjef) {

    }

    @Override
    public void slettAvdeling(int id) {

    }
}
