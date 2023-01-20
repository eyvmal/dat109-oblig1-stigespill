package no.hvl.dat109;

public class Terning {
    private int sekserePaaRad;
    private int seksereTotalt;
    private int enereTotalt;
    private int totalKast;
    private int stigerTotalt;
    private int slangerTotalt;

    public Terning() {
        sekserePaaRad = 0;
        seksereTotalt = 0;
        enereTotalt = 0;
        totalKast = 0;
        stigerTotalt = 0;
        slangerTotalt = 0;
    }

    public int trill(int enTilSeks) {
        totalKast++;

        if (enTilSeks == 6) {
            seksereTotalt++;
            sekserePaaRad++;
        } else sekserePaaRad = 0;

        if (enTilSeks == 1) enereTotalt++;
        return enTilSeks;
    }

    public void setSekserePaaRad(int seksere) { sekserePaaRad = seksere; }
    public int getSekserePaaRad() {
        return sekserePaaRad;
    }
    public int getSeksereTotalt() {
        return seksereTotalt;
    }
    public int getEnereTotalt() {
        return enereTotalt;
    }
    public int getTotalKast() {
        return totalKast;
    }
    public void incSlangerTotalt() {slangerTotalt++; }
    public int getSlangerTotalt() {return slangerTotalt; }
    public void incStigerTotalt() {stigerTotalt++; }
    public int getStigerTotalt() {return stigerTotalt; }
}
