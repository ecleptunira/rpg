package project.rpg.characters;

import project.rpg.combat.DamageCalculator;
import project.rpg.combat.DamageType;
import project.rpg.utils.Information;

public class Player extends Character{
    
    public Player(String name, int level, int damage, 
                  int physicalDefense, int magicDefense, 
                  int maxLife, int posX, int posY,
                  String ocupation) {
        super(name, level, damage, physicalDefense, magicDefense, maxLife, posX, posY);
        this.setOcupation(ocupation);
    }

    public void setOcupation(String ocupation){

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
            0.75, 
            0.25, 
            "attack",
            DamageType.PHYSICAL);
    }



}
