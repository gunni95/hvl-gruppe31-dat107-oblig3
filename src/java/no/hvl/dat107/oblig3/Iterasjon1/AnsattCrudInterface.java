package no.hvl.dat107.oblig3.Iterasjon1;

import java.util.List;

public interface AnsattCrudInterface {
	
	/** Create - Opprette ny ansatt-rad i databasen */
	void lagreAnsatt(Ansatt a);	
	
	/** Read1 - Hente ut data fra databasen. En enkelt. */
	Ansatt hentAnsatt(int id);
	
	/** Read2 - Hente ut data fra databasen. Alle. */
	List<Ansatt> hentAlleAnsatte();
	
	/** Read3 - Hente ut data fra databasen. Named Query. */
	List<Ansatt> hentAlleAnsatteNQ();
	
	/** Update - Oppdatere en ansatt-rad i databasen */
	void oppdaterAnsatt(int id, String nyttBrukernavn, String nyttFornavn, String nyttEtternavn, String nyStilling, int nyMaanedsLonn);
	
	/** Delete - Slette en ansatt-rad fra databasen */
	void slettAnsatt(int id);
}
