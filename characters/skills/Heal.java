package project.rpg.characters.skills;

import project.rpg.characters.Character;
import project.rpg.utils.Information;

public class Heal extends Skill{

    private static final String NAME = "Heal";
    private static final String DESCRIPTION = "Restore a portion of life";

    public Heal() {
        super(NAME, DESCRIPTION);
    }
    
    public String getName() {
        return NAME;
    }

    public String getDescription(){
        return DESCRIPTION;
    }

    @Override
    public void execute(Character healer, Character target) {
        double healAmount = healer.getMagicalDamage() * 0.4 + 15;
        Information.heal(target, (int) target.heal((int) healAmount));
    }
}
