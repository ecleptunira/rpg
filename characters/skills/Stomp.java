package project.rpg.characters.skills;

import project.rpg.characters.Character;
import project.rpg.combat.DamageCalculator;
import project.rpg.combat.DamageType;

public class Stomp extends Skill {

    public Stomp(String name, String description) {
        super(name, description);
    }

    public String getName() {
        return "Stomp";
    }

    @Override
    public void execute(Character attacker, Character defensor) {
        DamageCalculator.calculateAndApplyDamage(
            attacker, 
            defensor, 
            0.9, 
            0.3, 
            getName(), 
            DamageType.PHYSICAL);
    }
}
