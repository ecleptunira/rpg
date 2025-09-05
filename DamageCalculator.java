package project.rpg;

public class DamageCalculator {

    public static int criticalChance(Character attacker, int damageDealt){
        if (Math.random() * 100 < attacker.getCriticalChanceAcumulated()) {
            damageDealt = (int) (damageDealt * (attacker.getCriticalDamage() / 100.0 + 1));
            System.out.println("CRITICAL HIT!");
            attacker.setCriticalChanceAcumulated(attacker.getCriticalChance());
            return damageDealt;
        } else {
            attacker.setCriticalChanceAcumulated(attacker.getCriticalChanceAcumulated() + attacker.getCriticalChance());
            return damageDealt;
        }
    }

    public static void calculateAndApplyDamage(Character attacker, Character defender, double baseDamageMultiplier, double bonusDamageMultiplier, double missChance, String hability) {
        if (Math.random() <= missChance) {
            Information.damageMissed(attacker, hability, defender);
            return; // No damage on a miss
        }

        int baseDamage = (int) (attacker.getDamage() * baseDamageMultiplier);
        int bonusDamage = (int) (attacker.getDamage() * bonusDamageMultiplier);
        int variableDamage = (int) (Math.random() * (bonusDamage + 1));
        int damageDealt = baseDamage + variableDamage;

        damageDealt = criticalChance(attacker, damageDealt);

        defender.takeDamage(damageDealt);
        Information.damageStatus(attacker, hability, damageDealt, defender);
    }
}
