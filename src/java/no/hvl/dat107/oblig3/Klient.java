package no.hvl.dat107.oblig3;

import no.hvl.dat107.oblig3.Ansatt.AnsattDAO;
import no.hvl.dat107.oblig3.Ansatt.AnsattDAOInterface;
import no.hvl.dat107.oblig3.Ansatt.AnsattTekstgrensesnitt;
import no.hvl.dat107.oblig3.Avdeling.AvdelingDAO;
import no.hvl.dat107.oblig3.Avdeling.AvdelingTekstgrensesnitt;

import java.util.Scanner;

public class Klient {

	private static final AnsattDAOInterface anDAO = new AnsattDAO();
	private static final AvdelingDAO avDAO = new AvdelingDAO();
	public static void main(String[] args) {
		boolean done = false;
		Scanner input = new Scanner(System.in);

		AnsattTekstgrensesnitt.skrivUt(anDAO, "Start.");

		while (!done) {
			String funksjonTxt = "Velg fuknsjon\n " +
					"a) Søk ansatt med id\n " +
					"b) Søk ansatt med brukernavn\n " +
					"c) Liste med ansatte\n " +
					"d) Oppdatere ansatt\n " +
					"e) Legg til ny ansatt\n " +
					"f) Finn avdeling\n " +
					"g) Hent ansatt i avdeling\n " +
					"h) Legg til ny avdeling\n " +
					"0) Avslutt";

			System.out.println(funksjonTxt);
			System.out.print("Ditt valg: ");

			String valg = input.nextLine();

			switch (valg){
				case "0": // 0) Avslutt
					done = true;
					break;
				case "a": // a) Søk ansatt med id
					System.out.println("Funnet: " + AnsattTekstgrensesnitt.finnAnsattMedId(anDAO));
					break;
				case "b": // b) Søk ansatt med brukernavn
					System.out.println("Funnet: " + AnsattTekstgrensesnitt.finnAnsattMedBrukernavn(anDAO));
					break;
				case "c": // c) Liste med ansatt
					System.out.println("Alle ansatte: " + AnsattTekstgrensesnitt.listAnsatte(anDAO));
					break;
				case "d": // d) Oppdatere ansatt
					System.out.println("Oppdatert ansatt: " + AnsattTekstgrensesnitt.oppdaterAnsatt(avDAO, anDAO));
					break;
				case "e": // e) Legg til ny ansatt
					System.out.println("Ny ansatt: " + AnsattTekstgrensesnitt.LesInnNyAnsatt(avDAO, anDAO));
					break;
				case "f": // f) Finn avdeling
					System.out.println("Avdeling: " + AvdelingTekstgrensesnitt.finnAvdelingMedId(avDAO));
					break;
				case "g": // g) Hent ansatt i avdeling
					System.out.println("\nAvdeling består av: \n\nSjef:" + AvdelingTekstgrensesnitt.hentAnsatteIAvdeling(avDAO));
					break;
				case "h": // g) Legg til ny avdeling
					System.out.println("Ny avdeling lagt til: \n" + AvdelingTekstgrensesnitt.leggTilAvdeling(avDAO,anDAO));
					break;
			}
		}
		input.close();
	}
}
