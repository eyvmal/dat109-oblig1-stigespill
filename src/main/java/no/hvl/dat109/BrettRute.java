package no.hvl.dat109;

import java.util.ArrayList;

public class BrettRute {
    private int nr;
    private String navn;
    private boolean erStige;    //Sjekke om ruten er en stige
    private int stigeTil;       //Hvor stigen går til
    private boolean erSlange;   //Sjekke om ruten er en slange
    private int slangeTil;      //Hvor slangen går til

    public BrettRute(int nr, String navn) {
        this.nr = nr;
        this.navn = navn;
        erStige = false;
        stigeTil = -1;
        erSlange = false;
        slangeTil = -1;
    }

    public int getNr() {
        return nr;
    }
    public String getNavn() {
        return navn;
    }

    public boolean erStige() {
        return erStige;
    }
    public void setErStige(boolean erStige) {
        this.erStige = erStige;
    }
    public int getStigeTil() {
        return stigeTil;
    }
    public void setStigeTil(int stigeTil) {
        this.stigeTil = stigeTil;
    }
    public boolean erSlange() {
        return erSlange;
    }
    public void setErSlange(boolean erSlange) {
        this.erSlange = erSlange;
    }
    public int getSlangeTil() {
        return slangeTil;
    }
    public void setSlangeTil(int slangeTil) {
        this.slangeTil = slangeTil;
    }
}

