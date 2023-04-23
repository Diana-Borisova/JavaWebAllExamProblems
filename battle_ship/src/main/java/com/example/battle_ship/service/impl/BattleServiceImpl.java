package com.example.battle_ship.service.impl;

import com.example.battle_ship.model.Dtos.StartBattleDto;
import com.example.battle_ship.model.entity.Ship;
import com.example.battle_ship.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class BattleServiceImpl {

        private final ShipRepository shipRepository;

        public BattleServiceImpl(ShipRepository shipRepository) {
            this.shipRepository = shipRepository;
        }

        public void attack(StartBattleDto attackData) throws Exception {
            Optional<Ship> attackerOpt = this.shipRepository.findById((long) attackData.getAttackerId());
            Optional<Ship> defenderOpt = this.shipRepository.findById((long) attackData.getDefenderId());

            if (attackerOpt.isEmpty() || defenderOpt.isEmpty()) {
                throw new Exception();
            }

            Ship attacker = attackerOpt.get();
            Ship defender = defenderOpt.get();

            long newDefenderHealth = defender.getHealth() - attacker.getPower();

            if (newDefenderHealth <= 0) {
                this.shipRepository.delete(defender);
            } else {
                defender.setHealth(newDefenderHealth);
                this.shipRepository.save(defender);
            }
        }
    }

