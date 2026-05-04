package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

public class StatsDisplayVisitor implements ArtifactVisitor {
    @Override
    public void visit(Weapon weapon) {
        System.out.printf("  ⚔️ %s | Type: %s | Damage: +%d | Value: %d gold%n",
                weapon.getName(), weapon.getWeaponType(), weapon.getDamageBonus(), weapon.getValue());
    }

    @Override
    public void visit(Potion potion) {
        System.out.printf("  🧪 %s | Type: %s | Heal: %d HP | Value: %d gold%n",
                potion.getName(), potion.getPotionType(), potion.getHealAmount(), potion.getValue());
    }

    @Override
    public void visit(Scroll scroll) {
        System.out.printf("  📜 %s | Spell: %s | Mana Cost: %d | Value: %d gold%n",
                scroll.getName(), scroll.getSpellName(), scroll.getManaCost(), scroll.getValue());
    }

    @Override
    public void visit(Ring ring) {
        System.out.printf("  💍 %s | Mana Regen: +%d | Defense: +%d | Value: %d gold%n",
                ring.getName(), ring.getManaRegen(), ring.getDefenseBonus(), ring.getValue());
    }

    @Override
    public void visit(Armor armor) {
        System.out.printf("  🛡️ %s | Type: %s | Defense: %d | Value: %d gold%n",
                armor.getName(), armor.getArmorType(), armor.getDefenseRating(), armor.getValue());
    }
}