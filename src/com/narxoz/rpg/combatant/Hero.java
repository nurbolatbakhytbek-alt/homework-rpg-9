package com.narxoz.rpg.combatant;

import com.narxoz.rpg.artifact.Inventory;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    private final String name;
    private int hp;
    private final int maxHp;
    private int mana;
    private final int maxMana;
    private int gold;
    private int exp;
    private int attackPower;
    private final int baseAttackPower;
    private final Inventory inventory;
    private List<String> actionLog;

    public Hero(String name, int hp, int mana, int attackPower) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.mana = mana;
        this.maxMana = mana;
        this.attackPower = attackPower;
        this.baseAttackPower = attackPower;
        this.gold = 0;
        this.exp = 0;
        this.inventory = new Inventory();
        this.actionLog = new ArrayList<>();
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getMana() { return mana; }
    public int getMaxMana() { return maxMana; }
    public int getGold() { return gold; }
    public int getExp() { return exp; }
    public int getAttackPower() { return attackPower; }
    public Inventory getInventory() { return inventory; }
    public List<String> getActionLog() { return new ArrayList<>(actionLog); }

    public boolean isAlive() { return hp > 0; }

    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
        log("Took " + amount + " damage. HP: " + hp + "/" + maxHp);
    }

    public void heal(int amount) {
        int oldHp = hp;
        hp = Math.min(maxHp, hp + amount);
        log("Healed for " + (hp - oldHp) + " HP. HP: " + hp + "/" + maxHp);
    }

    public void useMana(int amount) {
        mana = Math.max(0, mana - amount);
        log("Used " + amount + " mana. Mana: " + mana + "/" + maxMana);
    }

    public void restoreMana(int amount) {
        int oldMana = mana;
        mana = Math.min(maxMana, mana + amount);
        log("Restored " + (mana - oldMana) + " mana. Mana: " + mana + "/" + maxMana);
    }

    public void addGold(int amount) {
        gold += amount;
        log("Gained " + amount + " gold. Total: " + gold);
    }

    public void subtractGold(int amount) {
        gold = Math.max(0, gold - amount);
        log("Lost " + amount + " gold. Total: " + gold);
    }

    public void addExp(int amount) {
        exp += amount;
        log("Gained " + amount + " EXP. Total: " + exp);
        checkLevelUp();
    }

    private void checkLevelUp() {
        int newAttackPower = baseAttackPower + (exp / 100);
        if (newAttackPower > attackPower) {
            attackPower = newAttackPower;
            log("LEVEL UP! Attack Power increased to " + attackPower);
        }
    }

    public void addArtifact(com.narxoz.rpg.artifact.Artifact artifact) {
        inventory.add(artifact);
        log("Added artifact: " + artifact.getName());
    }

    public void removeArtifact(com.narxoz.rpg.artifact.Artifact artifact) {
        inventory.remove(artifact);
        log("Removed artifact: " + artifact.getName());
    }

    public HeroMemento createMemento() {
        log("SAVE POINT CREATED");
        return new HeroMemento(name, hp, maxHp, mana, maxMana, gold, exp, attackPower, inventory);
    }

    public void restoreMemento(HeroMemento memento) {
        if (memento == null) {
            log("RESTORE FAILED: No save point available!");
            return;
        }

        this.hp = memento.getHp();
        this.mana = memento.getMana();
        this.gold = memento.getGold();
        this.exp = memento.getExp();
        this.attackPower = memento.getAttackPower();

        this.inventory.getArtifacts().clear();
        for (var artifact : memento.getInventory().getArtifacts()) {
            this.inventory.add(artifact);
        }

        log("=== STATE RESTORED ===");
        log("HP: " + hp + "/" + maxHp);
        log("Mana: " + mana + "/" + maxMana);
        log("Gold: " + gold);
        log("EXP: " + exp);
        log("Attack Power: " + attackPower);
    }

    private void log(String message) {
        actionLog.add(message);
        System.out.println("[HERO] " + message);
    }

    public void displayStatus() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║ " + padRight(name, 28) + "║");
        System.out.println("╠════════════════════════════════╣");
        System.out.printf("║ HP: %3d / %-3d                    ║%n", hp, maxHp);
        System.out.printf("║ Mana: %3d / %-3d                  ║%n", mana, maxMana);
        System.out.printf("║ Attack: %-3d                       ║%n", attackPower);
        System.out.printf("║ Gold: %-6d                       ║%n", gold);
        System.out.printf("║ EXP: %-6d                        ║%n", exp);
        System.out.println("╚════════════════════════════════╝");
    }

    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}