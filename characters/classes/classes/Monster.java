package project.rpg.characters.classes.classes;

import project.rpg.characters.Player;
import project.rpg.characters.classes.jobs.Job;
import project.rpg.combat.DamageCalculator;
import project.rpg.combat.DamageType;
import project.rpg.characters.Character;
import project.rpg.utils.Information;

public class Monster extends Player{

    public Monster(String name, Job job) {
        super(name, job);
    }
    
    public void attack(Character enemy) {
        if (!canAttack(enemy)) {
            Information.outOfRange(this, enemy);
            return;
        }
        DamageCalculator.calculateAndApplyDamage(
            this, 
            enemy, 
            0.85,
            0.45,
            "Attack",
            DamageType.PHYSICAL);
    }

    public void stomp(Character enemy) {
        if (!canAttack(enemy)) {
            Information.outOfRange(this, enemy);
            return;
        }
        DamageCalculator.calculateAndApplyDamage(
            this, 
            enemy, 
            1, 
            0.5, 
            "Stomp",
            DamageType.PHYSICAL);
    }

}
