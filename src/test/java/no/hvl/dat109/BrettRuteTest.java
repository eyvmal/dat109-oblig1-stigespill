package no.hvl.dat109;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrettRuteTest {

    @Test
    void sjekkKonstroktor() {
        BrettRute rute = new BrettRute(17, "017");
        assertEquals(17, rute.getNr());
        assertEquals("017", rute.getNavn());
        assertFalse(rute.erStige());
        assertEquals(-1, rute.getStigeTil());
        assertFalse(rute.erSlange());
        assertEquals(-1, rute.getSlangeTil());
    }

    @Test
    void erStige() {
        BrettRute rute = new BrettRute(17, "017");
        rute.setErStige(true);
        assertTrue(rute.erStige());
    }

    @Test
    void getStigeTil() {
        BrettRute rute = new BrettRute(17, "017");
        rute.setStigeTil(37);
        assertEquals(37, rute.getStigeTil());
    }

    @Test
    void erSlange() {
        BrettRute rute = new BrettRute(17, "017");
        rute.setErSlange(true);
        assertTrue(rute.erSlange());
    }

    @Test
    void getSlangeTil() {
        BrettRute rute = new BrettRute(17, "017");
        rute.setSlangeTil(37);
        assertEquals(37, rute.getSlangeTil());
    }
}