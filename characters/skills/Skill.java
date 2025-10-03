package project.rpg.characters.skills;

import project.rpg.characters.Character;

public class Skill{
    private String name;
    private String description;

    public Skill(String name, String description){
        this.name = name;
        this.description = description;
    }

    public void execute(Character attacker, Character defensor) {
        System.out.println(attacker.getName() + " usa " + name + " em " + defensor.getName());
    }

    public String toString (){
        return name + ": " + description;
    }

    public String getName(){return name;};
    public String getDescription(){return description;};
}
