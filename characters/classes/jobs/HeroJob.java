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
    }

    @Override
    public void applyLevelStats(Character c) {
        c.setPhysicalDamage(c.getPhysicalDamage() + 2);
        c.setMagicalDamage(c.getMagicalDamage() + 1);

        c.setMaxLife(c.getMaxLife() + (15));
        c.setLife(c.getLife() + (int) (c.getMaxLife() * 0.2));

        c.setPhysicalDefense(c.getPhysicalDefense() + 1);
        c.setMagicDefense(c.getMagicDefense() + 1);
    }
}