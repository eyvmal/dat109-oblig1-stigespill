package no.hvl.dat109;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TerningTest {
    @Test
    void trillSeks() {
        Terning terning = new Terning();
        for (int i = 0; i < 100; i++) {
            terning.trill(6);
        }
        assertEquals(100, terning.getSeksereTotalt());      // Sjekker at den logger seksere
        assertEquals(0, terning.getEnereTotalt());          // Sjekker at den ikke logger enere også
        assertEquals(100, terning.getSekserePaaRad());      // Sjekker at den også legger dem til i denne listen
    }

    @Test
    void trillFem() {
        Terning terning = new Terning();
        for (int i = 0; i < 100; i++) {
            terning.trill(5);
        }
        assertEquals(0, terning.getSeksereTotalt());        // Sjekker at den ikke logger femere
        assertEquals(0, terning.getEnereTotalt());
        assertEquals(0, terning.getSekserePaaRad());
    }

    @Test
    void trillFire() {
        Terning terning = new Terning();
        for (int i = 0; i < 100; i++) {
            terning.trill(4);
        }
        assertEquals(0, terning.getSeksereTotalt());        // Sjekker at den ikke logger firere
        assertEquals(0, terning.getEnereTotalt());
        assertEquals(0, terning.getSekserePaaRad());
    }

    @Test
    void trillTre() {
        Terning terning = new Terning();
        for (int i = 0; i < 100; i++) {
            terning.trill(3);
        }
        assertEquals(0, terning.getSeksereTotalt());        // Sjekker at den ikke logger treere
        assertEquals(0, terning.getEnereTotalt());
        assertEquals(0, terning.getSekserePaaRad());
    }

    @Test
    void trillTo() {
        Terning terning = new Terning();
        for (int i = 0; i < 100; i++) {
            terning.trill(2);
        }
        assertEquals(0, terning.getSeksereTotalt());        // Sjekker at den ikke logger toere
        assertEquals(0, terning.getEnereTotalt());
        assertEquals(0, terning.getSekserePaaRad());
    }

    @Test
    void trillEn() {
        Terning terning = new Terning();
        for (int i = 0; i < 100; i++) {
            terning.trill(1);
        }
        assertEquals(0, terning.getSeksereTotalt());        // Sjekker at den logger enere
        assertEquals(100, terning.getEnereTotalt());        // Men ikke seksere også
        assertEquals(0, terning.getSekserePaaRad());
    }

    @Test
    void incSlangerTotalt() {
        Terning terning = new Terning();
        terning.incStigerTotalt();

    }

    @Test
    void incStigerTotalt() {
        Terning terning = new Terning();
    }
}