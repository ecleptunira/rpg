package project.rpg.characters.classes.jobs.monstersjobs;

import project.rpg.characters.classes.jobs.Job;
import project.rpg.characters.skills.BasicAttack;


public abstract class MonsterJob extends Job {
    protected int baseExp = 1;

    public MonsterJob(String name, int baseXp) {
        this.name = name;
        this.baseExp = baseXp;
        skills.add(new BasicAttack("Basick Attack", "Simple physical attack"));
    }
    
    public int getBaseExp(){
        return baseExp;
    }
    public void setBaseExp(int baseExp){
        this.baseExp = baseExp;
    }

}