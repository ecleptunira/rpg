package project.rpg.characters.skills;

import project.rpg.characters.Character;
import project.rpg.combat.DamageCalculator;
import project.rpg.combat.DamageType;

public class DivineSword extends Skill {

    public DivineSword(String name, String description) {
        super(name, description);
    }

    public String getName() {
        return "Divine Sword";
    }

    @Override
    public void execute(Character attacker, Character defensor) {
        DamageCalculator.calculateAndApplyDamage(
            attacker, 
            defensor, 
            1.0, 
            0.5, 
            getName(), 
            DamageType.PHYSICAL);
    }
}
