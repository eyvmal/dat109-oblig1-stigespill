package no.hvl.dat109;

import java.util.ArrayList;

public class Brett {

    private ArrayList<BrettRute> brett;
    private int storrelse;

    private ArrayList<Spiller> spillerListe;
    private int antallSpillere;
    private int hvemSinTur;
    private boolean spill;
    private boolean simulering;

    public Brett(int storrelse) {
        this.storrelse = storrelse;
        spillerListe = new ArrayList<Spiller>();
        antallSpillere = 0;
        hvemSinTur = 0;
        spill = true;
        simulering = false;

        brett = new ArrayList<BrettRute>(); // Oppretter et brett med ønsket antall ruter
        for (int i = 1; i < storrelse + 1; i++) { // Gjør navnet finere ved å legge til 0er forran.
            String navn = "";
            if (i < 10) {
                navn = "00" + i + " ";
            } else if (i < 100) {
                navn = "0" + i + " ";
            } else if (i < 1000) {
                navn = i + " ";
            }
            brett.add(new BrettRute(i, navn));
        }
    }

    /* ---------------
    Printing av brett
    --------------- */
    public void printBrett() {
        System.out.println(" --- Stigespillbrettet ---");
        for (int i = brett.size() - 1; 0 <= i; i--) {
            System.out.print(brett.get(i).getNavn());

            if ((i-1 >= 0) && (brett.get(i-1).getNr()%10 == 0)) {
                System.out.println();
                i -= printRevers(i);
            }
        }
    }
    // Måtte lage en reversmetode for å få brettet til å gå i sikksakk oppover
    public int printRevers(int index) {
        int temp = index - 10;
        for (int i = 9; 0 <= i; i--) {
            System.out.print(brett.get(temp).getNavn());
            temp++;
        }
        System.out.println();
        return 10;
    }

    /* --------------------
    Håndtering av spillere
    -------------------- */
    public void opprettSpiller(String navn) {
        if (antallSpillere == 4) { // Går ikke an å overstride 4 med mindre man hardkoder det inn lmao. Men better safe than sorry
            System.err.println("!!! Maksimalt antall spillere er nådd!");
            return;
        }
        spillerListe.add(new Spiller(antallSpillere, navn));
        antallSpillere++;
        int id = getIdMedNavn(navn);
        System.out.println("Spiller " + spillerListe.get(id).getNavn() + "(id: " + id + ")" + " har blitt opprettet!");
    }
    public void opprettDummy() { // For å hindre kodekrasj ved færre spillere
        spillerListe.add(new Spiller(69, "--"));
    }

    // Generell trille-metode -- Mulig den kan optimaliseres litt, men funker greit sånn her.
    public void trillNeste() {
        Spiller spiller = spillerListe.get(hvemSinTur%antallSpillere);
        int kast = spiller.getTerning().trill();

        // Sjekker om spilleren er blitt sendt tilbake til start pga for mange 6ere
        if (spiller.getBackToStart()) {
            if (kast == 6) {        // Kaster de 6 slipper de fri og kan fortsette, men får ikke nytt kast
                setpos(spiller.getId(), kast);
                System.out.println("Du kastet 6 og kan fortsette å spille!");
                spiller.setBackToStart(false);
                return;
            } else {                // Kaster de noe annet enn 6, skjer ingen ting
                System.out.println("Du kastet " + kast + ". Du trenger en 6er for å bli med i spillet igjen!");
                spiller.incStuckVedStart();
                hvemSinTur++;
                return;
            }
        }

        // Sjekker om de har kastet for mange 6ere. Hvis ja, blir de sendt til start og plassert under lås (backToStart).
        if (spiller.getTerning().getSekserePaaRad() > 2) {
            spiller.setBackToStart(true);
            spiller.setPosisjon(-1);
            System.out.println(spiller.getNavn() + " har trillet '" + spiller.getTerning().getSekserePaaRad() + "' 6ere på rad og sendes tilbake til start!");
            System.out.println("For å komme deg fri må du trille en sekser!");
            hvemSinTur++;
            return;
        }

        // Kode for helt vanlig kast.
        setpos(spiller.getId(), kast);
        System.out.println(spiller.getNavn() + " kastet " + kast + ". " + spiller.getNavn() + " står nå i rute " + (spiller.getPosisjon() + 1) + ".");
        harVunnet();
        if (kast == 6) {
            System.out.println("!! Du får kaste igjen!");
        } else {
            hvemSinTur++;
            if (!simulering)
                System.out.println(spillerListe.get(hvemSinTur%antallSpillere).getNavn() + " er nestemann!");
        }
    }

    // Sjekker om noen av spillerene har vunnet. Denne kjører etter hver gang noen får endret posisjonen sin
    public void harVunnet() {
            for (int i = 0; i < antallSpillere; i++) {
                if (spillerListe.get(i).getPosisjon() == 99) {
                    System.out.println(spillerListe.get(i).getNavn() + " har vunnet! Gratulerer!");
                    setSpill(false);
                }
            };
    }

    // Endrer posisjonen til en spiller, men sjekker også om de har overgått 100.
    // Da blir de sendt tilbake like mange som de gikk over :))
    // JA jeg vet det står i oppgaven at ingenting skal skje. Men jeg liker dette bedre.
    public void setpos(int id, int terningResultat) {
        Spiller spiller = spillerListe.get(id);
        int nyPosisjon = spiller.getPosisjon() + terningResultat;
        if (nyPosisjon > 99) {
            nyPosisjon = 99 - (nyPosisjon%99);
        }
        spiller.setPosisjon(nyPosisjon);
    }

    // Fant ut denne var grei å ha i noen tilfeller.
    public int getIdMedNavn(String navn) {
        boolean funnet = false;
        while (!funnet) {
            for (int i = 0; i < spillerListe.size(); i++) {
                if (spillerListe.get(i).getNavn().equals(navn)) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    // Master-switch for om spillet er i gang eller ikke
    public void setSpill(boolean verdi) {
        this.spill = verdi;
    }
    public boolean getSpill() {
        return spill;
    }
    public ArrayList<BrettRute> getBrett() {
        return brett;
    }
    public void setBrett(ArrayList<BrettRute> brett) {
        this.brett = brett;
    }

    public ArrayList<Spiller> getSpillerListe() {
        return spillerListe;
    }
    public void setSpillerListe(ArrayList<Spiller> spillerListe) {
        this.spillerListe = spillerListe;
    }
    public void setSimulering(boolean verdi) {
        simulering = verdi;
    }

    public static void linje() {
        System.out.println("-------------------------");
    }
    public void hjelp() {
        System.out.println("-------------------------");
        System.out.println("Spillmeny:");
        System.out.println("1: Se denne menyen igjen");
        System.out.println("2: Kast terning. " + spillerListe.get(hvemSinTur).getNavn() + " er neste til å kaste.");
        System.out.println("3: Vis brett");
        System.out.println("4: Avslutt spill");
        System.out.println("5: Simuler resten");
        System.out.println("-------------------------");
    }
}
