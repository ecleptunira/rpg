package project.rpg;

public class Monster extends Player {
    
    public Monster(String name, int damage, int life, int posX, int posY) {
        super(name, damage, life, posX, posY);
    }

    @Override
    public void attack(Character enemy) {
        boolean inRange = canAttack(enemy);
        if (!inRange) {
            System.out.println("Out of range");
        } else {
            int missChance = (int) (Math.random() * 10);
            if (missChance <= 3) {
                System.out.println("failed attack");
            } else {
            int baseDamage = (int) (this.getDamage() * 0.85);
            int bonusDamage = (int) (this.getDamage() * 0.45);
            int variableDamage = (int) (Math.random() * (bonusDamage + 1));
            int damageDealt = baseDamage + variableDamage;

            enemy.takeDamage(damageDealt);
            System.out.println(this.getName() + " cause " + damageDealt + 
            " of damage on " + enemy.getName() + 
            ", now he has " + enemy.getLife() + " of life.");
            }
        }
    }

    public void stomp(Character enemy) {
        boolean inRange = canAttack(enemy);
        if (!inRange) {
            System.out.println("Out of range");
        } else {
            int missChance = (int) (Math.random() * 10);
            if (missChance <= 3) {
                System.out.println("Stomp Failed ");
                return;
            } else {
            int baseDamage = (int) (this.getDamage() * 1);
            int bonusDamage = (int) (this.getDamage() * 0.5);
            int variableDamage = (int) (Math.random() * (bonusDamage + 1));
            int damageDealt = baseDamage + variableDamage;
            
            enemy.takeDamage(damageDealt);

            System.out.println(this.getName() + " used Stomp!");
            System.out.println("Stomp hit with a power of " + damageDealt + " points! \n" + 
            enemy.getName() + " now has " + enemy.getLife() + " points of life.");
            }
        }
    }

}
