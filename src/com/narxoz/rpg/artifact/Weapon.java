package com.narxoz.rpg.artifact;

public class Weapon extends Artifact {
    private final int damageBonus;
    private final String weaponType;

    public Weapon(String name, int value, int damageBonus, String weaponType) {
        super(name, value);
        this.damageBonus = damageBonus;
        this.weaponType = weaponType;
    }

    public int getDamageBonus() { return damageBonus; }
    public String getWeaponType() { return weaponType; }

    @Override
    public void accept(ArtifactVisitor visitor) {
        visitor.visit(this);
    }
}