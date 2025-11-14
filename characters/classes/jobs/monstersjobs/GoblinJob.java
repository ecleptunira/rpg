package project.rpg.characters.classes.jobs.monstersjobs;

import project.rpg.characters.Character;
import project.rpg.characters.skills.Stomp;


public class GoblinJob extends MonsterJob{

    public GoblinJob() {
        super("Goblin");
        skills.add(new Stomp("Stomp", "A stomp that breaks the ground."));

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
    }
}
