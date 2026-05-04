package com.narxoz.rpg.artifact;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Artifact> artifacts = new ArrayList<>();

    public void add(Artifact artifact) {
        artifacts.add(artifact);
    }

    public void remove(Artifact artifact) {
        artifacts.remove(artifact);
    }

    public List<Artifact> getArtifacts() {
        return new ArrayList<>(artifacts);
    }

    public void accept(ArtifactVisitor visitor) {
        for (Artifact artifact : artifacts) {
            artifact.accept(visitor);
        }
    }

    public int size() {
        return artifacts.size();
    }

    public boolean isEmpty() {
        return artifacts.isEmpty();
    }
}