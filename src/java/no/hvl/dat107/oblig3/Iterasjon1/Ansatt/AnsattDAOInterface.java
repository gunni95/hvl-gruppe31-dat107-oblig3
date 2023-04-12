package no.hvl.dat107.oblig3.Iterasjon1.Ansatt;

import java.util.List;

public interface AnsattDAOInterface {
	
	/** Create - Opprette ny ansatt-rad i databasen */
	void lagreAnsatt(Ansatt a);	
	
	/** Read1 - Hente ut data fra databasen. En enkelt. */
	Ansatt finnAnsattMedId(int id);

	/** Read1.5 - - Hente ut data fra databasen. En enkel. */
	Ansatt finnAnsattMedBrukernavn(String brukernavn);

	/** Read2 - Hente ut data fra databasen. Alle. */
	List<Ansatt> hentAlleAnsatte();

	Integer getLastId();
	
	/** Read3 - Hente ut data fra databasen. Named Query. */
	List<Ansatt> hentAlleAnsatteNQ();
	
	/** Update - Oppdatere en ansatt-rad i databasen */
	void oppdaterAnsatt(int id, String nyttBrukernavn, String nyttFornavn, String nyttEtternavn, String nyStilling, int nyMaanedsLonn);

	/** Update - Oppdatere stilling i en ansatt-rad i databasen */
	void oppdaterStilling(int id, String nyStilling);

	/** Update - Oppdatere Månedslønn i en ansatt-rad i databasen */
	void oppdaterLonn(int id, int nyLonn);
	
	/** Delete - Slette en ansatt-rad fra databasen */
	void slettAnsatt(int id);
}
