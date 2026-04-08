package project.rpg.characters.classes.jobs.monsters_jobs;

import project.rpg.characters.Character;
import project.rpg.characters.skills.Stomp;
import project.rpg.characters.skills.BasicAttack;


public class HighGoblinJob extends MonsterJob{

    public HighGoblinJob() {
        super("High Goblin");
        skills.add(new Stomp());
        skills.add(new BasicAttack());
    }

    @Override
    public void applyClassStats(Character character) {
        character.setPhysicalDamage(25);

        character.setPhysicalDefense(35);
        character.setMagicalDefense(25);

        character.setMaxLife(70);
        character.setLife(70);

        character.setCriticalChance(15);
        character.setCriticalChanceAcumulated(15);
        character.setCriticalDamage(50);

        character.setEvasion(10);
        character.setAccuracy(15);
    }

    @Override
    public void applyLevelStats(Character c) {
        c.setMaxLife(c.getMaxLife() + 50);
        c.setLife(c.getMaxLife());
        double multiplier = (1 + c.getLevel() * 0.05);
        c.setPhysicalDamage((int) (c.getPhysicalDamage() * multiplier));
        c.setMagicalDefense((int) (c.getMagicalDefense() * multiplier));
        c.setPhysicalDefense((int) (c.getPhysicalDefense() * multiplier));
    }
}
