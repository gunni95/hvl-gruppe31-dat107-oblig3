package no.hvl.dat107.oblig3.Iterasjon1.Avdeling;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(schema = "avdelinger")
@NamedQuery(name = "hentAlleAvdelinger", query = "SELECT a FROM Avdeling as a order by a.avdelingId")
public class Avdeling {
    @Id static Integer avdelingId;
    static String avdelingNavn;
    static String avdelingSjef; // required og burde være en AnsattId



    public Avdeling(Integer avdelingId, String avdelingNavn, String avdelingSjef){
        this.avdelingId = avdelingId;
        this.avdelingNavn = avdelingNavn;
        this.avdelingSjef = avdelingSjef;
    }

    public Avdeling() {

    }

    public Integer getAvdelingId(){return avdelingId;}
    public Integer setAvdelingId(Integer avdelingId){this.avdelingId = avdelingId;}
    public String getAvdelingNavn(){return avdelingNavn;}
    public void setAvdelingNavn(String avdelingNavn){this.avdelingNavn = avdelingNavn;}
    public String getAvdelingSjef(){return avdelingSjef;}
    public String setAvdelingSjef(String avdelingSjef){this.avdelingSjef = avdelingSjef;}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
