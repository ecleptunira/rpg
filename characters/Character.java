package project.rpg.characters;

import project.rpg.utils.Direction;
import project.rpg.characters.classes.jobs.Job;
import project.rpg.characters.skills.Skill;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Character {
    private List<Skill> skills = new ArrayList<>();

    private String name;
    private int level = 1;
    private int physicalDamage = 1;
    private int magicDamage = 1;

    private int physicalDefense = 0;
    private int magicDefense = 0;

    private int life = 100;
    private int maxLife = life;

    private int posX = 0;
    private int posY = 0;

    private int criticalChance = 0; 
    private int criticalChanceAcumulated = criticalChance; //base equals to critical chance
    private int criticalDamage = 50; 

    private int evasion = 0;
    private int accuracy = 0; 

    private int block = 0;
    private int blockReduction = 0;
    private int parry = 0;
    private int parryReduction = 0;
    private int parryCounterChance = 0;

    protected Character(String name, Job choosenClass) {
        this.name = name;
        choosenClass.applyClassStats(this);
    }

    protected Character(String name){
        this.name = name;
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

    public String toString() {
        return "Caracter information: " +
        "\n Name: " + name + " | Level: " + level +
        "\n | Life: " + life + "/" + maxLife +
        "\n | Physical Damage: " + physicalDamage +
        "\n | Magic Damage: " + magicDamage + 
        "\n | Physical defense: " + physicalDefense +
        "\n | Magic defense: " + magicDefense +
        "\n | Critical Chance: " + criticalChance + "%" +
        "\n | Critical Damage: " + criticalDamage + "%" +
        "\n | Evasion: " + evasion + "%" +
        "\n | Accuracy: " + accuracy + "%" +
        "\n | Block: " + block + "%" + 
        "\n | Position: (" + posX + ", " + posY + ")" +
        "\n | Skills: " + skills.stream().map(Skill::getName).collect(Collectors.joining(", "));
    }

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

    //Setters and Getters

    public List<Skill> getSkills() {
        return skills;
    }
    public void addSkills(Skill skill){
        skills.add(skill);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }

    public int getPhysicalDamage() {
        return physicalDamage;
    }
    public void setPhysicalDamage(int physicalDamage) {
        this.physicalDamage = physicalDamage;
    }

    public int getMagicDamage() {
        return magicDamage;
    }
    public void setMagicDamage(int magicDamage) {
        this.magicDamage = magicDamage;
    }

    public int getPhysicalDefense(){
        return physicalDefense;
    }
    public void setPhysicalDefense(int physicalDefense) {
        this.physicalDefense = physicalDefense;
    }

    public int getMagicDefense(){
        return magicDefense;
    }
    public void setMagicDefense(int magicDefense) {
        this.magicDefense = magicDefense;
    }

    public int getLife() {
        return life;
    }
    public void setLife(int life) {
        this.life = life;
    }

    public int getMaxLife() {
        return maxLife;
    }
    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getCriticalChance() {
        return criticalChance;
    }
    public void setCriticalChance(int criticalChance) {
        this.criticalChance = criticalChance;
    }

    public int getCriticalChanceAcumulated(){
        return criticalChanceAcumulated;
    }
    public void setCriticalChanceAcumulated(int criticalChanceAcumulated) {
        this.criticalChanceAcumulated = criticalChanceAcumulated;
    }

    public int getCriticalDamage() {
        return criticalDamage;
    }
    public void setCriticalDamage(int criticalDamage) {
        this.criticalDamage = criticalDamage;
    }

    public int getEvasion(){
        return evasion;
    }
    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public int getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getBlock() {
        return block;
    }
    public void setBlock(int block) {
        this.block = block;
    }

    public int getBlockReduction() {
        return blockReduction;
    }
    public void setBlockReduction(int blockReduction) {
        this.blockReduction = blockReduction;
    }

    public int getParry() {
        return parry;
    }
    public void setParry(int parry) {
        this.parry = parry;
    }

    public int getParryReduction() {
        return parryReduction;
    }
    public void setParryReduction(int parryReduction) {
        this.parryReduction = parryReduction;
    }

    public int getParryCounterChance() {
        return parryCounterChance;
    }
    public void setParryCounterChance(int parryCounterChance) {
        this.parryCounterChance = parryCounterChance;
    }

}
