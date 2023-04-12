package no.hvl.dat107.oblig3;

import no.hvl.dat107.oblig3.Ansatt.AnsattDAO;
import no.hvl.dat107.oblig3.Ansatt.AnsattDAOInterface;
import no.hvl.dat107.oblig3.Ansatt.AnsattTekstgrensesnitt;

import java.util.Scanner;

public class Klient {

	private static final AnsattDAOInterface DAO = new AnsattDAO();
	public static void main(String[] args) {
		
		AnsattTekstgrensesnitt.skrivUt(DAO, "Start.");

		Scanner input = new Scanner(System.in);

		String funksjonTxt = "Velg fuknsjon\n a) Søk ansatt med id\n b) Søk ansatt med brukernavn\n c) Liste med ansatte\n d) Oppdatere ansatt\n e) Legg til ny ansatt";

		System.out.println(funksjonTxt);
		System.out.print("Ditt valg: ");

		String valg = input.nextLine();

		switch (valg){
			case "a": // a) Søk ansatt med id
				System.out.println("Funnet: " + AnsattTekstgrensesnitt.finnAnsattMedId(DAO));
				break;
			case "b": // b) Søk ansatt med brukernavn
				System.out.println("Funnet: " + AnsattTekstgrensesnitt.finnAnsattMedBrukernavn(DAO));
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
}
