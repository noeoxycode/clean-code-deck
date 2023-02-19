package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.player.functional.exception.PlayerException;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.functional.service.validation.PlayerValidator;
import fr.cleancode.org.domain.player.ports.client.PlayerFinderApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FightService {
    @Override
    public FightService fight(Player player) {
    }