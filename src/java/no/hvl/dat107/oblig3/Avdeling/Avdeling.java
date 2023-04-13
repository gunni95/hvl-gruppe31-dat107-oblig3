package no.hvl.dat107.oblig3.Avdeling;


import jakarta.persistence.*;

@Entity
@Table(schema = "oblig3", name = "avdeling")
@NamedQuery(name = "hentAlleAvdelinger", query = "SELECT a FROM Prosjekt as a order by a.id")
public class Avdeling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String navn;
    private String sjef; // required og burde være en AnsattId


    public Avdeling(String avdelingNavn, String avdelingSjef){
        this.navn = avdelingNavn;
        this.sjef = avdelingSjef;
    }

    public Avdeling() {

    }

    public String getAvdelingNavn(){
        return this.navn;
    }
    public void setAvdelingNavn(String avdelingNavn){
        this.navn = avdelingNavn;
    }
    public String getAvdelingSjef(){
        return this.sjef;
    }
    public void setAvdelingSjef(String avdelingSjef){
        this.sjef = avdelingSjef;
    }

    public Integer getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("Avdeling: id=%1$d, \nNavn = %2$s, \nSjef = %3$s\n", id, navn, sjef);
    }
}
