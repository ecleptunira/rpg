package project.rpg.characters.classes.jobs;

import project.rpg.characters.Character;
import project.rpg.characters.skills.*;

public class MageJob extends Job{

    public MageJob() {
        this.name = "Mage";
        skills.add(new MagicBolt());
        skills.add(new Heal());
        skills.add(new ArcaneBurst());
    }

    @Override
    public void applyClassStats(Character character) {
        character.setLife(80);
        character.setMaxLife(80);

        character.setPhysicalDamage(7);
        character.setMagicalDamage(18);

        character.setPhysicalDefense(5);
        character.setMagicalDefense(10);

        character.setCriticalChance(1);
        character.setCriticalChanceAcumulated(1);
        character.setCriticalDamage(100);

        character.setEvasion(5);
        character.setAccuracy(10);
    }

    @Override
    public void applyLevelStats(Character c) {
        if (c.getLevel() > 30){
            c.heal((int) (c.getMaxLife() * 0.2));
            return;
        }

        //life
        c.setMaxLife(c.getMaxLife() + 10);
        c.heal((int) (c.getMaxLife() * 0.2));

        //defense
        c.setPhysicalDefense(c.getPhysicalDefense() + 1);
        c.setMagicalDefense(c.getMagicalDefense() + 2);

        //damage
        c.setMagicalDamage(c.getMagicalDamage() + 4);

        //accuracy
        c.setAccuracy(c.getAccuracy() + 1);

    }

}
