package fr.cleancode.org.domain.pack.functional.service.initialization;

import fr.cleancode.org.domain.pack.functional.model.Pack;
import fr.cleancode.org.domain.pack.functional.model.PackType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PackInitializerTest {

    @Test
    public void should_initialize_silver_pack_properties() {
        Pack pack = Pack.builder().packType(PackType.SILVER).build();
        PackInitializer.initializePackPropertiesByType(pack);
        assertEquals(1, pack.getCost());
        assertEquals(3, pack.getCardsQuantity());
        assertArrayEquals(new int[]{75, 20, 5}, pack.getProbability());
    }

    @Test
    public void should_initialize_diamond_pack_properties() {
        Pack pack = Pack.builder().packType(PackType.DIAMOND).build();
        PackInitializer.initializePackPropertiesByType(pack);
        assertEquals(2, pack.getCost());
        assertEquals(5, pack.getCardsQuantity());
        assertArrayEquals(new int[]{50, 35, 15}, pack.getProbability());
    }

    @Test
    public void should_not_initialize_pack_properties_when_null() {
        Pack pack = null;
        assertThrows(NullPointerException.class, () -> {
            PackInitializer.initializePackPropertiesByType(pack);
        });
    }

}
