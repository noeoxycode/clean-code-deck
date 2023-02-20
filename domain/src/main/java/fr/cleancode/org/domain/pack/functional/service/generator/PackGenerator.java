package fr.cleancode.org.domain.pack.functional.service.generator;

import fr.cleancode.org.domain.pack.functional.model.Pack;
import fr.cleancode.org.domain.pack.functional.model.PackType;

import static fr.cleancode.org.domain.pack.functional.model.PackType.DIAMOND;
import static fr.cleancode.org.domain.pack.functional.model.PackType.SILVER;

public interface PackGenerator {

    static Pack generatePack(PackType packType) {
        Pack pack = Pack.builder().build();
        switch (packType) {
            case SILVER -> pack.setPackType(SILVER);
            case DIAMOND -> pack.setPackType(DIAMOND);
        }
        return pack;
    }
}