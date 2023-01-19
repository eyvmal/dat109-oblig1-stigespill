package no.hvl.dat109;

public class Terning {
    private int sekserePaaRad;
    private int seksereTotalt;
    private int enereTotalt;
    private int sisteKast;
    private int totalKast;

    public Terning() {
        sekserePaaRad = 0;
        seksereTotalt = 0;
        enereTotalt = 0;
        sisteKast = 0;
        totalKast = 0;
    }

    public int trill() {
        int kast = (int)Math.floor(Math.random() * 6 + 1);    // Genererer et tall mellom 1 og 6
        sisteKast = kast;
        totalKast++;

        if (kast == 6) {
            seksereTotalt++;
            sekserePaaRad++;
        } else sekserePaaRad = 0;

        if (kast == 1) enereTotalt++;
        return kast;
    }

    public int getSekserePaaRad() {
        return sekserePaaRad;
    }
    public int getSeksereTotalt() {
        return seksereTotalt;
    }
    public int getEnereTotalt() {
        return enereTotalt;
    }
    public int getSisteKast() {
        return sisteKast;
    }
    public int getTotalKast() {
        return totalKast;
    }
}
