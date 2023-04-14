package no.hvl.dat107.oblig3.Prosjekt;


import jakarta.persistence.*;

@Entity
@Table(schema = "oblig3", name = "Prosjekt")
@NamedQuery(name = "hentAlleProsjekter", query = "SELECT a FROM Prosjekt as a order by a.id")
public class Prosjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String prosjektnavn;
    private String sjef;
    private String beskrivelse; // required og burde være en AnsattId
    private int timetall;


    public Prosjekt(String prosjektNavn, String sjef, String beskrivelse){
        this.prosjektnavn = prosjektNavn;
        this.sjef = sjef;
        this.beskrivelse = beskrivelse;
        this.timetall = 0;
    }

    public Prosjekt() {

    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }
    public String getBeskrivelse() {
        return beskrivelse;
    }
    public String getProsjektnavn(){
        return this.prosjektnavn;
    }
    public void setProsjektnavn(String ProsjektNavn){
        this.prosjektnavn = ProsjektNavn;
    }
    public String getSjef(){
        return this.beskrivelse;
    }
    public void setSjef(String ProsjektSjef){
        this.beskrivelse = ProsjektSjef;
    }
    public int getTimetall(){
        return this.timetall;
    }
    public void setTimetall(int TotaltTimeantall){
        this.timetall = TotaltTimeantall;
    }
    public Integer getId() {
        return this.id;
    }
    @Override
    public String toString() {
        return String.format("Prosjektnavn: id=%1$s, \nsjef: %2$s, \nBeskrivelse = %3$s, \nTotal timer = %4$d\n", this.prosjektnavn, this.sjef, this.beskrivelse, this.timetall);
    }

}