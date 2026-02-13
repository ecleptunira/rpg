package project.rpg.characters.skills;

import project.rpg.characters.Character;
import project.rpg.combat.*;
import project.rpg.utils.Information;

public class BasicArrow extends Skill {

    private static final String NAME = "Basic Arrow";
    private static final String DESCRIPTION = "A simples arrow shot";

    public BasicArrow() {
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
        DamageResult result = DamageCalculator.calculateDamage(attacker, defensor, 0.8,0.2,DamageType.PHYSICAL);

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
