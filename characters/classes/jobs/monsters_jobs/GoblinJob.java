package project.rpg.characters.classes.jobs.monsters_jobs;

import project.rpg.characters.Character;
import project.rpg.characters.skills.Stomp;
import project.rpg.characters.skills.BasicAttack;


public class GoblinJob extends MonsterJob{

    public GoblinJob() {
        super("Goblin");
        skills.add(new Stomp());
        skills.add(new BasicAttack());

    }

    @Override
    public void applyClassStats(Character character) {
        character.setPhysicalDamage(20);

        character.setPhysicalDefense(25);
        character.setMagicalDefense(15);

        character.setMaxLife(40);
        character.setLife(40);

        character.setCriticalChance(10);
        character.setCriticalChanceAcumulated(10);
        character.setCriticalDamage(50);

        character.setEvasion(10);
        character.setAccuracy(15);
    }

    @Override
    public void applyLevelStats(Character c) {
        c.setMaxLife(c.getMaxLife() + 25);
        c.setLife(c.getMaxLife());

        double multiplier = (1 + c.getLevel() * 0.05);
        c.setPhysicalDamage((int) (c.getPhysicalDamage() * multiplier));
        c.setMagicalDefense((int) (c.getMagicalDefense() * multiplier));
        c.setPhysicalDefense((int) (c.getPhysicalDefense() * multiplier));
    }
}
