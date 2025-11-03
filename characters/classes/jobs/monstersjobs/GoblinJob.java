package project.rpg.characters.classes.jobs.monstersjobs;

import project.rpg.characters.Character;
import project.rpg.characters.skills.BasicAttack;
import project.rpg.characters.skills.Stomp;
import project.rpg.characters.classes.jobs.monstersjobs.*;

public class GoblinJob extends MonsterJob{

    public GoblinJob() {
        this.name = "Goblin";
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

        //monstersjobs.setBaseExp(5);
    }

}
