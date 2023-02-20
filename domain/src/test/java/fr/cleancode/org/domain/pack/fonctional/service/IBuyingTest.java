package fr.cleancode.org.domain.pack.fonctional.service;
//
//import fr.cleancode.org.domain.pack.functional.exception.PaymentException;
//import fr.cleancode.org.domain.pack.functional.service.OpenPackService;
//import fr.cleancode.org.domain.player.functional.model.Player;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//
//@ExtendWith(MockitoExtension.class)
//public class IBuyingTest {
//
//    @InjectMocks
//    OpenPackService openPackService;
//
//    @Test
//    void shouldPayProductIfSufficientTokens() throws IllegalArgumentException {
//        Player player = Player.builder().token(50).build();
//        int price = 2;
//
//        openPackService.openPack(player, price);
//
//        assertEquals(48, player.getToken());
//    }
//
//    @Test
//    void shouldThrowPaymentExceptionIfInsufficientTokens() throws PaymentException {
//        Player player = Player.builder().token(50).build();
//        int price = 100;
//
//        assertThrows(PaymentException.class, () -> {
//            openPackService.payProduct(player, price);
//        });
//
//        assertEquals(50, player.getToken());
//    }
//}
//
