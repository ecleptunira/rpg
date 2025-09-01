package project.rpg;

public class Player extends Character{

    public Player(String name, int damage, int life, int posX, int posY) {
        super(name, damage, life, posX, posY);
    }

    @Override
    public void attack(Character enemy) {
        if (!canAttack(enemy)) {
            System.out.println("Out of range");
            return;
        } 
        if (Math.random() <= 0.3) {
            System.out.println("failed attack");
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
