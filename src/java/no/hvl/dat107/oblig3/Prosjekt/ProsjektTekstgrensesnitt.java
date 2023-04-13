package no.hvl.dat107.oblig3.Prosjekt;

import no.hvl.dat107.oblig3.Ansatt.Ansatt;
import no.hvl.dat107.oblig3.Ansatt.AnsattDAOInterface;
import no.hvl.dat107.oblig3.Avdeling.Avdeling;
import no.hvl.dat107.oblig3.Avdeling.AvdelingDAOInterface;
import no.hvl.dat107.oblig3.Teksgrensesnitt;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProsjektTekstgrensesnitt extends Teksgrensesnitt {

    public static Avdeling finnAvdelingMedNavn(no.hvl.dat107.oblig3.Avdeling.AvdelingDAOInterface DAO) {
        Scanner input = new Scanner(System.in);

        String sokNavn = safeRead(() -> {
            System.out.print("Skriv inn brukernavn:");
            return input.nextLine();
        }, "Ikke gyldig søkeverdi");

        return DAO.finnAvdelingMedNavn(sokNavn);
    }

    public static Avdeling finnAvdelingMedId(no.hvl.dat107.oblig3.Avdeling.AvdelingDAOInterface DAO) {
        Scanner input = new Scanner(System.in);

        int id = safeRead(() -> {
            System.out.print("Skriv inn id:");
            return Integer.parseInt(input.nextLine());
        }, "Ikke gyldig søkeverdi");

        return DAO.finnAvdelingMedId(id);
    }

    public static String hentAnsatteIAvdeling(no.hvl.dat107.oblig3.Avdeling.AvdelingDAOInterface DAO) {
        Scanner input = new Scanner(System.in);

        return safeRead(() -> {
            System.out.print("Avdeling id: ");
            int id = Integer.parseInt(input.nextLine());

            List<Ansatt> ansattList = DAO.getAnsatte(id);
            Avdeling avdeling = DAO.finnAvdelingMedId(id);

            ansattList.sort((a, b) -> a.getBrukernavn().equals(avdeling.getAvdelingSjef()) ? -1 : a.getId() - b.getId() + 1);


            String s = "\n" + ansattList.stream().map(Object::toString).collect(Collectors.joining("\nMedarbeider:\n"));
            return s;
        }, "Ikke gyldig avdeling");
    }


    public static Avdeling leggTilAvdeling(AvdelingDAOInterface DAO, AnsattDAOInterface anDAO){

        String avdeling;
        String sjefsBrukernavn;

        Scanner input = new Scanner(System.in);

        avdeling = safeRead(() -> {
            System.out.println("Skriv inn nytt avdelingsnavn:");
            String res = input.nextLine();
            if(DAO.finnAvdelingMedNavn(res) != null){
                throw new Exception("Avdelingsnavn er tatt");
            }
            return res;
        }, "Ikke gyldig navn");

        sjefsBrukernavn = safeRead(() -> {
            System.out.println("Skriv inn ny sjefbrukernavn:");
            String sjef = input.nextLine();
            Ansatt ansattInfo = anDAO.finnAnsattMedBrukernavn(sjef);
            if(ansattInfo == null){
                throw new Exception("Bruker finnes ikke");
            }
            if(anDAO.erSjef(ansattInfo.getId())){
                throw new Exception("Allerede sjef i annen avdeling");
            }
            return sjef;
        }, "Ikke gyldig navn");

        Avdeling nyAvdeling = new Avdeling(avdeling,sjefsBrukernavn);
        DAO.lagreProsjekt(nyAvdeling);
        return nyAvdeling;
    }
}
