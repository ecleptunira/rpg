package project.rpg.characters.classes.jobs;

import project.rpg.characters.Character;
import project.rpg.characters.skills.BasicAttack;
import project.rpg.characters.skills.BackStab;

public class AssasinJob extends Job {

    public AssasinJob(){
        this.name = "Assassin";
        skills.add(new BasicAttack("Basick Attack", "Simple physical attack"));
        skills.add(new BackStab("BackStab", "A powerful attack from the shadows"));
    }

    @Override
    public void applyClassStats(Character character) {
        character.setPhysicalDamage(28);
        character.setMagicalDamage(10);

        character.setPhysicalDefense(6);
        character.setMagicalDefense(6);

        character.setLife(90);
        character.setMaxLife(90);

        character.setCriticalChance(25);
        character.setCriticalChanceAcumulated(25);
        character.setCriticalDamage(75);

        character.setEvasion(13);
        character.setAccuracy(15);
    }

    @Override
    public void applyLevelStats(Character c) {
        c.setPhysicalDamage(c.getPhysicalDamage() + 6);

        c.setMaxLife(c.getMaxLife() + (12));
        c.setLife(c.getLife() + (int) (c.getMaxLife() * 0.2));
        if (c.getLife() > c.getMaxLife()){
            c.setLife(c.getMaxLife());
        }

        if (c.getEvasion() <= 95){
            c.setEvasion(c.getEvasion() + 2);
        }

        if (c.getCriticalChance() <= 50){
            c.setCriticalChance(c.getCriticalChance() + 1);
            c.setCriticalChanceAcumulated(c.getCriticalChance());
        } else {
            c.setPhysicalDamage(c.getPhysicalDamage() + 1);
        }
    }

}
