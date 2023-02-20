package fr.cleancode.org.domain.pack.functional.service.initialization;

import fr.cleancode.org.domain.pack.functional.model.Pack;
import fr.cleancode.org.domain.pack.functional.model.PackType;

public interface PackInitializer {

    static void initializePackPropertiesByType(Pack pack) {
        PackType packType = pack.getPackType();
        switch (packType) {
            case SILVER -> {
                pack.setCost(1);
                pack.setCardsQuantity(3);
                pack.setProbability(new int[]{75, 20, 5});
            }
            case DIAMOND -> {
                pack.setCost(2);
                pack.setCardsQuantity(5);
                pack.setProbability(new int[]{50, 35, 15});
            }
        }
    }
}