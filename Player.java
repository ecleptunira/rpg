package project.rpg;

public class Player extends Character{

    public Player(String name, int damage, int maxLife, int posX, int posY) {
        super(name, damage, maxLife, posX, posY);
    }

    @Override
    public void attack(Character enemy) {
        if (!canAttack(enemy)) {
            Information.outOfRange(this, enemy);
            return;
        }
        if (Math.random() <= this.getMissChance()) {
            Information.damageMissed(this, enemy);
            return;
        }
        int baseDamage = (int) (this.getDamage() * 0.75);
        int bonusDamage = (int) (this.getDamage() * 0.25);
        int variableDamage = (int) (Math.random() * (bonusDamage + 1));
        int damageDealt = baseDamage + variableDamage;

        enemy.takeDamage(damageDealt);
        Information.damageStatus(this, damageDealt, enemy);
    }
}
