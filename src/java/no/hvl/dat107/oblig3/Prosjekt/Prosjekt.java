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
    private String beskrivelse; // required og burde være en AnsattId
    private int TotaltTimeantall;


    public Prosjekt(String ProsjektNavn, String Beskrivelse){
        this.navn = ProsjektNavn;
        this.beskrivelse = Beskrivelse;
        this.TotaltTimeantall = 0;
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
        return this.beskrivelse;
    }
    public void setProsjektSjef(String ProsjektSjef){
        this.beskrivelse = ProsjektSjef;
    }
    public int getTotaltTimeantall(){
        return this.TotaltTimeantall;
    }
    public void setTotaltTimeantall(int TotaltTimeantall){
        this.TotaltTimeantall = TotaltTimeantall;
    }
    public Integer getId() {
        return this.id;
    }

}