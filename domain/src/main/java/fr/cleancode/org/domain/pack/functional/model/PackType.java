package fr.cleancode.org.domain.pack.functional.model;

public enum PackType {
    SILVER(SilverPack.builder().build()),
    DIAMOND(DiamondPack.builder().build());

    public final Pack value;

    PackType(Pack pack){ this.value = pack; }
}