package project.rpg.characters.classes.jobs.monstersjobs;

import project.rpg.characters.Character;
import project.rpg.characters.skills.Stomp;


public class HighGoblinJob extends MonsterJob{

    public HighGoblinJob() {
        super("High Goblin");
        skills.add(new Stomp());

    }

    @Override
    public void applyClassStats(Character character) {
        character.setPhysicalDamage(35);

        character.setPhysicalDefense(35);
        character.setMagicalDefense(25);

        character.setMaxLife(350);
        character.setLife(350);

        character.setCriticalChance(15);
        character.setCriticalChanceAcumulated(15);
        character.setCriticalDamage(50);

        character.setEvasion(10);
        character.setAccuracy(15);
    }

    @Override
    public void applyLevelStats(Character c) {
        c.setMaxLife(c.getMaxLife() * c.getLevel());
        c.setLife(c.getMaxLife());

        c.setPhysicalDamage(c.getPhysicalDamage() * (int) Math.round(1 + c.getLevel() * 0.08));
        c.setMagicalDefense(c.getMagicalDefense() * (int) Math.round(1 + c.getLevel() * 0.05));
        c.setPhysicalDefense(c.getPhysicalDefense() * (int) Math.round(1 + c.getLevel() * 0.04));
    }
}
