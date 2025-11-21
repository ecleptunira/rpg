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
        character.setPhysicalDamage(20);
        character.setMagicalDamage(5);

        character.setMaxLife(150);
        character.setLife(150);

        character.setPhysicalDefense(15);
        character.setMagicalDefense(10);

        character.setCriticalChance(10);
        character.setCriticalChanceAcumulated(10);
        character.setCriticalDamage(50);

        character.setEvasion(5);
        character.setAccuracy(10);
    }

    @Override
    public void applyLevelStats(Character c) {
        c.setPhysicalDamage(c.getPhysicalDamage() + 4);
        c.setMagicalDamage(c.getMagicalDamage() + 1);

        c.setMaxLife(c.getMaxLife() + (25));
        c.setLife(c.getLife() + (int) (c.getMaxLife() * 0.2));
        if (c.getLife() > c.getMaxLife()){
            c.setLife(c.getMaxLife());
        }

        c.setPhysicalDefense(c.getPhysicalDefense() + 3);
        c.setMagicalDefense(c.getMagicalDefense() + 2);
    }
}