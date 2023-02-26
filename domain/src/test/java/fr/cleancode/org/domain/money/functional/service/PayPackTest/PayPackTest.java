package fr.cleancode.org.domain.money.functional.service.PayPackTest;

import fr.cleancode.org.domain.player.functional.model.Player;
import org.junit.jupiter.api.Test;

import static fr.cleancode.org.domain.money.payPackValidator.PayPack.payPack;
import static org.junit.jupiter.api.Assertions.*;

public class PayPackTest {

    @Test
    public void test_pay_pack_success() {
        Player player = Player.builder().token(5).build();
        int cost = 2;
        assertTrue(payPack(player, cost));
        assertEquals(player.getToken(), 3);
    }

    @Test
    public void test_payPack_failure() {
        Player player = Player.builder().token(5).build();
        int cost = 10;
        assertFalse(payPack(player, cost));
        assertEquals(player.getToken(), 5);
    }

    @Test
    public void test_pay_pack_negative_cost() {
        Player player = Player.builder().token(5).build();
        int cost = -5;
        assertFalse(payPack(player, cost));
        assertEquals(player.getToken(), 5);
    }

    @Test
    public void test_pay_pack_null_player() {
        Player player = null;
        int cost = 5;
        assertFalse(payPack(player, cost));
        assertNull(player);
    }

}
