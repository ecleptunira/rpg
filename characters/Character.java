package project.rpg.characters;

import project.rpg.utils.Direction;
import project.rpg.utils.Information;
import project.rpg.characters.classes.jobs.Job;
import project.rpg.characters.skills.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Character {
    private List<Skill> skills = new ArrayList<>();

    private String name;                        // name of the character
    private int level = 0;                      // level of the character
    private int experience = 0;                 // experience points
    private int experienceToNextLevel = 100;    // experience needed for next level
    
    private int life = 100;              // current life
    private int maxLife = life;          // maximum life

    private int physicalDamage = 1;      // base physical damage
    private int magicalDamage = 1;       // base magic damage

    private int physicalDefense = 0;     // base physical defense
    private int magicDefense = 0;        // base magic defense

    private int posX = 0;                // position on X axis
    private int posY = 0;                // position on Y axis

    private int criticalChance = 0;                        // percentage chance to land a critical hit
    private int criticalChanceAcumulated = 0;              // acumulated critical chance for next attack
    private int criticalDamage = 50;                       // percentage increase in damage on a critical hit

    private int evasion = 0;              // percentage chance to evade an attack
    private int accuracy = 0;             // percentage chance to hit an attack

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

    /**
     * Reduce the character's life when taking damage,
     * ensuring it does not drop below zero.
     * @param damage amound damage to be taken
     */
    public void takeDamage(int damage) {
        this.life -= damage;
        if (this.life < 0) {
            this.life = 0;
        }
    }

    /**
     * Heal the character by a especific amount,
     * ensuring life does not exceed maxLife.
     * @param amount amount to heal
     */
    public int heal(int amount){
        int before = this.life;
        this.life = Math.min(this.life + amount, this.maxLife);
        return (this.life - before);
    }

    /**
     * Restore the character's life to full capacity.
     */
    public void restoreLifeToFull(){
        this.life = this.maxLife;
    }

    /**
     * Increase character's experience points
     * @param amount Amount of experience to be gained
     */
    public void gainExperience(int amount){
        this.experience += amount;
        Information.gainExperience(this, amount);
        Information.showExperience(this);
        while (this.experience >= this.experienceToNextLevel){
            levelUp();
        }
    }

    /**
     * level up the character.
     */
    public void levelUp(){
        this.level ++;
        this.experience -= this.experienceToNextLevel ;
        this.experienceToNextLevel = (int) Math.round(this.experienceToNextLevel * 1.25);
        Information.levelUp(this);
        Information.showExperience(this);
    }

    public void forceLevelTo(int targetLevel){
        if (targetLevel < this.level){
            return;
        }

        while (this.level < targetLevel){
            this.level ++;
            this.experienceToNextLevel = (int) Math.round(this.experienceToNextLevel * 1.25);
        }
        // Information.levelUp(this);
        // Information.showExperience(this);
    }

    /**
     * Check if the character is alive
     * @return true if is alive and false if is dead
     */
    public boolean isAlive(){
        return this.life > 0;
    }

    public String toString() {
        return "Caracter information: " +
        "\n Name: " + name + " | Level: " + level +
        "\n | Life: " + life + "/" + maxLife +
        "\n | Physical Damage: " + physicalDamage +
        "\n | Magic Damage: " + magicalDamage + 
        "\n | Physical defense: " + physicalDefense +
        "\n | Magic defense: " + magicDefense +
        "\n | Critical Chance: " + criticalChance + "%" +
        "\n | Critical Damage: " + criticalDamage + "%" +
        "\n | Evasion: " + evasion + "%" +
        "\n | Accuracy: " + accuracy + "%" +
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

    public double getExperience(){
        return experience;
    }
    public void setExperience(int experience){
        this.experience = experience;
    }

    public double getExperienceToNextLevel(){
        return experienceToNextLevel;
    }
    public void setExperienceToNextLevel(int experienceToNextLevel){
        this.experienceToNextLevel = experienceToNextLevel;
    }

    public int getPhysicalDamage() {
        return physicalDamage;
    }
    public void setPhysicalDamage(int physicalDamage) {
        this.physicalDamage = physicalDamage;
    }

    public int getMagicalDamage() {
        return magicalDamage;
    }
    public void setMagicalDamage(int magicalDamage) {
        this.magicalDamage = magicalDamage;
    }

    public int getPercentPhysicalDefense(){
        return (int) Math.abs((100 / (1 + (this.physicalDefense / 25.0)))-100);
    }
    public int getPhysicalDefense(){
        return physicalDefense;
    }
    public void setPhysicalDefense(int physicalDefense) {
        this.physicalDefense = physicalDefense;
    }

    public int getPercentMagicDefense(){
        return (int) Math.abs((100 / (1 + (this.magicDefense / 25.0)))-100);
    }
    public int getMagicalDefense(){
        return magicDefense;
    }
    public void setMagicalDefense(int magicDefense) {
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

}
