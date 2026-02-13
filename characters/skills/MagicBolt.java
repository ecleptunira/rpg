package project.rpg.characters.skills;

import project.rpg.characters.Character;
import project.rpg.combat.*;
import project.rpg.utils.Information;

public class MagicBolt extends Skill {

    private static final String NAME = "Basic magic";
    private static final String DESCRIPTION = "Simple Magical attack";

    public MagicBolt() {
        super(NAME, DESCRIPTION);
    }
    
    public String getName() {
        return NAME;
    }

    public String getDescription(){
        return DESCRIPTION;
    }

    @Override
    public void execute(Character attacker, Character defensor) {
        DamageResult result = DamageCalculator.calculateDamage(attacker, defensor, 1.2, 0.2, DamageType.MAGICAL);

        if (result.isCritical()){
            Information.criticalHit(attacker, getName(), result.damageDealt(), defensor);
        } else if (result.willHit()){
            Information.damageStatus(attacker, getName(), result.damageDealt(), defensor);
        } 
        if(result.willHit()){
            defensor.takeDamage(result.damageDealt());
            Information.showHealth(defensor);
            Information.combatLogSingleHit(attacker, defensor, result);
        }
    }
}
