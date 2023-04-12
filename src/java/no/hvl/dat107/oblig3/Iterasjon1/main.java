package no.hvl.dat107.oblig3.Iterasjon1;

import no.hvl.dat107.oblig3.Iterasjon1.Ansatt.Ansatt;
import no.hvl.dat107.oblig3.Iterasjon1.Ansatt.AnsattDAO;
import no.hvl.dat107.oblig3.Iterasjon1.Ansatt.AnsattDAOInterface;

import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

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
				System.out.print("Skriv inn id:");
				sokId = input.nextInt();
				System.out.println(DAO.finnAnsattMedId(sokId).toString());

				break;
			case "b": // b) Søk ansatt med brukernavn
				System.out.print("Skriv inn brukernavn:");
				sokBrukernavn = input.nextLine();
				System.out.println(DAO.finnAnsattMedBrukernavn(sokBrukernavn).toString());

				break;
			case "c": // c) Liste med ansatt
				skrivUt("Alle ansatte:");

				break;
			case "d": // d) Oppdatere ansatt
				System.out.println("Skriv id på ansatt du vil oppdatere:");
				String idd = input.nextLine();
				int iddd = parseInt(idd);
				System.out.println("Skriv inn ny stilling og/eller ny lønn");
				System.out.println("Skriv inn ny stilling:");
				String nyStilling = input.nextLine();
				System.out.println("Skriv inn ny lønn:");
				String nyLonn = input.nextLine();
				int nylLonn = parseInt(nyLonn);
				DAO.oppdaterStilling(iddd, nyStilling);
				DAO.oppdaterLonn(iddd, nylLonn);
				skrivUt("Oppdatert ansattliste");
				break;
			case "e": // e) Legg til ny ansatt
				System.out.println("Skriv inn unikt brukernavn:");
				String eBrukernavn = input.nextLine();
				System.out.println("Skriv inn fornavn:");
				String eFornavn = input.nextLine();
				System.out.println("Skriv inn etternavn:");
				String eEtternavn = input.nextLine();
				System.out.println("Skriv inn Dato:");
				String eDato = input.nextLine();
				System.out.println("Skriv inn stilling");
				String eStilling = input.nextLine();
				System.out.println("Skriv inn lønn");
				String eLonn = input.nextLine();
				int eLoon = parseInt(eLonn);
				DAO.lagreAnsatt(new Ansatt(eBrukernavn, eFornavn, eEtternavn, eDato, eStilling, eLoon));
				skrivUt("Oppdatert ansattliste");
				break;
		}


	}
	
	private static void skrivUt(String tekst) {
		List<Ansatt> personer = DAO.hentAlleAnsatte();
		System.out.println("\n--- "+ tekst +" ---");
		personer.forEach(System.out::println);		
	}
}
