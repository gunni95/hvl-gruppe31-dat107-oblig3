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
    private String sjef;
    private String beskrivelse; // required og burde være en AnsattId
    private int totaltTimeantall;


    public Prosjekt(String prosjektNavn, String sjef, String beskrivelse){
        this.navn = prosjektNavn;
        this.sjef = sjef;
        this.beskrivelse = beskrivelse;
        this.totaltTimeantall = 0;
    }

    public Prosjekt() {

    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }
    public String getBeskrivelse() {
        return beskrivelse;
    }
    public String getNavn(){
        return this.navn;
    }
    public void setNavn(String ProsjektNavn){
        this.navn = ProsjektNavn;
    }
    public String getSjef(){
        return this.beskrivelse;
    }
    public void setSjef(String ProsjektSjef){
        this.beskrivelse = ProsjektSjef;
    }
    public int getTotaltTimeantall(){
        return this.totaltTimeantall;
    }
    public void setTotaltTimeantall(int TotaltTimeantall){
        this.totaltTimeantall = TotaltTimeantall;
    }
    public Integer getId() {
        return this.id;
    }
    @Override
    public String toString() {
        return "Prosjektnavn: " + this.navn + "\n" +
                "Sjef: " + this.sjef + "\n" +
                "Beskrivelse: " + this.beskrivelse + "\n" +
                "Total timer: " + this.totaltTimeantall + "\n";
    }

}