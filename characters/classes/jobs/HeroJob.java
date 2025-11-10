package project.rpg.characters.classes.jobs;

import project.rpg.characters.Character;
import project.rpg.characters.skills.*;

public class HeroJob extends Job {

    public HeroJob() {
        this.name = "Hero";
        skills.add(new BasicAttack("Basick Attack", "Simple physical attack"));
        skills.add(new DivineSword("Divine Sword", "A powerful divine slash."));
        skills.add(new FireBall("FireBall", "A ball of fire that burns the enemy."));
    }

    @Override
    public void applyClassStats(Character character) {
        character.setPhysicalDamage(40);
        character.setMagicalDamage(20);

        character.setMaxLife(200);
        character.setLife(200);

        character.setPhysicalDefense(15);
        character.setMagicDefense(10);

        character.setCriticalChance(10);
        character.setCriticalChanceAcumulated(10);
        character.setCriticalDamage(50);

        character.setEvasion(10);
        character.setAccuracy(15);

        // character.setBlock(10);
        // character.setBlockReduction(25);
        // character.setParry(20);
        // character.setParryReduction(20);
        // character.setParryCounterChance(30);
    }
    
}