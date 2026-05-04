package com.narxoz.rpg.combatant;

import com.narxoz.rpg.artifact.Inventory;

public class HeroMemento {
    private final String name;
    private final int hp;
    private final int maxHp;
    private final int mana;
    private final int maxMana;
    private final int gold;
    private final int exp;
    private final int attackPower;
    private final Inventory inventory;

    HeroMemento(String name, int hp, int maxHp, int mana, int maxMana,
                int gold, int exp, int attackPower, Inventory inventory) {
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.mana = mana;
        this.maxMana = maxMana;
        this.gold = gold;
        this.exp = exp;
        this.attackPower = attackPower;
        this.inventory = copyInventory(inventory);
    }

    private Inventory copyInventory(Inventory original) {
        Inventory copy = new Inventory();
        for (var artifact : original.getArtifacts()) {
            copy.add(artifact);
        }
        return copy;
    }

    String getName() { return name; }
    int getHp() { return hp; }
    int getMaxHp() { return maxHp; }
    int getMana() { return mana; }
    int getMaxMana() { return maxMana; }
    int getGold() { return gold; }
    int getExp() { return exp; }
    int getAttackPower() { return attackPower; }
    Inventory getInventory() { return copyInventory(inventory); }
}