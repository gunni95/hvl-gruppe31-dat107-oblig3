package no.hvl.dat107.oblig3.Prosjekt;


import jakarta.persistence.*;

@Entity
@Table(schema = "oblig3", name = "Prosjekt")
@NamedQuery(name = "hentAlleProsjekter", query = "SELECT a FROM Prosjekt as a order by a.id")
public class Prosjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String navn;
    private String sjef; // required og burde være en AnsattId


    public Prosjekt(String ProsjektNavn, String ProsjektSjef){
        this.navn = ProsjektNavn;
        this.sjef = ProsjektSjef;
    }

    public Prosjekt() {

    }

    public String getProsjektNavn(){
        return this.navn;
    }
    public void setProsjektNavn(String ProsjektNavn){
        this.navn = ProsjektNavn;
    }
    public String getProsjektSjef(){
        return this.sjef;
    }
    public void setProsjektSjef(String ProsjektSjef){
        this.sjef = ProsjektSjef;
    }

    public Integer getId() {
        return this.id;
    }

}