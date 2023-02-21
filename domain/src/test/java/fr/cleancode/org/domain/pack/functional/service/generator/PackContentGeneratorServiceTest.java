package fr.cleancode.org.domain.pack.functional.service.generator;

import fr.cleancode.org.domain.hero.ports.server.HeroFinderSpi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PackContentGeneratorServiceTest {

    @InjectMocks
    private PackContentGeneratorService generatorService;

    @Mock
    private HeroFinderSpi heroFinderSpi;

    @Test
    public void test_generate_content() {

    }

    @Test
    public void test_generate_content_with_invalid_probability() {

    }

    @Test
    public void test_probability() {

    }

}
