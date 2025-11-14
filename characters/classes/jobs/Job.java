package project.rpg.characters.classes.jobs;

import java.util.List;
import java.util.ArrayList;

import project.rpg.characters.Character;
import project.rpg.characters.skills.Skill;
import project.rpg.characters.classes.classes.Monster;

public abstract class Job {
    protected String name;
    protected List<Skill> skills = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public abstract void applyClassStats(Character character);

    public abstract void applyLevelStats(Character character);

    public void useSkill(String skillName, Character user, Monster target) {
        for (Skill skill : skills) {
            if (skill.getName().equalsIgnoreCase(skillName)) {
                skill.execute(user, target);
                return;
            }
        }
        System.out.println("Skill " + skillName + " não encontrada para " + name);
    }

}
