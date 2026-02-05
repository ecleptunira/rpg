package project.rpg.characters.skills;

import project.rpg.characters.Character;
import project.rpg.combat.DamageCalculator;
import project.rpg.combat.DamageType;

public class MagicBolt extends Skill {

    public MagicBolt(String name, String description) {
        super(name, description);
    }
    
    public String getName() {
        return "Magic Bolt";
    }

    @Override
    public void execute(Character attacker, Character defensor) {
        DamageCalculator.calculateAndApplyDamage(
            attacker, 
            defensor, 
            1.2, 
            0.2, 
            getName(), 
            DamageType.MAGICAL);
    }
}
