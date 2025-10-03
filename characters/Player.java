package project.rpg.characters;

import project.rpg.characters.classes.jobs.Job;

/**
 * Represents a controllable player character in the game.
 * 
 * A Player has:
 * - A name (given at creation).
 * - A Job (which defines stats and skills).
 * - All attributes inherited from Character.
 * 
 * This class connects the abstract Character with the concrete Job
 * so that players can have unique roles (Assassin, Warrior, Mage, etc.).
 */
public class Player extends Character{
    private Job job;

    /**
     * Creates a new Player with a given name and job.
     * 
     * @param name the player's name
     * @param job the player's chosen Job (class template with stats and skills)
     */
    public Player(String name, Job job) {
        super(name);
        this.job = job;
        //applying job stats to the character
        job.applyClassStats(this);
    }

    public Job getJob(){
        return job;
    }

    /**
     * Executes a skill from the player's current job.
     * 
     * @param skillName the name of the skill to use
     * @param target the target Character who will receive the skill
     */
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
