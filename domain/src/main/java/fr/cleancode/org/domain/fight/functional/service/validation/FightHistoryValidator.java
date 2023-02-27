package fr.cleancode.org.domain.fight.functional.service.validation;

import fr.cleancode.org.domain.fight.functional.model.Fight;

import java.util.List;

public interface FightHistoryValidator {

    default boolean validate(List<Fight> fightHistory) {
        return fightHistory.size() > 0;
    }

}
