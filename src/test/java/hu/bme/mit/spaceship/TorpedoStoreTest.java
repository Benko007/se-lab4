package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    private TorpedoStore torpedoStore;

    @BeforeEach
    void setUp() {
        // Minden teszt elott inicializaljuk egy alapallapottal
        torpedoStore = new TorpedoStore(2);
    }

    @Test
    void testInitialState() {
        // Ellenorizzuk a kezdeti darabszamot es hogy nem ures-e
        assertEquals(2, torpedoStore.getTorpedoCount());
        assertFalse(torpedoStore.isEmpty());
    }

    @Test
    void testFireTorpedoSuccessfully() {
        // Tuzelunk 1 torpedot
        boolean result = torpedoStore.fire(1);
        
        assertTrue(result);
        assertEquals(1, torpedoStore.getTorpedoCount());
        assertFalse(torpedoStore.isEmpty());
    }

    @Test
    void testFireUntilEmpty() {
        // Ellojuk mind a kettot
        torpedoStore.fire(1);
        boolean secondResult = torpedoStore.fire(1);

        assertTrue(secondResult);
        assertEquals(0, torpedoStore.getTorpedoCount());
        assertTrue(torpedoStore.isEmpty());
    }

    @Test
    void testFireWhenEmptyReturnsFalse() {
        // Eloszor kiuritjuk a raktarat
        torpedoStore.fire(1);
        torpedoStore.fire(1);

        // Megprobalunk tuzelni ures raktarbol
        assertThrows(IllegalArgumentException.class, () -> {
            torpedoStore.fire(1);
        });
        
        assertEquals(0, torpedoStore.getTorpedoCount());
        assertTrue(torpedoStore.isEmpty());
    }
}
