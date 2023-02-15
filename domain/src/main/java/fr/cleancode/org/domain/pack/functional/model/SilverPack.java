package fr.cleancode.org.domain.pack.functional.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.With;

@Builder
@Data
class SilverPack extends Pack {
    @With
    @Default
    int cost = 1;
    @Default
    int cardsQuantity = 3;
    @Default
    int[] proba = {75, 25, 5};
}