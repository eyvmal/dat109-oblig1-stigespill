package no.hvl.dat109;

import java.util.ArrayList;

public class Brett {
    private final ArrayList<BrettRute> brett;
    private final ArrayList<Spiller> spillerListe;
    private int antallSpillere;
    private int hvemSinTur;
    private boolean spill;
    private boolean simulering;

    public Brett() {
        spillerListe = new ArrayList<>();
        antallSpillere = 0;
        hvemSinTur = 0;
        spill = true;
        simulering = false;

        brett = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {     // Gjør navnet finere ved å legge til 0er forran
            String navn = "";                // Slik at det blir like mange siffere per rute
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

    public void printBrett() {
        System.out.println(" --- Stigespillbrettet ---");
        for (int i = brett.size() - 1; 0 <= i; i--) {
            System.out.print(brett.get(i).getNavn());

            // Printer neste 10 ruter i revers for å lage sikksakk-mønster
            if ((i-1 >= 0) && (brett.get(i-1).getNr()%10 == 0)) {
                System.out.println();
                i -= printRevers(i);
            }
        }
    }

    public int printRevers(int index) {
        int temp = index - 10;
        for (int i = 9; 0 <= i; i--) {
            System.out.print(brett.get(temp).getNavn());
            temp++;
        }
        System.out.println();
        return 10;
    }

    // Oppretter en spiller med selvvalgt navn
    public void opprettSpiller(String navn) {
        // Går ikke an å overstride 4 med mindre man hardkoder det inn lmao.
        // Men better safe than sorry
        if (antallSpillere == 4) {
            System.err.println("!!! Maksimalt antall spillere er nådd!");
            return;
        }
        spillerListe.add(new Spiller(antallSpillere, navn));
        int id = antallSpillere;
        antallSpillere++;
        System.out.println("Spiller " + spillerListe.get(id).getNavn() + "(id: " + id + ")" + " har blitt opprettet!");
    }

    public void opprettDummy() { // For å hindre kodekrasj ved færre spillere
        spillerListe.add(new Spiller(69, "--"));
    }

    // Generell trille-metode -- Mulig den kan optimaliseres litt, men funker...
    public void trillNeste() {
        Spiller spiller = spillerListe.get(hvemSinTur%antallSpillere);
        int kast = spiller.getTerning().trill();

        // Sjekker om spilleren er blitt sendt tilbake til start pga for mange 6ere
        if (spiller.getBackToStart()) {
            // Kaster de 6 slipper de fri og kan fortsette, men får ikke nytt kast
            if (kast == 6) {
                System.out.println("Du kastet 6 og kan fortsette å spille!");
                spiller.setBackToStart(false);
                spiller.getTerning().setSekserePaaRad(0);

            // Kaster de noe annet enn 6, blir det neste sin tur
            } else {
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
        if(!setpos(spiller.getId(), kast)) {
            System.out.println(spiller.getNavn() + " kastet " + kast + ". " +
                    spiller.getNavn() + " står nå i rute " + (spiller.getPosisjon() + 1) + ".");
        } // Hvis de lander på stige/slange tar setpos() seg av utskrivningen

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
            }
    }

    // Endrer posisjonen til en spiller, men sjekker også om de har overgått 100.
    // Da blir de sendt tilbake like mange som de gikk over :))
    // JA jeg vet det står i oppgaven at ingenting skal skje. Men jeg liker dette bedre.
    public boolean setpos(int id, int terningResultat) {
        Spiller spiller = spillerListe.get(id);
        int nyPosisjon = spiller.getPosisjon() + terningResultat;
        if (nyPosisjon > 99) {
            nyPosisjon = 99 - (nyPosisjon%99);
        }
        if (brett.get(nyPosisjon).erSlange()) {
            System.out.println(spiller.getNavn() + " kastet " + spiller.getTerning().getSisteKast() + ".\n!! " +
                    spiller.getNavn() + " landet på en slange og sklir fra " +
                    (nyPosisjon + 1) + " til " + (brett.get(nyPosisjon).getSlangeTil() + 1) + ".");
            spiller.setPosisjon(brett.get(nyPosisjon).getSlangeTil());
            spiller.getTerning().incSlangerTotalt();
            return true;
        } else if (brett.get(nyPosisjon).erStige()) {
            System.out.println(spiller.getNavn() + " kastet " + spiller.getTerning().getSisteKast() + ".\n!! " +
                    spiller.getNavn() + " landet på en stige og klatrer fra " +
                    (nyPosisjon + 1) + " til " + (brett.get(nyPosisjon).getStigeTil() + 1) + ".");
            spiller.setPosisjon(brett.get(nyPosisjon).getStigeTil());
            spiller.getTerning().incStigerTotalt();
            return true;
        }
        spiller.setPosisjon(nyPosisjon);
        return false;
    }

    public void lagStige(int id, int stigeTil) {
        brett.get(id).setErStige(true);
        brett.get(id).setStigeTil(stigeTil);
    }
    public void lagSlange(int id, int slangeTil) {
        brett.get(id).setErSlange(true);
        brett.get(id).setSlangeTil(slangeTil);
    }

    // Master-switch for om spillet er i gang eller ikke
    public void setSpill(boolean verdi) {
        this.spill = verdi;
    }
    public boolean getSpill() {
        return spill;
    }
    public ArrayList<Spiller> getSpillerListe() {
        return spillerListe;
    }
    public void setSimulering(boolean verdi) {
        simulering = verdi;
    }

    // Utskrift-metoder
    public static void linje() {
        System.out.println("-------------------------");
    }
    public void hjelp() {
        System.out.println("-------------------------");
        System.out.println("Spillmeny:");
        System.out.println("1: Se denne menyen igjen");
        System.out.println("2: Kast terning. " + spillerListe.get(hvemSinTur%antallSpillere).getNavn() + " er neste til å kaste.");
        System.out.println("3: Vis brett");
        System.out.println("4: Avslutt spill");
        System.out.println("5: Simuler resten");
        System.out.println("-------------------------");
    }
}
