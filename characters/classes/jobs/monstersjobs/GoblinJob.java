package project.rpg.characters.classes.jobs.monstersjobs;

import project.rpg.characters.Character;
import project.rpg.characters.skills.Stomp;


public class GoblinJob extends MonsterJob{

    public GoblinJob() {
        super("Goblin");
        skills.add(new Stomp());

    }

    @Override
    public void applyClassStats(Character character) {
        character.setPhysicalDamage(30);

        character.setPhysicalDefense(25);
        character.setMagicalDefense(15);

        character.setMaxLife(250);
        character.setLife(250);

        character.setCriticalChance(10);
        character.setCriticalChanceAcumulated(10);
        character.setCriticalDamage(50);

        character.setEvasion(10);
        character.setAccuracy(15);

        // character.setBaseExp(5);
    }

    @Override
    public void applyLevelStats(Character c) {
        c.setMaxLife(c.getMaxLife() * c.getLevel());
        c.setLife(c.getMaxLife());

        c.setPhysicalDamage(c.getPhysicalDamage() * (int) Math.round(1 + c.getLevel() * 0.05));

        c.setMagicalDefense(c.getMagicalDefense() * (int) Math.round(1 + c.getLevel() * 0.04));
        c.setPhysicalDefense(c.getPhysicalDefense() * (int) Math.round(1 + c.getLevel() * 0.04));
    }
}
