package project.rpg.characters.skills;

import project.rpg.characters.Character;
import project.rpg.combat.DamageCalculator;
import project.rpg.combat.DamageType;

public class ArcaneBurst extends Skill {

    public ArcaneBurst(String name, String description) {
        super(name, description);
    }
    
    public String getName() {
        return "Magic Bolt";
    }

    @Override
    public void execute(Character attacker, Character defensor) {
        for (int i = 0; i < 3; i++){
            DamageCalculator.calculateAndApplyDamage(
                attacker, 
                defensor, 
                1.2, 
                0.4, 
                getName(), 
                DamageType.MAGICAL);
        }
    }
}
