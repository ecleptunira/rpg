package project.rpg.characters.skills;

import project.rpg.characters.Character;
import project.rpg.combat.DamageCalculator;
import project.rpg.combat.DamageType;

public class BasicArrow extends Skill {

    public BasicArrow(String name, String description) {
        super(name, description);
    }

    public String getName() {
        return "Basic Arrow";
    }

    @Override
    public void execute(Character attacker, Character defensor) {
        DamageCalculator.calculateAndApplyDamage(
            attacker, 
            defensor, 
            0.8,
            0.2,
            getName(), 
            DamageType.PHYSICAL);
    }
}
