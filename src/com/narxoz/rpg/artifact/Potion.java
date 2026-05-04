package com.narxoz.rpg.artifact;

public class Potion extends Artifact {
    private final int healAmount;
    private final String potionType;

    public Potion(String name, int value, int healAmount, String potionType) {
        super(name, value);
        this.healAmount = healAmount;
        this.potionType = potionType;
    }

    public int getHealAmount() { return healAmount; }
    public String getPotionType() { return potionType; }

    @Override
    public void accept(ArtifactVisitor visitor) {
        visitor.visit(this);
    }
}