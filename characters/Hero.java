package project.rpg.characters;

import project.rpg.combat.DamageCalculator;
import project.rpg.combat.DamageType;
import project.rpg.utils.Information;

public class Hero extends Player {

    String hability1 = "Divine Sword";

    public Hero(String name, int level, int damage, 
                int physicalDefense, int magicDefense, 
                int maxLife, int posX, int posY,
                String ocupation) {
        super(name, level, damage, physicalDefense, magicDefense, maxLife, posX, posY, ocupation);
    }

    @Override
    public void attack(Character enemy) {
        if (!canAttack(enemy)) {
            Information.outOfRange(this, enemy);
            return;
        }
        DamageCalculator.calculateAndApplyDamage(
            this, 
            enemy, 
            0.95, 
            0.15, 
            "attack",
            DamageType.PHYSICAL);
    }

    public void divineSword(Character enemy) {
        if (!canAttack(enemy)) {
            Information.outOfRange(this, enemy);
            return;
        }
        DamageCalculator.calculateAndApplyDamage(
            this, 
            enemy, 
            1.0, 
            0.25,  
            hability1,
            DamageType.PHYSICAL);
    }
}