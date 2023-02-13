package fr.cleancode.org.domain.hero.functional.model;

public enum Rarity {
    COMMON(0),
    RARE(10),
    LEGENDARY(20);

    public final int value;

    Rarity(int value) {
        this.value = value;
    }
}