package project.rpg.characters.classes.jobs.monstersjobs;

import project.rpg.characters.Character;
import project.rpg.characters.classes.jobs.Job;
import project.rpg.characters.skills.BasicAttack;


public abstract class MonsterJob extends Job {
    public int baseExp = 1;

    public MonsterJob() {
        skills.add(new BasicAttack("Basick Attack", "Simple physical attack"));
    }

    @Override
    public void applyClassStats(Character character) {
        character.setPhysicalDamage(10);

        character.setPhysicalDefense(10);
        character.setMagicDefense(10);

        character.setMaxLife(100);
        character.setLife(100);

        character.setCriticalChance(10);
        character.setCriticalChanceAcumulated(10);
        character.setCriticalDamage(50);

        character.setEvasion(5);
        character.setAccuracy(15);
    }
    
    public int getBaseExp(){
        return baseExp;
    }
    public void setBaseExp(int baseExp){
        this.baseExp = baseExp;
    }

}