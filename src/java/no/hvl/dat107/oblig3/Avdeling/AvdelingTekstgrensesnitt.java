package no.hvl.dat107.oblig3.Avdeling;

import no.hvl.dat107.oblig3.Ansatt.Ansatt;
import no.hvl.dat107.oblig3.Ansatt.AnsattDAOInterface;
import no.hvl.dat107.oblig3.Teksgrensesnitt;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AvdelingTekstgrensesnitt extends Teksgrensesnitt {

    public static void avdelingGrensesnitt(AvdelingDAOInterface avDAO, AnsattDAOInterface anDAO) {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            String promptTekst = "Velg operasjon:" +
                    " a) Finn avdeling\n " +
                    " b) Hent ansatt i avdeling\n " +
                    " c) Legg til ny avdeling\n " +
                    " 0) Tilbake";

            System.out.println(promptTekst);

            String input = scanner.nextLine();

            switch (input) {
                case "0": // 0) Tilbake
                    done = true;
                    break;
                case "a": // a) Finn avdeling
                    System.out.println("Avdeling: " + AvdelingTekstgrensesnitt.finnAvdelingMedId(avDAO));
                    break;
                case "b": // b) Hent ansatt i avdeling
                    System.out.println("\nAvdeling består av: \n\nSjef:" + AvdelingTekstgrensesnitt.hentAnsatteIAvdeling(avDAO));
                    break;
                case "c": // c) Legg til ny avdeling
                    AvdelingTekstgrensesnitt.leggTilAvdeling(avDAO,anDAO);
                    System.out.println("Ny avdeling lagt til.");
                    break;
            }
        }
    }

    public static Avdeling finnAvdelingMedNavn(AvdelingDAOInterface DAO) {
        Scanner input = new Scanner(System.in);

        String sokNavn = safeRead(() -> {
            System.out.print("Skriv inn brukernavn:");
            return input.nextLine();
        }, "Ikke gyldig søkeverdi");

        return DAO.finnAvdelingMedNavn(sokNavn);
    }

    public static Avdeling finnAvdelingMedId(AvdelingDAOInterface DAO) {
        Scanner input = new Scanner(System.in);

        int id = safeRead(() -> {
            System.out.print("Skriv inn id:");
            return Integer.parseInt(input.nextLine());
        }, "Ikke gyldig søkeverdi");

        return DAO.finnAvdelingMedId(id);
    }

    public static String hentAnsatteIAvdeling(AvdelingDAOInterface DAO) {
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


    public static String leggTilAvdeling(AvdelingDAOInterface DAO, AnsattDAOInterface anDAO){

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

        String s = nyAvdeling.toString();
        return s;
    }
}
