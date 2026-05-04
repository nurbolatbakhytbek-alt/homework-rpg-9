package com.narxoz.rpg.artifact;

public class Ring extends Artifact {
    private final int manaRegen;
    private final int defenseBonus;

    public Ring(String name, int value, int manaRegen, int defenseBonus) {
        super(name, value);
        this.manaRegen = manaRegen;
        this.defenseBonus = defenseBonus;
    }

    public int getManaRegen() { return manaRegen; }
    public int getDefenseBonus() { return defenseBonus; }

    @Override
    public void accept(ArtifactVisitor visitor) {
        visitor.visit(this);
    }
}