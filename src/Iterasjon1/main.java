package Iterasjon1;

import java.util.List;

public class main {
	
	private static AnsattCrudInterface crudHjelper = new AnsattCrudHjelper();
	
	public static void main(String[] args) {
		
		skrivUt("Start.");
		
		crudHjelper.lagreAnsatt(new Ansatt(0004, "Jan", "Jan", "Banan", "2022-11-11", "Bilmekaniker", 44000));
		skrivUt("Lagt til Jan.");
		
		crudHjelper.oppdaterAnsatt(0004, null, null, "Heisekran", null, 0);
		skrivUt("Endret Etternavn.");
		
		crudHjelper.slettAnsatt(0001);
		skrivUt("Slettet ansatt 0001.");
		
		crudHjelper.lagreAnsatt(new Ansatt(0001, "Don", "Don", "Duck", "2022-01-01", "Kokk", 40000));
		crudHjelper.slettAnsatt(0004);
		skrivUt("Tilbakestillt database.");
	}
	
	private static void skrivUt(String tekst) {
		List<Ansatt> personer = crudHjelper.hentAlleAnsatte();
		System.out.println("\n--- "+ tekst +" ---");
		personer.forEach(System.out::println);		
	}
}
