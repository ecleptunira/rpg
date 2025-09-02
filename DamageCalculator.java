package project.rpg;

public class DamageCalculator {

    public static int criticalChance(Character attacker, int damageDealt){
        if (Math.random() * 100 < attacker.getCriticalChanceAcumulative()) {
            System.out.println("tentativa de critico "  + damageDealt); // tirar
            damageDealt = (int) (damageDealt * (attacker.getCritalDamage() / 100.0 + 1));
            System.out.println("CRITICAL HIT!");
            attacker.setCriticalChanceAculative(attacker.getCriticalChance());
            return damageDealt;
        } else {
            attacker.setCriticalChanceAculative(attacker.getCriticalChanceAcumulative() + attacker.getCriticalChance());
            System.out.println("tentiva falha de critico "+attacker.getCriticalChanceAcumulative()); // tirar
            return damageDealt;
        }
    }
}
