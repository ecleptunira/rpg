package project.rpg.combat;

import project.rpg.characters.Character;

public record DamageResult (
    boolean willHit,
    DamageType damageType,
    
    int attackDamage,
    int baseDamage,
    int bonusDamage,
    int variableDamage,
    
    double levelDifference,
    int damageDealt,
    int damageReduction,
    int criticalPercentage,
    boolean isCritical
){

    public static DamageResult missResult(Character attacker, DamageType type){
        return new DamageResult(false, type, 0, 0, 0, 0, 0, 0, 0, attacker.getCriticalChanceAcumulated(), false);
    }
}