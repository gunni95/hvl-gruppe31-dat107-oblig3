package no.hvl.dat107.oblig3.Iterasjon1;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(schema = "ansatte")
@NamedQuery(name = "hentAlleAnsatte", query = "SELECT a FROM Ansatt as a order by a.id")
public class Ansatt {
	@Id		private Integer id;
	private String Brukernavn;
	private String Fornavn;
	private String Etternavn;
	private String AnsettelsesDato;
	private String Stilling;
	private Integer MaanedsLonn;
	
	public Ansatt() {
		
	}
	
	public Ansatt(Integer id, String Brukernavn, String Fornavn, String Etternavn, String AnsettelsesDato, String Stilling, Integer MaanedsLonn) {
		this.id = id;
		this.Brukernavn = Brukernavn;
		this.Fornavn = Fornavn;
		this.Etternavn = Etternavn;
		this.AnsettelsesDato = AnsettelsesDato;
		this.Stilling = Stilling;
		this.MaanedsLonn = MaanedsLonn;
		id++;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrukernavn() {
		return Brukernavn;
	}
	public void setBrukernavn(String Brukernavn) {
		this.Brukernavn = Brukernavn;
	}
	public String getFornavn() {
		return Fornavn;
	}
	public void setFornavn(String Fornavn) {
		this.Fornavn = Fornavn;
	}
	public String getEtternavn() {
		return Etternavn;
	}
	public void setEtternavn(String Etternavn) {
		this.Etternavn = Etternavn;
	}
	public String getAnsettelsesDato() {
		return AnsettelsesDato;
	}
	public void setAnsettelsesDato(String AnsettelsesDato) {
		this.AnsettelsesDato = AnsettelsesDato;
	}
	public String getStilling() {
		return Stilling;
	}
	public void setStilling(String Stilling) {
		this.Stilling = Stilling;
	}
	public Integer getMaanedsLonn() {
		return MaanedsLonn;
	}
	public void setMaanedsLonn(Integer MaanedsLonn) {
		this.MaanedsLonn = MaanedsLonn;
	}
	
	@Override
	public String toString() {
		return String.format("Ansatt: id=%1$d, Brukernavn=%2$s, Fornavn=%3$s, Etternavn=%4$s, AnsettelsesDato=%5$s, Stilling=%6$s, MaanedsLonn=%7$d", id, Brukernavn, Fornavn, Etternavn, AnsettelsesDato, Stilling, MaanedsLonn);
	}
}
