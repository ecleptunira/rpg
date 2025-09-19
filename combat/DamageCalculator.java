package project.rpg.combat;

import project.rpg.characters.Character;
import project.rpg.utils.Information;

public class DamageCalculator {

    public static boolean isCritical(Character attacker){
        // Determine if the attack is a critical and if is not, acumulate the critical chance
        double chance = attacker.getCriticalChanceAcumulated();
        if (Math.random() * 100 < chance) {
            attacker.setCriticalChanceAcumulated(attacker.getCriticalChance());
            return true;
        } else {
            attacker.setCriticalChanceAcumulated((int)chance + attacker.getCriticalChance());
            return false;
        }
    }

    public static int applyCritical(Character attacker, int damageDealt){
        // calculate critical damage and apply it
        damageDealt = (int) (damageDealt * (1 + (attacker.getCriticalDamage() / 100.0)));
        return damageDealt;
    }

    public static double levelDiference(Character attacker, Character defender){
        int levelDifference = attacker.getLevel() - defender.getLevel();
        
        if (Math.abs(levelDifference) <= 5) {
            return 1.0;
        }
        // Cap the level difference at 15
        if (levelDifference > 15) {
            levelDifference = 15;
        } else if (levelDifference < -15) {
            levelDifference = -15;
        }
        //applying a factor of 10% per level difference beyond level 5.
        double factor = 1.0 + ((levelDifference-5) * 0.1);
        
        // Add caps to the factor to avoid extreme values
        if (factor > 2.0) {
            factor = 2.0;
        } else if (factor < 0.5) {
            factor = 0.5;
        }
        return factor;
    }

    /*
     * DEFENSE_SCALE determines the effectiveness of the defense in reducing damage taken.
     * The higher the value, the less effective the defense.
     * Example:
     * Damage = 100 and DEFENSE_SCALE = 10
     * Defense 00 --- 100/(1+00/10)  = 100/1   = 100 damage taken
     * Defense 05 --- 100/(1+05/10)  = 100/1.5 = 66  damage taken
     * Defense 10 --- 100/(1+10/10) = 100/2   = 50  damage taken
     * Defense 20 --- 100/(1+20/10) = 100/3   = 33  damage taken
     */
    private static final double DEFENSE_SCALE = 10.0;

    public static int applyDefense(int damage, int defense){
        return (int) (damage / (1 + (defense / DEFENSE_SCALE)));
    }

    public static boolean willHit(Character attacker, Character defender){
        double hitChance =  defender.getEvasion() - attacker.getAccuracy();
        if (hitChance < 5 ) hitChance = 5; // Minimum hit chance of 5%
        if (hitChance > 95) hitChance = 95; // Maximum hit chance of 95%
        double randomValue = Math.random() * 100;
        return  randomValue < hitChance;
    }

    public static void calculateAndApplyDamage( Character attacker, Character defensor, 
                                                double baseDamageFactor, double bonusDamageFactor, 
                                                String hability, DamageType damageType) {
        // check if is a miss
        if (willHit(attacker, defensor)) {
            Information.damageMissed(attacker, hability, defensor);
            return;
        }
        // calculate the damage
        int baseDamage = (int) (attacker.getDamage() * baseDamageFactor);
        int bonusDamage = (int) (attacker.getDamage() * bonusDamageFactor);
        int variableDamage = (int) (Math.random() * (bonusDamage + 1));
        int damageDealt = (int) ((baseDamage + variableDamage) * levelDiference(attacker, defensor));

        // aplly the defense based on the damage type
        if (damageType == DamageType.PHYSICAL){
            damageDealt = applyDefense(damageDealt, defensor.getPhysicalDefense());
        } else if (damageType == DamageType.MAGICAL){
            damageDealt = applyDefense(damageDealt, defensor.getMagicDefense());
        }

        // check if is a critical hit, and if it is, apply critical damage
        if (isCritical(attacker)){
            damageDealt = applyCritical(attacker, damageDealt);
            Information.criticalHit(attacker, hability, damageDealt, defensor);
        }else {
            Information.damageStatus(attacker, hability, damageDealt, defensor);
        }
        // aaply the damage on the defensor and show his health
        defensor.takeDamage(damageDealt);
        Information.showHealth(defensor);
    }
}
