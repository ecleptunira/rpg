package project.rpg;

public class Monster extends Player {
    private String hability1 = "Stomp";

    public Monster(String name, int damage, int life, int posX, int posY) {
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

        int baseDamage = (int) (this.getDamage() * 0.85);
        int bonusDamage = (int) (this.getDamage() * 0.45);
        int variableDamage = (int) (Math.random() * (bonusDamage + 1));
        int damageDealt = baseDamage + variableDamage;

        enemy.takeDamage(damageDealt);
        Information.damageStatus(this, damageDealt, enemy);
    }

    public void stomp(Character enemy) {
        if (!canAttack(enemy)) {
            System.out.println("Out of range");
        } 
        if (Math.random() <= 0.3) {
            System.out.println("Stomp Failed ");
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
