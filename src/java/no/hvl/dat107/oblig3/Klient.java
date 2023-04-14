package no.hvl.dat107.oblig3;

import no.hvl.dat107.oblig3.Ansatt.AnsattDAO;
import no.hvl.dat107.oblig3.Ansatt.AnsattDAOInterface;
import no.hvl.dat107.oblig3.Ansatt.AnsattTekstgrensesnitt;
import no.hvl.dat107.oblig3.Avdeling.AvdelingDAO;
import no.hvl.dat107.oblig3.Avdeling.AvdelingTekstgrensesnitt;
import no.hvl.dat107.oblig3.Prosjekt.ProsjektDAO;
import no.hvl.dat107.oblig3.Prosjekt.ProsjektTekstgrensesnitt;
import no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse.ProsjektdeltakelseDAO;

import java.util.Scanner;

public class Klient {

	private static final AnsattDAOInterface anDAO = new AnsattDAO();
	private static final AvdelingDAO avDAO = new AvdelingDAO();
	private static final ProsjektDAO prDAO = new ProsjektDAO();
	private static final ProsjektdeltakelseDAO pdDAO = new ProsjektdeltakelseDAO();

	public static void main(String[] args) {
		boolean done = false;
		Scanner input = new Scanner(System.in);

		AnsattTekstgrensesnitt.skrivUt(anDAO, "Start.");

		while (!done) {
			String funksjonTxt = "Velg Kategori\n " +
					"a) Ansatte\n " +
					"b) Avdelinger\n " +
					"c) Prosjekter\n " +
					"0) Avslutt";

			System.out.println(funksjonTxt);
			System.out.print("Ditt valg: ");

			String valg = input.nextLine();

			switch (valg){
				case "0": // 0) Avslutt
					done = true;
					break;
				case "a":
					AnsattTekstgrensesnitt.ansattGrensesnitt(avDAO, anDAO);
					break;
				case "b":
					AvdelingTekstgrensesnitt.avdelingGrensesnitt(avDAO, anDAO);
					break;
				case "c":
					ProsjektTekstgrensesnitt.prosjektGrensesnitt(avDAO, anDAO, prDAO, pdDAO);
					break;
			}
		}
		input.close();
	}
}
