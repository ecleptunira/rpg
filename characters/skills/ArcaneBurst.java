package project.rpg.characters.skills;

import project.rpg.characters.Character;
import project.rpg.combat.*;
import project.rpg.utils.Information;

public class ArcaneBurst extends Skill {

//"Arcane Burst", "Unleash a burst of arcane energy"
    private static final String NAME = "Arcane Burst";
    private static final String DESCRIPTION = "Unleash a burst of arcane energy";

    public ArcaneBurst() {
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
            result = DamageCalculator.calculateDamage(attacker, defensor, 1.2, 0.4, DamageType.MAGICAL);
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
