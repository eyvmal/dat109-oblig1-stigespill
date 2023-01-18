package no.hvl.dat109;

import java.util.ArrayList;

public class Spiller {

    private int id;
    private String navn;
    private Terning terning;
    private int posisjon;
    private ArrayList<Integer> posisjonLog;

    private boolean backToStart;
    private int stuckVedStart;

    public Spiller(int id, String navn) {
        this.id = id;
        this.navn = navn;
        terning = new Terning();
        posisjon = -1;
        posisjonLog = new ArrayList<Integer>();
        backToStart = false;
        stuckVedStart = 0;
    }


    public int getId() {
        return id;
    }
    public String getNavn() {
        return navn;
    }
    public int getPosisjon() {
        return posisjon;
    }
    public void setPosisjon(int nyPosisjon) {
        posisjonLog.add(nyPosisjon);
        this.posisjon = nyPosisjon;
    }
    public Terning getTerning() {
        return terning;
    }
    public boolean getBackToStart() {
        return backToStart;
    }
    public void setBackToStart(boolean verdi) {
        backToStart = verdi;
    }
    public int getStuckVedStart() {
        return stuckVedStart;
    }
    public void incStuckVedStart() {
        stuckVedStart++;
    }

    public void printPosisjonLog() {
       if (!posisjonLog.isEmpty()) {
           String log = "[";
           for (int i = 0; i < posisjonLog.size() - 1; i++) {
               log += (posisjonLog.get(i) + 1) + ", ";
           }
           log += (posisjonLog.get(posisjonLog.size() - 1) + 1) + "]";

           System.out.println(log);
       }
    }
}
