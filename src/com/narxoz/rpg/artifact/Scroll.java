package com.narxoz.rpg.artifact;

public class Scroll extends Artifact {
    private final String spellName;
    private final int manaCost;

    public Scroll(String name, int value, String spellName, int manaCost) {
        super(name, value);
        this.spellName = spellName;
        this.manaCost = manaCost;
    }

    public String getSpellName() { return spellName; }
    public int getManaCost() { return manaCost; }

    @Override
    public void accept(ArtifactVisitor visitor) {
        visitor.visit(this);
    }
}