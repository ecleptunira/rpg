package project.rpg.characters.skills;

import project.rpg.characters.Character;
import project.rpg.combat.DamageCalculator;
import project.rpg.combat.DamageType;

public class BackStab extends Skill{

    public BackStab(String name, String description) {
        super(name, description);
    }

    public String getName() {
        return "BackStab";
    }

    @Override
    public void execute(Character attacker, Character defensor) {
        DamageCalculator.calculateAndApplyDamage(
            attacker, 
            defensor, 
            1.3, 
            0.7, 
            getName(), 
            DamageType.PHYSICAL);
    }
}
