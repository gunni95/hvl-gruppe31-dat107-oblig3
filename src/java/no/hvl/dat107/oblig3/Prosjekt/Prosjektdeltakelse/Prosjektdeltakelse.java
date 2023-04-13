package no.hvl.dat107.oblig3.Prosjekt.Prosjektdeltakelse;

public class Prosjektdeltakelse {

    private String rolle;
    private int prosjekttimer;

    public Prosjektdeltakelse(){

    }
    public Prosjektdeltakelse(String rolle){
        this.rolle = rolle;
        this.prosjekttimer = 0;
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
