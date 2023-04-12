package no.hvl.dat107.oblig3.Iterasjon1.Avdeling;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(schema = "avdelinger")
@NamedQuery(name = "hentAlleAvdelinger", query = "SELECT a FROM Avdeling as a order by a.id")
public class Avdeling {
    @Id
    private Integer id;
    private String navn;
    private String sjef; // required og burde være en AnsattId


    public Avdeling(Integer avdelingId, String avdelingNavn, String avdelingSjef){
        this.id = avdelingId;
        this.navn = avdelingNavn;
        this.sjef = avdelingSjef;
    }

    public Avdeling() {

    }

    public Integer getAvdelingId(){
        return this.id;
    }
    public void setAvdelingId(Integer avdelingId){
        this.id = avdelingId;
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
}