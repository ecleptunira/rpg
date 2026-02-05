package project.rpg.characters.skills;

import project.rpg.characters.Character;
import project.rpg.combat.DamageCalculator;
import project.rpg.combat.DamageType;

public class MultiShot extends Skill{

    public MultiShot(String name, String description) {
        super(name, description);
    }
    
    public String getName() {
        return "Multi Shot";
    }

    @Override
    public void execute(Character attacker, Character defensor) {
        for (int i = 0; i < 3; i++){
            DamageCalculator.calculateAndApplyDamage(
                attacker, 
                defensor, 
                0.6, 
                0.2, 
                getName(), 
                DamageType.PHYSICAL);
        }
    }
}
