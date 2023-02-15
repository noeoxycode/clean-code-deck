package fr.cleancode.org.domain.pack.functional.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.With;

@Builder
@Data
class DiamondPack extends Pack{
    @With
    @Default
    int cost = 2;
    @Default
    int cardsQuantity = 5;
    @Default
    int[] proba = {50, 35, 15};
}
