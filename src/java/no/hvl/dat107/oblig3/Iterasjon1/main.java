package no.hvl.dat107.oblig3.Iterasjon1;

import java.util.List;
import java.util.Scanner;

public class main {
	
	private static final AnsattDAOInterface DAO = new AnsattDAO();
	
	public static void main(String[] args) {
		
		skrivUt("Start.");
		
		DAO.lagreAnsatt(new Ansatt("Jan", "Jan", "Banan", "2022-11-11", "Bilmekaniker", 44000));
		skrivUt("Lagt til Jan.");
		
		DAO.oppdaterAnsatt(0004, null, null, "Heisekran", null, 0);
		skrivUt("Endret Etternavn.");
		
		DAO.slettAnsatt(0001);
		skrivUt("Slettet ansatt 0001.");
		
		DAO.lagreAnsatt(new Ansatt("Don", "Don", "Duck", "2022-01-01", "Kokk", 40000));
		DAO.slettAnsatt(0004);
		skrivUt("Tilbakestillt database.");


		Ansatt nyAnsatt = new Ansatt();
		int sokId = 0;
		String sokBrukernavn = null;

		Scanner input = new Scanner(System.in);

		String funksjonTxt = "Velg fuknsjon\n a) Søk ansatt med id\n b) Søk ansatt med brukernavn\n c) Liste med ansatte\n d) Oppdatere ansatt\n e) Legg til ny ansatt";

		System.out.println(funksjonTxt);
		System.out.println("Ditt valg: ");

		String valg = input.nextLine();

		switch (valg){
			case "a":
				System.out.print("Skriv inn id:");
				sokId = input.nextInt();
				System.out.println(DAO.finnAnsattMedId(sokId).toString());

				break;
			case "b":
				System.out.print("Skriv inn brukernavn:");
				sokBrukernavn = input.nextLine();
				System.out.println(DAO.finnAnsattMedBrukernavn(sokBrukernavn).toString());

				break;
			case "c":
				DAO.hentAlleAnsatte();

				break;
			case "d":


				break;
			case "e":


				break;
		}


	}
	
	private static void skrivUt(String tekst) {
		List<Ansatt> personer = DAO.hentAlleAnsatte();
		System.out.println("\n--- "+ tekst +" ---");
		personer.forEach(System.out::println);		
	}
}
