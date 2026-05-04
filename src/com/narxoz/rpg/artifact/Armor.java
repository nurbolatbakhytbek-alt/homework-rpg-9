package com.narxoz.rpg.artifact;

public class Armor extends Artifact {
    private final int defenseRating;
    private final String armorType;

    public Armor(String name, int value, int defenseRating, String armorType) {
        super(name, value);
        this.defenseRating = defenseRating;
        this.armorType = armorType;
    }

    public int getDefenseRating() { return defenseRating; }
    public String getArmorType() { return armorType; }

    @Override
    public void accept(ArtifactVisitor visitor) {
        visitor.visit(this);
    }
}