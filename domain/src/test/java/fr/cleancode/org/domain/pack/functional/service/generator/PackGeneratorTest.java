package fr.cleancode.org.domain.pack.functional.service.generator;

import fr.cleancode.org.domain.pack.functional.model.Pack;
import fr.cleancode.org.domain.pack.functional.model.PackType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PackGeneratorTest {

    @Test
    public void should_generate_silver_pack() {
        Pack generatedPack = PackGenerator.generatePack(PackType.SILVER);
        assertEquals(PackType.SILVER, generatedPack.getPackType());
    }

    @Test
    public void should_generate_diamond_pack() {
        Pack generatedPack = PackGenerator.generatePack(PackType.DIAMOND);
        assertEquals(PackType.DIAMOND, generatedPack.getPackType());
    }

    @Test
    public void should_not_generate_pack() {
        assertThrows(NullPointerException.class, () -> {
            PackGenerator.generatePack(null);
        });
    }

}
