package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

public class ValueCalculatorVisitor implements ArtifactVisitor {
    private int totalValue = 0;

    @Override
    public void visit(Weapon weapon) {
        totalValue += weapon.getValue();
        System.out.println("  Weapon: " + weapon.getName() + " (Legendary) - Value: " + weapon.getValue());
    }

    @Override
    public void visit(Potion potion) {
        totalValue += potion.getValue();
        System.out.println("  Potion: " + potion.getName() + " (Alchemical) - Value: " + potion.getValue());
    }

    @Override
    public void visit(Scroll scroll) {
        totalValue += scroll.getValue();
        System.out.println("  Scroll: " + scroll.getName() + " (Arcane) - Value: " + scroll.getValue());
    }

    @Override
    public void visit(Ring ring) {
        totalValue += ring.getValue();
        System.out.println("  Ring: " + ring.getName() + " (Jeweled) - Value: " + ring.getValue());
    }

    @Override
    public void visit(Armor armor) {
        totalValue += armor.getValue();
        System.out.println("  Armor: " + armor.getName() + " (Protective) - Value: " + armor.getValue());
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void reset() {
        totalValue = 0;
    }
}