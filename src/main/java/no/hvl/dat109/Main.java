package no.hvl.dat109;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Spiller> spillerListe;
    private static Spiller s1;
    private static Spiller s2;
    private static Spiller s3;
    private static Spiller s4;

    public static void main(String[] args) throws InterruptedException {

        // Setter opp et brett
        Brett brett = new Brett(100);
        brett.printBrett();

        // Registrering av spillere. Den vil automatisk avslutte hvis 4 spillere registrerer seg
        brett.linje();
        System.out.println("Skriv inn navn på spillere. Maks 4!");
        System.out.println("Skriv 'ferdig' når alle er registrert.");

        while(brett.getSpillerListe().size() < 4) {
            Scanner in = new Scanner(System.in);
            String navn = in.nextLine();

            if (navn.toLowerCase().equals("ferdig")) {
                if (brett.getSpillerListe().size() < 2) {
                    System.out.println("Du må registrere minimum 2 spillere!");
                } else
                    while(brett.getSpillerListe().size() < 4) {
                        brett.opprettDummy();
                    }
            } else
                brett.opprettSpiller(navn);
        }

        brett.linje();
        System.out.println("Spillmeny:");
        System.out.println("1: Se denne menyen igjen");
        System.out.println("2: Kast terning. " + brett.getSpillerListe().get(0).getNavn() + " er først.");
        System.out.println("3: Vis brett");
        System.out.println("4: Avslutt spill");
        System.out.println("5: Simuler resten");
        brett.linje();

        while (brett.getSpill()) {
            Scanner in = new Scanner(System.in);
            int input = Integer.valueOf(in.nextLine());

            switch (input) {
                case 1:
                    brett.hjelp();
                    break;
                case 2:
                    brett.trillNeste();
                    break;
                case 3:
                    brett.printBrett();
                    break;
                case 4:
                    brett.linje();
                    System.out.println("Spillet er avsluttet :)");
                    brett.linje();
                    brett.setSpill(false);
                    break;
                case 5:
                    while (brett.getSpill()) {
                        // Dropper å si hvem som er nestemann i køen. Mindre spam i konsoll
                        brett.setSimulering(true);
                        brett.trillNeste();
                        Thread.sleep(50);
                    }
                    break;
            }
        }

        // Lagrer spillerne lokalt for å slippe gjentagende metodekall
        spillerListe = brett.getSpillerListe();
        s1 = spillerListe.get(0);
        s2 = spillerListe.get(1);
        s3 = spillerListe.get(2);
        s4 = spillerListe.get(3);

        Brett.linje();
        System.out.println("Statistikk!");
        System.out.println("1: for å printe ut " + s1.getNavn() + " sin statistikk");
        System.out.println("2: for å printe ut " + s2.getNavn() + " sin statistikk");
        System.out.println("3: for å printe ut " + s3.getNavn() + " sin statistikk");
        System.out.println("4: for å printe ut " + s4.getNavn() + " sin statistikk");
        System.out.println("Skriv '0' når du er ferdig :)");
        Brett.linje();

        boolean temp = true;

        while (temp) {
            Scanner in = new Scanner(System.in);
            int input = Integer.valueOf(in.nextLine());

            switch (input) {
                case 0:
                    temp = false;
                    break;
                case 1:
                    System.out.println("Statistikk for " + s1.getNavn());
                    System.out.println("--- Terning ---");
                    System.out.println("Enere totalt: " + s1.getTerning().getEnereTotalt());
                    System.out.println("Seksere totalt: " + s1.getTerning().getSeksereTotalt());
                    System.out.println("Totalt antall kast: " + s1.getTerning().getTotalKast());
                    System.out.println("--- Spiller ---");
                    System.out.println("Avslutningposisjon: " + (s1.getPosisjon() + 1));
                    System.out.println("Antall kast stuck ved start: " + s1.getStuckVedStart());
                    System.out.println("Antall stiger: --");
                    System.out.println("Antall slanger: --");
                    System.out.println("--- Log ---");
                    s1.printPosisjonLog();
                    Brett.linje();
                    break;
                case 2:
                    System.out.println("Statistikk for " + s2.getNavn());
                    System.out.println("--- Terning ---");
                    System.out.println("Enere totalt: " + s2.getTerning().getEnereTotalt());
                    System.out.println("Seksere totalt: " + s2.getTerning().getSeksereTotalt());
                    System.out.println("Totalt antall kast: " + s2.getTerning().getTotalKast());
                    System.out.println("--- Spiller ---");
                    System.out.println("Avslutningposisjon: " + (s2.getPosisjon() + 1));
                    System.out.println("Antall kast stuck ved start: " + s2.getStuckVedStart());
                    System.out.println("Antall stiger: --");
                    System.out.println("Antall slanger: --");
                    System.out.println("--- Log ---");
                    s2.printPosisjonLog();
                    Brett.linje();
                    break;
                case 3:
                    System.out.println("Statistikk for " + s3.getNavn());
                    System.out.println("--- Terning ---");
                    System.out.println("Enere totalt: " + s3.getTerning().getEnereTotalt());
                    System.out.println("Seksere totalt: " + s3.getTerning().getSeksereTotalt());
                    System.out.println("Totalt antall kast: " + s3.getTerning().getTotalKast());
                    System.out.println("--- Spiller ---");
                    System.out.println("Avslutningposisjon: " + (s3.getPosisjon() + 1));
                    System.out.println("Antall kast stuck ved start: " + s3.getStuckVedStart());
                    System.out.println("Antall stiger: --");
                    System.out.println("Antall slanger: --");
                    System.out.println("--- Log ---");
                    s3.printPosisjonLog();
                    Brett.linje();
                    break;
                case 4:
                    System.out.println("Statistikk for " + s4.getNavn());
                    System.out.println("--- Terning ---");
                    System.out.println("Enere totalt: " + s4.getTerning().getEnereTotalt());
                    System.out.println("Seksere totalt: " + s4.getTerning().getSeksereTotalt());
                    System.out.println("Totalt antall kast: " + s4.getTerning().getTotalKast());
                    System.out.println("--- Spiller ---");
                    System.out.println("Avslutningposisjon: " + (s4.getPosisjon() + 1));
                    System.out.println("Antall kast stuck ved start: " + s4.getStuckVedStart());
                    System.out.println("Antall stiger: --");
                    System.out.println("Antall slanger: --");
                    System.out.println("--- Log ---");
                    s4.printPosisjonLog();
                    Brett.linje();
                    break;
            }
        }
        System.out.println("Takk for at du spilte!");
        System.out.println("Ha det bra :)");
    }
}