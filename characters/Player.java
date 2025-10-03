package project.rpg.characters;

import project.rpg.characters.classes.jobs.Job;

public class Player extends Character{
    private Job job;
    
    public Player(String name, Job job) {
        super(name);
        this.job = job;
        job.applyClassStats(this);
    }

    public Job getJob(){
        return job;
    }

    public void useSkill(String skillName, Character target){
        if (job != null){
            job.useSkill(skillName, this, target);
        }
    }

    public String toString() {
        return super.toString() + "\nClasse: " + job.getName() + 
               "\nSkills: " + job.getSkills();
    }
}
