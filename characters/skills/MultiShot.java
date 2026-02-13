package project.rpg.characters.skills;

import project.rpg.utils.Information;
import project.rpg.characters.Character;
import project.rpg.combat.*;

public class MultiShot extends Skill{

    private static final String NAME = "Multi-Shot";
    private static final String DESCRIPTION = "Shoots 3 arrows at once";

    public MultiShot() {
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
        int damageDealt = 0;
        int damageDealtPreMitigation = 0;
        DamageResult result = null;

        for (int i = 0; i < 3; i++){
            result = DamageCalculator.calculateDamage(attacker, defensor, 0.6, 0.2, DamageType.PHYSICAL);
            damageDealt += result.damageDealt();
            damageDealtPreMitigation += result.baseDamage() + result.bonusDamage();
            
            if (result.isCritical() && result.willHit()){
                Information.criticalHit(attacker, getName(), result.damageDealt(), defensor);
            } else if (result.willHit()){
                Information.damageStatus(attacker, getName(), result.damageDealt(), defensor);
            }
            if (result.willHit()){
                defensor.takeDamage(result.damageDealt());
                Information.showHealth(defensor);
            }
        }
        Information.combatLogMultHit(attacker, defensor, result, damageDealt, damageDealtPreMitigation);
    }
}
