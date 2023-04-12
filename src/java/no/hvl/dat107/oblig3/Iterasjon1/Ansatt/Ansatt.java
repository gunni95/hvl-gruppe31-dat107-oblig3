package no.hvl.dat107.oblig3.Iterasjon1.Ansatt;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(schema = "oblig3", name="ansatt")
@NamedQuery(name = "hentAlleAnsatte", query = "SELECT a FROM Ansatt as a order by a.id")
public class Ansatt {
	@Id
	private Integer id;
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private String ansettelsesDato;
	private String stilling;
	private Integer maanedsLonn;
	
	public Ansatt() {
		
	}

	public Ansatt(String brukernavn, String fornavn, String etternavn, String ansettelsesDato, String stilling, Integer maanedsLonn) {
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelsesDato = ansettelsesDato;
		this.stilling = stilling;
		this.maanedsLonn = maanedsLonn;
	}

	public Integer getId() {
		return this.id;
	}
	public String getBrukernavn() {
		return brukernavn;
	}
	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}
	public String getFornavn() {
		return fornavn;
	}
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	public String getEtternavn() {
		return etternavn;
	}
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}
	public String getAnsettelsesDato() {
		return ansettelsesDato;
	}
	public void setAnsettelsesDato(String ansettelsesDato) {
		this.ansettelsesDato = ansettelsesDato;
	}
	public String getStilling() {
		return stilling;
	}
	public void setStilling(String stilling) {
		this.stilling = stilling;
	}
	public Integer getMaanedsLonn() {
		return maanedsLonn;
	}
	public void setMaanedsLonn(Integer maanedsLonn) {
		this.maanedsLonn = maanedsLonn;
	}

	@Override
	public String toString() {
		return String.format("Ansatt: id=%1$d, Brukernavn=%2$s, Fornavn=%3$s, Etternavn=%4$s, AnsettelsesDato=%5$s, Stilling=%6$s, MaanedsLonn=%7$d", id, brukernavn, fornavn, etternavn, ansettelsesDato, stilling, maanedsLonn);
	}
}