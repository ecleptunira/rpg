package project.rpg.characters.classes.jobs.monsters_jobs;

import project.rpg.characters.classes.jobs.Job;
import project.rpg.characters.skills.BasicAttack;


public abstract class MonsterJob extends Job {
    public MonsterJob(String name) {
        this.name = name;
        skills.add(new BasicAttack());
    }

}