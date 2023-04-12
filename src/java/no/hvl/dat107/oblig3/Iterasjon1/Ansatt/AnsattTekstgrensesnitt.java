package no.hvl.dat107.oblig3.Iterasjon1.Ansatt;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class AnsattTekstgrensesnitt {
    private static <T> T safeRead(Callable<T> operation, String errorMessage) {
        boolean valid = false;
        T response = null;
        while (!valid) {
            try {
                response = (T) operation;
                valid = true;
            } catch (Exception e) {
                System.out.println(errorMessage + ", " + e.getMessage());
            }
        }

        return response;
    }

    public static Ansatt LesInnNyAnsatt(AnsattDAOInterface DAO) {
        String brukernavn;
        String fornavn;
        String etternavn;
        String ansettelsesdato;
        String stilling;
        Integer maanedslonn;

        Scanner input = new Scanner(System.in);
        brukernavn = safeRead(() -> {
            System.out.println("Brukernavn: ");
            String res = input.nextLine();
            if (DAO.finnAnsattMedBrukernavn(res) != null) {
                throw new Exception("Brukernavn er allerede tatt");
            }
            return res;
        }, "Ikke gyldig brukernavn");
        fornavn = safeRead(() -> {
            System.out.println("Fornavn: ");
            return input.nextLine();
        }, "Ikke gyldig fornavn");
        etternavn = safeRead(() -> {
            System.out.println("Etternavn: ");
            return input.nextLine();
        }, "Ikke gyldig etternavn");
        ansettelsesdato = safeRead(() -> {
            System.out.println("dato (dd.mm.yyyy): ");
            String res = input.nextLine();
            String[] dateSections = res.split("-|.| ");
            int day = Integer.parseInt(dateSections[0]);
            int month = Integer.parseInt(dateSections[1]);
            if (day < 31 || (month >= 1 && month <= 12) || dateSections[2].length() > 0) {
                throw new Exception("Ikke gyldig datoformat");
            }
            String formatedDay = String.format("%2d", dateSections[0]);
            String formatedMonth = String.format("%2d", dateSections[1]);
            String formatedYear = String.format("%4d", dateSections[2]);

            return formatedDay + "." + formatedMonth + "." + formatedYear;
        }, "Ikke gyldig dato");
        stilling = safeRead(() -> {
            System.out.println("Stilling: ");
            return input.nextLine();
        }, "Ikke gyldig stilling");
        maanedslonn = safeRead(() -> {
            System.out.println("Månedslønn: ");
            return Integer.parseInt(input.nextLine());
        }, "Ikke gyldig lønn");

        input.close();
        return new Ansatt(brukernavn, fornavn, etternavn, ansettelsesdato, stilling, maanedslonn);
    }

    public static Ansatt oppdaterAnsatt(AnsattDAOInterface DAO) {
        Scanner input = new Scanner(System.in);
        int sokId = safeRead(() -> {
            System.out.println("Skriv id på ansatt du vil oppdatere:");
            return Integer.parseInt(input.nextLine());
        }, "Ikke gyldig Ansatt id");
        String nyStilling = safeRead(() -> {
            System.out.println("Skriv inn ny stilling:");
            return input.nextLine();
        }, "Ikke gyldig stilling");
        int nyLonn = safeRead(() -> {
            System.out.println("Skriv inn ny lønn:");
            return Integer.parseInt(input.nextLine());
        }, "Ikke gyldig lønn");
        input.close();

        DAO.oppdaterStilling(sokId, nyStilling);
        DAO.oppdaterLonn(sokId, nyLonn);
        return DAO.finnAnsattMedId(sokId);
    }

    public static Ansatt finnAnsatt(AnsattDAOInterface DAO) {
        Scanner input = new Scanner(System.in);

        String sokBrukernavn = safeRead(() -> {
            System.out.print("Skriv inn brukernavn:");
            return input.nextLine();
        }, "Ikke gyldig søkeverdi");
        input.close();

        return DAO.finnAnsattMedBrukernavn(sokBrukernavn);
    }

    public static List<Ansatt> listAnsatte(AnsattDAOInterface DAO) {
        return DAO.hentAlleAnsatte();
    }

    public static void skrivUt(AnsattDAOInterface DAO, String tekst) {
        List<Ansatt> personer = DAO.hentAlleAnsatte();
        System.out.println("\n--- "+ tekst +" ---");
        personer.forEach(System.out::println);
    }
}
