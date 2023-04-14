package no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse;

import jakarta.persistence.*;

@Entity
@Table(schema = "oblig3", name = "Prosjektdeltakelse")
public class Prosjektdeltakelse {
    @Id
    private Integer prosjektid;
    @Id
    private Integer ansattid;
    private String rolle;
    private int prosjekttimer;

    public Prosjektdeltakelse(){

    }
    public Prosjektdeltakelse(Integer prosjektid, Integer ansattid, String rolle){
        this.prosjektid = prosjektid;
        this.ansattid = ansattid;
        this.rolle = rolle;
        this.prosjekttimer = 0;

    }

    public String getId() {
        return this.prosjektid.toString() + this.ansattid.toString();
    }
    public Integer getProsjektid() {
        return prosjektid;
    }

    public Integer getAnsattid() {
        return ansattid;
    }

    public String getRolle(){
        return this.rolle;
    }
    public void setRolle(String rolle){
        this.rolle = rolle;
    }
    public int getProsjekttimer(){
        return this.prosjekttimer;
    }
    public void setProsjekttimer(int prosjekttimer){
        this.prosjekttimer = prosjekttimer;
    }
}
