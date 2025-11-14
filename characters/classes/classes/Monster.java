package project.rpg.characters.classes.classes;

import project.rpg.characters.Player;
import project.rpg.characters.classes.jobs.Job;
import project.rpg.characters.classes.jobs.monstersjobs.MonsterJob;

public class Monster extends Player{
    private int baseExp;

    public Monster(String name, Job job, int xpBase) {
        super(name, job);
        this.baseExp = xpBase;
    }
    
    public MonsterJob getMonsterJob(){
        return (MonsterJob) getJob();
    }

    public int getBaseExp(){
        return baseExp;
    }
    public void setBaseExp(int baseExp){
        this.baseExp = baseExp;
    }
}
