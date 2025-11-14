package project.rpg.characters.classes.jobs.monstersjobs;

import project.rpg.characters.Character;
import project.rpg.characters.skills.Stomp;


public class HighGoblinJob extends MonsterJob{

    public HighGoblinJob() {
        super("High Goblin");
        skills.add(new Stomp("Stomp", "A heavy stomp that shakes the ground."));

    }

    @Override
    public void applyClassStats(Character character) {
        character.setPhysicalDamage(45);

        character.setPhysicalDefense(35);
        character.setMagicDefense(20);

        character.setMaxLife(350);
        character.setLife(350);

        character.setCriticalChance(15);
        character.setCriticalChanceAcumulated(15);
        character.setCriticalDamage(50);

        character.setEvasion(10);
        character.setAccuracy(15);
    }
}
