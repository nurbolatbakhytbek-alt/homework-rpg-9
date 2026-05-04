package com.narxoz.rpg.vault;

public class VaultRunResult {
    private final boolean completed;
    private final int artifactsAppraised;
    private final int totalValue;
    private final int timesRewound;
    private final int finalHp;
    private final int finalMana;
    private final int finalGold;

    public VaultRunResult(boolean completed, int artifactsAppraised, int totalValue,
                          int timesRewound, int finalHp, int finalMana, int finalGold) {
        this.completed = completed;
        this.artifactsAppraised = artifactsAppraised;
        this.totalValue = totalValue;
        this.timesRewound = timesRewound;
        this.finalHp = finalHp;
        this.finalMana = finalMana;
        this.finalGold = finalGold;
    }

    public boolean isCompleted() { return completed; }
    public int getArtifactsAppraised() { return artifactsAppraised; }
    public int getTotalValue() { return totalValue; }
    public int getTimesRewound() { return timesRewound; }
    public int getFinalHp() { return finalHp; }
    public int getFinalMana() { return finalMana; }
    public int getFinalGold() { return finalGold; }

    public void display() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║        CHRONOMANCER'S VAULT RESULTS        ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("Vault Completed: " + (completed ? "✓ YES" : "✗ NO"));
        System.out.println("Artifacts Appraised: " + artifactsAppraised);
        System.out.println("Total Artifact Value: " + totalValue + " gold");
        System.out.println("Times Rewound: " + timesRewound);
        System.out.println("Final HP: " + finalHp);
        System.out.println("Final Mana: " + finalMana);
        System.out.println("Final Gold: " + finalGold);
    }
}