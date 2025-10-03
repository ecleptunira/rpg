package project.rpg.characters.classes.jobs;

import project.rpg.characters.Character;
import project.rpg.characters.skills.BasicAttack;
import project.rpg.characters.skills.BackStab;

public class AssasinJob extends Job {

    public AssasinJob(){
        skills.add(new BasicAttack("Basick Attack", "Simple physical attack"));
        skills.add(new BackStab("BackStab", "A powerful attack from the shadows"));
    }

    @Override
    public void applyClassStats(Character character) {
        character.setPhysicalDamage(45);

        character.setLife(100);
        character.setMaxLife(100);

        character.setCriticalChance(25);
        character.setCriticalDamage(100);

        character.setEvasion(25);
        character.setAccuracy(20);

        character.setParry(15);
        character.setParryReduction(20);
        character.setParryCounterChance(50);
    }

}
