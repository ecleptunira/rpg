package project.rpg.characters.skills;

import project.rpg.characters.Character;
import project.rpg.combat.DamageCalculator;
import project.rpg.combat.DamageType;

public class FireBall extends Skill{

    public FireBall(String name, String description) {
        super(name, description);
    }

    public String getName(){
        return "FireBall";
    }

    @Override
    public void execute(Character attacker, Character defensor){
        DamageCalculator.calculateAndApplyDamage(
            attacker, 
            defensor, 
            1.5, 
            0.5, 
            getName(), 
            DamageType.MAGICAL);
    }
}
