package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

public class PowerRatingVisitor implements ArtifactVisitor {
    private int totalPower = 0;
    private int artifactCount = 0;

    @Override
    public void visit(Weapon weapon) {
        int power = weapon.getDamageBonus() * 2 + weapon.getValue() / 10;
        totalPower += power;
        artifactCount++;
        System.out.printf("  %s (Weapon) - Power Rating: %d%n", weapon.getName(), power);
    }

    @Override
    public void visit(Potion potion) {
        int power = potion.getHealAmount() + potion.getValue() / 20;
        totalPower += power;
        artifactCount++;
        System.out.printf("  %s (Potion) - Power Rating: %d%n", potion.getName(), power);
    }

    @Override
    public void visit(Scroll scroll) {
        int power = (100 - scroll.getManaCost()) + scroll.getValue() / 15;
        totalPower += power;
        artifactCount++;
        System.out.printf("  %s (Scroll) - Power Rating: %d%n", scroll.getName(), power);
    }

    @Override
    public void visit(Ring ring) {
        int power = ring.getManaRegen() * 3 + ring.getDefenseBonus() * 2 + ring.getValue() / 25;
        totalPower += power;
        artifactCount++;
        System.out.printf("  %s (Ring) - Power Rating: %d%n", ring.getName(), power);
    }

    @Override
    public void visit(Armor armor) {
        int power = armor.getDefenseRating() * 3 + armor.getValue() / 10;
        totalPower += power;
        artifactCount++;
        System.out.printf("  %s (Armor) - Power Rating: %d%n", armor.getName(), power);
    }

    public double getAveragePower() {
        return artifactCount == 0 ? 0 : (double) totalPower / artifactCount;
    }

    public int getTotalPower() {
        return totalPower;
    }
}