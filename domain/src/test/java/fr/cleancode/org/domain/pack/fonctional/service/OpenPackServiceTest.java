package fr.cleancode.org.domain.pack.fonctional.service;
//
//import fr.cleancode.org.domain.hero.functional.model.Hero;
//import fr.cleancode.org.domain.pack.functional.exception.PaymentException;
//import fr.cleancode.org.domain.pack.functional.model.Pack;
//import fr.cleancode.org.domain.pack.functional.model.PackType;
//import fr.cleancode.org.domain.pack.functional.service.OpenPackService;
//import fr.cleancode.org.domain.pack.functional.service.generator.PackContentGeneratorService;
//import fr.cleancode.org.domain.player.functional.model.Player;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class OpenPackServiceTest {
//
//        @Mock
//        private PlayerPersistenceSpi spi;
//
//        @Mock
//        private PackContentGeneratorService packContentGeneratorService;
//
//        @InjectMocks
//        private OpenPackService openPackService;
//
//        @Test
//        void shouldReturnPackContent() throws PaymentException {
//            UUID playerId = UUID.randomUUID();
//            Pack pack = PackType.SILVER.value;
//            pack.setCost(10);
//            Player player = Player.builder().token(20).deck(new ArrayList<>()).build();
//            List<Hero> expectedPackContent = Arrays.asList(Hero.builder().name("Hero 1").build(), (Hero.builder().name("Hero 2").build()));
//            when(spi.findPlayerById(playerId)).thenReturn(player);
//            when(packContentGeneratorService.generateContent(pack)).thenReturn(expectedPackContent);
//
//            List<Hero> packContent = openPackService.openPack(playerId, pack);
//
//            assertEquals(expectedPackContent, packContent);
//            assertEquals(10, player.getToken());
//            verify(spi).save(player);
//        }
//
//    @Test
//    void shouldThrowPaymentExceptionIfInsufficientTokens() throws PaymentException {
//        UUID playerId = UUID.randomUUID();
//        Pack pack = PackType.SILVER.value;
//        pack.setCost(10);
//        Player player = Player.builder().token(5).deck(new ArrayList<>()).build();
//
//        when(spi.findPlayerById(playerId)).thenReturn(player);
//
//        assertThrows(PaymentException.class, () -> openPackService.openPack(playerId, pack));
//        assertEquals(5, player.getToken());
//        verify(spi, never()).save(player);
//    }
//}
