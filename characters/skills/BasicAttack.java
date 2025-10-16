package project.rpg.characters.skills;

import project.rpg.characters.Character;
import project.rpg.combat.DamageCalculator;
import project.rpg.combat.DamageType;

public class BasicAttack extends Skill{

    
    public BasicAttack(String name, String description) {
        super(name, description);
    }

    public String getName() {
        return "Basic Attack";
    }

    @Override
    public void execute(Character attacker, Character defensor) {
        DamageCalculator.calculateAndApplyDamage(
            attacker, 
            defensor, 
            0.75,
            0.25,
            getName(), 
            DamageType.PHYSICAL);
    }

}
