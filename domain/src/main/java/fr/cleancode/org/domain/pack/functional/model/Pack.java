package fr.cleancode.org.domain.pack.functional.model;

import lombok.Data;

@Data
public abstract class Pack {
    int cost;
    int cardsQuantity;
    int[] proba;
}
