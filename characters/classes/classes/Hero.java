package project.rpg.characters.classes.classes;

import project.rpg.characters.Player;
import project.rpg.characters.classes.jobs.Job;
import project.rpg.characters.Character;
import project.rpg.combat.DamageCalculator;
import project.rpg.combat.DamageType;

public class Hero extends Player{

    public Hero(String name, Job job) {
        super(name, job);
    }

    public void attack(Character enemy) {
        DamageCalculator.calculateAndApplyDamage(
            this, 
            enemy, 
            0.95, 
            0.15, 
            "attack",
            DamageType.PHYSICAL);
    }

    public void divineSword(Character enemy) {
        DamageCalculator.calculateAndApplyDamage(
            this, 
            enemy, 
            1.0, 
            0.25,  
            "Divine Sword",
            DamageType.PHYSICAL);
    }

}
