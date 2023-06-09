package no.hvl.dat107.oblig3.Ansatt;

import jakarta.persistence.*;

@Entity
@Table(schema = "oblig3", name="ansatt")
@NamedQuery(name = "hentAlleAnsatte", query = "SELECT a FROM Ansatt as a order by a.id")
public class Ansatt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private String ansettelsesDato;
	private Integer avdeling;
	private String stilling;
	private Integer maanedsLonn;
	
	public Ansatt() {
		
	}

	public Ansatt(String brukernavn, String fornavn, String etternavn, String ansettelsesDato, Integer avdeling, String stilling, Integer maanedsLonn) {
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelsesDato = ansettelsesDato;
		this.avdeling = avdeling;
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
	public Integer getAvdeling() {
		return this.avdeling;
	}
	public void setAvdeling(Integer avdeling) { this.avdeling = avdeling; }
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
		return String.format("Ansatt: id=%1$d, \nBrukernavn = %2$s, \nFornavn = %3$s, \nEtternavn = %4$s, \nAnsettelsesdato = %5$s, \nStilling = %6$s, \nMaanedslonn = %7$d\n", id, brukernavn, fornavn, etternavn, ansettelsesDato, stilling, maanedsLonn);
	}
}