package no.hvl.dat107.oblig3.Iterasjon1;

import java.util.List;

public class main {
	
	private static final AnsattDAOInterface ansattDAO = new AnsattDAO();
	
	public static void main(String[] args) {
		
		skrivUt("Start.");
		
		ansattDAO.lagreAnsatt(new Ansatt("Jan", "Jan", "Banan", "2022-11-11", "Bilmekaniker", 44000));
		skrivUt("Lagt til Jan.");
		
		ansattDAO.oppdaterAnsatt(0004, null, null, "Heisekran", null, 0);
		skrivUt("Endret Etternavn.");
		
		ansattDAO.slettAnsatt(0001);
		skrivUt("Slettet ansatt 0001.");
		
		ansattDAO.lagreAnsatt(new Ansatt("Don", "Don", "Duck", "2022-01-01", "Kokk", 40000));
		ansattDAO.slettAnsatt(0004);
		skrivUt("Tilbakestillt database.");
	}
	
	private static void skrivUt(String tekst) {
		List<Ansatt> personer = ansattDAO.hentAlleAnsatte();
		System.out.println("\n--- "+ tekst +" ---");
		personer.forEach(System.out::println);		
	}
}
