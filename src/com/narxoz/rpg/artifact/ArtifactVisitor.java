package com.narxoz.rpg.artifact;

public interface ArtifactVisitor {
    void visit(Weapon weapon);
    void visit(Potion potion);
    void visit(Scroll scroll);
    void visit(Ring ring);
    void visit(Armor armor);
}