package project.rpg.characters.classes.jobs;

import project.rpg.characters.Character;
import project.rpg.characters.skills.BasicAttack;
import project.rpg.characters.skills.Stomp;

public class MonsterJob extends Job {

    public MonsterJob() {
        skills.add(new BasicAttack("Basick Attack", "Simple physical attack"));
        skills.add(new Stomp("Stomp", "A heavy stomp that shakes the ground."));
    }

    @Override
    public void applyClassStats(Character character) {
        character.setPhysicalDamage(30);

        character.setPhysicalDefense(25);
        character.setMagicDefense(15);

        character.setMaxLife(250);
        character.setLife(250);

        character.setCriticalChance(10);
        character.setCriticalChanceAcumulated(10);
        character.setCriticalDamage(50);

        character.setEvasion(5);
        character.setAccuracy(15);

        // character.setBlock(15);
        // character.setBlockReduction(15);
        // character.setParry(10);
        // character.setParryReduction(10);
        // character.setParryCounterChance(20);
    }

}