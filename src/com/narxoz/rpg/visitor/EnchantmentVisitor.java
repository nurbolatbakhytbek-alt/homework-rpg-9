package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

public class EnchantmentVisitor implements ArtifactVisitor {
    private int enchantmentCost = 0;

    @Override
    public void visit(Weapon weapon) {
        int cost = weapon.getValue() / 2;
        enchantmentCost += cost;
        System.out.printf("  ⚔️ Enchanting %s: %d gold (Damage +%d)%n",
                weapon.getName(), cost, weapon.getDamageBonus() / 2);
    }

    @Override
    public void visit(Potion potion) {
        int cost = potion.getValue() / 3;
        enchantmentCost += cost;
        System.out.printf("  🧪 Enhancing %s: %d gold (Heal +%d)%n",
                potion.getName(), cost, potion.getHealAmount() / 2);
    }

    @Override
    public void visit(Scroll scroll) {
        int cost = scroll.getValue() / 2;
        enchantmentCost += cost;
        System.out.printf("  📜 Empowering %s: %d gold (Mana cost -%d)%n",
                scroll.getName(), cost, scroll.getManaCost() / 3);
    }

    @Override
    public void visit(Ring ring) {
        int cost = ring.getValue() / 2;
        enchantmentCost += cost;
        System.out.printf("  💍 Infusing %s: %d gold (Mana Regen +%d, Defense +%d)%n",
                ring.getName(), cost, ring.getManaRegen() / 2, ring.getDefenseBonus() / 2);
    }

    @Override
    public void visit(Armor armor) {
        int cost = armor.getValue() / 2;
        enchantmentCost += cost;
        System.out.printf("  🛡️ Reinforcing %s: %d gold (Defense +%d)%n",
                armor.getName(), cost, armor.getDefenseRating() / 2);
    }

    public int getEnchantmentCost() {
        return enchantmentCost;
    }
}