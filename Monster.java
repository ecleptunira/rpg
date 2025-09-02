package project.rpg;

public class Monster extends Player {
    private String hability1 = "Stomp";

    public Monster(String name, int damage, int maxLife, int posX, int posY) {
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
        int baseDamage = (int) (this.getDamage() * 0.85);
        int bonusDamage = (int) (this.getDamage() * 0.45);
        int variableDamage = (int) (Math.random() * (bonusDamage + 1));
        int damageDealt = baseDamage + variableDamage;

        enemy.takeDamage(damageDealt);
        Information.damageStatus(this, damageDealt, enemy);
    }

    public void stomp(Character enemy) {
        if (!canAttack(enemy)) {
            Information.outOfRange(this, enemy);
            return;
        } 
        if (Math.random() <= 0.3) {
            Information.damageMissed(this, hability1, enemy);
            return;
        } 
        int baseDamage = (int) (this.getDamage() * 1);
        int bonusDamage = (int) (this.getDamage() * 0.5);
        int variableDamage = (int) (Math.random() * (bonusDamage + 1));
        int damageDealt = baseDamage + variableDamage;
        
        enemy.takeDamage(damageDealt);
        Information.damageStatus(this, hability1, damageDealt, enemy);
    }
}
