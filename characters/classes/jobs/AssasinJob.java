package project.rpg.characters.classes.jobs;

import project.rpg.characters.Character;
import project.rpg.characters.skills.*;

public class AssasinJob extends Job {

    public AssasinJob(){
        this.name = "Assassin";
        skills.add(new BasicAttack("Basick Attack", "Simple physical attack"));
        skills.add(new BackStab("BackStab", "A powerful attack from the shadows"));
    }

    @Override
    public void applyClassStats(Character character) {
        character.setLife(90);
        character.setMaxLife(90);

        character.setPhysicalDamage(28);
        character.setMagicalDamage(10);

        character.setPhysicalDefense(6);
        character.setMagicalDefense(6);

        character.setCriticalChance(25);
        character.setCriticalChanceAcumulated(25);
        character.setCriticalDamage(75);

        character.setEvasion(13);
        character.setAccuracy(15);
    }

    @Override
    public void applyLevelStats(Character c) {
        if (c.getLevel() > 30){
            c.heal((int) (c.getMaxLife() * 0.2));
        }

        //Life
        c.setMaxLife(c.getMaxLife() + (15));

        //heal
        c.heal((int) (c.getMaxLife() * 0.2));

        //Damage
        c.setPhysicalDamage(c.getPhysicalDamage() + 2);

        //Defense
        c.setPhysicalDefense(c.getPhysicalDefense() + 1);
        if ((c.getLevel()) % 3 == 0){
            c.setMagicalDefense(c.getMagicalDefense() + 1);
        }

        //Critical
        if (c.getCriticalChance() < 50){
            c.setCriticalChance(c.getCriticalChance() + 1);
            c.setCriticalChanceAcumulated(c.getCriticalChance());
        } else if (c.getLevel() == 30) {
            c.setPhysicalDamage(c.getPhysicalDamage() + 5);
        }
        
        //Evasion
        c.setEvasion(c.getEvasion() + 2);

        //Accuracy
        c.setAccuracy(c.getAccuracy() + 2);
    }
}
