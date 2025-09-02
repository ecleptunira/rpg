package project.rpg;

public class Hero extends Player {

    String hability1 = "Divine Sword";

    public Hero(String name, int damage, int maxLife, int posX, int posY) {
        super(name, damage, maxLife, posX, posY);
    }

    @Override
    public void attack(Character enemy) {
        if (!canAttack(enemy)) {
            Information.outOfRange(this, enemy);
            return;
        }
        if (Math.random() <= 0.3) {
            Information.damageMissed(this, enemy);
            return;
        }
        int baseDamage = (int) (this.getDamage() * 0.95);
        int bonusDamage = (int) (this.getDamage() * 0.15);
        int variableDamage = (int) (Math.random() * (bonusDamage + 1));
        int damageDealt = baseDamage + variableDamage;

        enemy.takeDamage(damageDealt);
        Information.damageStatus(this, damageDealt, enemy);

    }

    public void divineSword(Character enemy) {
        if (!canAttack(enemy)) {
            Information.outOfRange(this, enemy);
            return;
        }
        if (Math.random() <= 0.3) { // base 0.3
            Information.damageMissed(this, hability1, enemy);
            System.out.println("***miss***"); // tirar
            return;
        }
        System.out.println("***calculando dano***"); // tirar
        int baseDamage = (int) (this.getDamage() * 1);
        int bonusDamage = (int) (this.getDamage() * 0.25);
        int variableDamage = (int) (Math.random() * (bonusDamage + 1));
        int damageDealt = DamageCalculator.criticalChance(this, (baseDamage + variableDamage));

        enemy.takeDamage(damageDealt);
        Information.damageStatus(this, hability1, damageDealt, enemy);
    }
}
