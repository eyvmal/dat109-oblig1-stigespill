package no.hvl.dat109;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpillerTest {

    // Visste ikke helt hva jeg skal teste her, så tester bare kosntruktøren

    @Test
    void sjekkKonstroktor() {
        Spiller spiller = new Spiller(420, "JanBanan");

        assertEquals(420, spiller.getId());                 // Sjekker om id blir gitt riktig
        assertEquals("JanBanan", spiller.getNavn());        // Sjekker om navn blir gitt riktig
        assertEquals(-1, spiller.getPosisjon());            // Sjekker om startpos er -1
        assertFalse(spiller.getBackToStart());                       // Sjekker om backtostart er false
        assertEquals(0, spiller.getStuckVedStart());        // Sjekker at stuckvedstart blir satt til 0
    }

    @Test
    void sjekkSetPosisjon() {
        Spiller spiller = new Spiller(420, "JanBanan");

        spiller.setPosisjon(42);
        assertEquals(42, spiller.getPosisjon());
    }

    @Test
    void testPrintLogMedData() {
        Spiller spiller = new Spiller(420, "JanBanan");

        for (int i = 0; i < 10; i++) {
            spiller.setPosisjon(i);
        }

        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]",
                spiller.printPosisjonLog());
    }

    @Test
    void testPrintLogUtenData() {
        Spiller spiller = new Spiller(420, "JanBanan");

        assertEquals("Ingen posisjonsdata", spiller.printPosisjonLog());
    }
}