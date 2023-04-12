package no.hvl.dat107.oblig3.Iterasjon1;

import no.hvl.dat107.oblig3.Iterasjon1.Ansatt.Ansatt;
import no.hvl.dat107.oblig3.Iterasjon1.Ansatt.AnsattDAO;
import no.hvl.dat107.oblig3.Iterasjon1.Ansatt.AnsattDAOInterface;
import no.hvl.dat107.oblig3.Iterasjon1.Ansatt.AnsattTekstgrensesnitt;

import java.util.List;
import java.util.Scanner;

import static no.hvl.dat107.oblig3.Iterasjon1.Ansatt.AnsattTekstgrensesnitt.skrivUt;

public class main {

	private static final AnsattDAOInterface DAO = new AnsattDAO();
	public static void main(String[] args) {
		
		skrivUt("Start.");


		Ansatt nyAnsatt = new Ansatt();
		int sokId = 0;
		String sokBrukernavn = null;

		Scanner input = new Scanner(System.in);

		String funksjonTxt = "Velg fuknsjon\n a) Søk ansatt med id\n b) Søk ansatt med brukernavn\n c) Liste med ansatte\n d) Oppdatere ansatt\n e) Legg til ny ansatt";

		System.out.println(funksjonTxt);
		System.out.println("Ditt valg: ");

		String valg = input.nextLine();

		switch (valg){
			case "a": // a) Søk ansatt med id
				AnsattTekstgrensesnitt.finnAnsatt(DAO);
				break;
			case "b": // b) Søk ansatt med brukernavn
				System.out.println("Funnet: " + AnsattTekstgrensesnitt.finnAnsatt(DAO));
				break;
			case "c": // c) Liste med ansatt
				System.out.println("Alle ansatte: " + AnsattTekstgrensesnitt.listAnsatte(DAO));
				break;
			case "d": // d) Oppdatere ansatt
				System.out.println("Oppdatert ansatt: " + AnsattTekstgrensesnitt.oppdaterAnsatt(DAO));
				break;
			case "e": // e) Legg til ny ansatt
				System.out.println("Ny ansatt: " + AnsattTekstgrensesnitt.LesInnNyAnsatt(DAO));
				break;
		}


	}

	public static void skrivUt(String tekst) {
		List<Ansatt> personer = DAO.hentAlleAnsatte();
		System.out.println("\n--- "+ tekst +" ---");
		personer.forEach(System.out::println);
	}
}
