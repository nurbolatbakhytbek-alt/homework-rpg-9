package com.narxoz.rpg.artifact;

public abstract class Artifact {
    protected final String name;
    protected final int value;

    public Artifact(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }
    public int getValue() { return value; }

    public abstract void accept(ArtifactVisitor visitor);
}