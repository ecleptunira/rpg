package project.rpg.characters.skills;

import project.rpg.characters.Character;
import project.rpg.utils.Information;

public class Heal extends Skill{

    public Heal(String name, String description) {
        super(name, description);
    }
    
    public String getName() {
        return "Heal";
    }

    @Override
    public void execute(Character healer, Character target) {
        double healAmount = healer.getMagicalDamage() * 0.4 + 15;
        Information.heal(target, (int) target.heal((int) healAmount));
    }
}
