package no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse;

import jakarta.persistence.*;

@Entity
@Table(schema = "oblig3", name = "Prosjektdeltakelse")
public class Prosjektdeltakelse {

    private Integer prosjektId;
    private Integer ansattId;
    private String rolle;
    private int prosjekttimer;

    public Prosjektdeltakelse(){

    }
    public Prosjektdeltakelse(Integer prosjektId, Integer ansattId, String rolle){
        this.prosjektId = prosjektId;
        this.ansattId = ansattId;
        this.rolle = rolle;
        this.prosjekttimer = 0;

    }

    public Integer getProsjektId() {
        return prosjektId;
    }

    public Integer getAnsattId() {
        return ansattId;
    }

    public Integer getId() {
        return id;
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
