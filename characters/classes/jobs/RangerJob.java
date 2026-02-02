package project.rpg.characters.classes.jobs;

import project.rpg.characters.Character;

public class RangerJob extends Job{

    public RangerJob() {
        this.name = "Ranger";
    }

    @Override
    public void applyClassStats(Character character) {
        
        character.setLife(85);
        character.setMaxLife(85);

        character.setPhysicalDamage(20);
        character.setMagicalDamage(18);

        character.setPhysicalDefense(10);
        character.setMagicalDefense(6);

        character.setCriticalChance(20);
        character.setCriticalChanceAcumulated(20);
        character.setCriticalDamage(60);

        character.setEvasion(15);
        character.setAccuracy(15);
    }

    @Override
    public void applyLevelStats(Character c) {
        if (c.getLevel() > 30){
            c.heal((int) (c.getMaxLife() * 0.2));
            return;
        }

        //life
        c.setMaxLife(c.getMaxLife() + 13);
        c.heal((int) (c.getMaxLife() * 0.2));

        //defense
        if (c.getLevel() % 2 == 0){
            c.setPhysicalDefense(c.getPhysicalDefense() + 1);
            c.setMagicalDefense(c.getMagicalDefense() + 1);
        }

        if (c.getLevel() % 2 == 1){ //odd
            //damage
            c.setPhysicalDamage(c.getPhysicalDamage() + 2);

            //evasion
            c.setEvasion(c.getEvasion() + 2);

            //accuracy
            c.setAccuracy(c.getAccuracy() + 2);
        } else { //even
            //damage
            c.setPhysicalDamage(c.getPhysicalDamage() + 3);

            //evasion
            c.setEvasion(c.getEvasion() + 3);

            //accuracy
            c.setAccuracy(c.getAccuracy() + 3);
        }
    }

}
