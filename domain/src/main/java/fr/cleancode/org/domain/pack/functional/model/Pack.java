package fr.cleancode.org.domain.pack.functional.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
public class Pack {
    int cost;
    int cardsQuantity;
    @Default
    int[] probability = new int[3];
    PackType packType;
}