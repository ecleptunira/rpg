package project.rpg;

public abstract class Character {
    private String name;
    private int damage;
    private int life;
    private int posX;
    private int posY;

    protected Character(String name, int damage, int life, int posX, int posY) {
        this.name = name;
        this.damage = damage;
        this.life = life;
        this.posX = posX;
        this.posY = posY;
    }

    public boolean canAttack(Character enemy) {
        int deltaX = Math.abs(this.posX - enemy.posX);
        int deltaY = Math.abs(this.posY - enemy.posY);
        return (deltaX < 2 && deltaY == 0) || (deltaX == 0 && deltaY < 2);
    }

    public void takeDamage(int damage) {
        this.life -= damage;
        if (this.life < 0) {
            this.life = 0;
        }
    }

    public abstract void attack(Character enemy);

    public void move (Direction direction) {
        if (direction == Direction.UP) {
            this.posY += 1;
        } else if (direction == Direction.DOWN) {
            this.posY -= 1;
        } else if (direction == Direction.LEFT) {
            this.posX -= 1;
        } else if (direction == Direction.RIGHT) {
            this.posX += 1;
        }
    }

    public int getDamage() {
        return damage;
    }
    public int getLife() {
        return life;
    }
    public String getName() {
        return name;
    }
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
}
