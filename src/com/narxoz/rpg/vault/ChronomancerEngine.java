package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.memento.Caretaker;
import com.narxoz.rpg.visitor.PowerRatingVisitor;
import com.narxoz.rpg.visitor.StatsDisplayVisitor;
import com.narxoz.rpg.visitor.ValueCalculatorVisitor;
import java.util.Random;

public class ChronomancerEngine {
    private final Hero hero;
    private final Caretaker caretaker;
    private final Random random;
    private int timesRewound = 0;
    private int artifactsAppraised = 0;
    private int totalValue = 0;

    public ChronomancerEngine(Hero hero) {
        this.hero = hero;
        this.caretaker = new Caretaker();
        this.random = new Random();
    }

    public VaultRunResult runVault() {
        System.out.println("\n⏳ CHRONOMANCER'S VAULT ⏳");
        System.out.println("A temporal anomaly awaits...\n");

        hero.displayStatus();

        caretaker.save(hero);

        for (int chamber = 1; chamber <= 5; chamber++) {
            System.out.println("\n┌─────────────────────────────────────────┐");
            System.out.println("│  CHAMBER " + chamber + " OF THE CHRONOMANCER            │");
            System.out.println("└─────────────────────────────────────────┘");

            if (!hero.isAlive()) {
                System.out.println("\n💀 The hero has fallen! Vault expedition failed! 💀");
                return new VaultRunResult(false, artifactsAppraised, totalValue,
                        timesRewound, hero.getHp(), hero.getMana(), hero.getGold());
            }

            caretaker.save(hero);

            Inventory chamberInventory = generateChamberLoot(chamber);

            System.out.println("\n📦 You find several artifacts in the chamber:");
            StatsDisplayVisitor displayVisitor = new StatsDisplayVisitor();
            chamberInventory.accept(displayVisitor);

            System.out.println("\n🔍 Appraising artifacts...");
            ValueCalculatorVisitor valueVisitor = new ValueCalculatorVisitor();
            chamberInventory.accept(valueVisitor);
            int chamberValue = valueVisitor.getTotalValue();

            artifactsAppraised += chamberInventory.size();
            totalValue += chamberValue;

            System.out.println("\n📊 Chamber Summary:");
            System.out.println("  - Artifacts found: " + chamberInventory.size());
            System.out.println("  - Total value: " + chamberValue + " gold");

            boolean trapTriggered = random.nextDouble() < 0.3;

            if (trapTriggered) {
                System.out.println("\n⚠️ TEMPORAL TRAP ACTIVATED! ⚠️");
                int damage = 30 + random.nextInt(41);
                int manaDrain = 20 + random.nextInt(31);

                hero.takeDamage(damage);
                hero.useMana(manaDrain);

                System.out.println("  - Damage taken: " + damage);
                System.out.println("  - Mana drained: " + manaDrain);

                if (!hero.isAlive()) {
                    System.out.println("\n⌛ Attempting temporal rewind...");
                    if (caretaker.restore(hero)) {
                        timesRewound++;
                        System.out.println("✓ Timeline restored! Hero saved from death!");

                        System.out.println("\n🔄 State after rewind:");
                        PowerRatingVisitor powerVisitor = new PowerRatingVisitor();
                        hero.getInventory().accept(powerVisitor);
                        System.out.printf("  - Average Artifact Power: %.1f%n", powerVisitor.getAveragePower());
                    } else {
                        System.out.println("✗ No save point available. Hero perishes.");
                        return new VaultRunResult(false, artifactsAppraised, totalValue,
                                timesRewound, hero.getHp(), hero.getMana(), hero.getGold());
                    }
                } else if (hero.getHp() < hero.getMaxHp() * 0.3) {
                    System.out.println("\n⚠️ Critical HP! Considering rewind...");
                    if (random.nextBoolean()) {
                        System.out.println("⌛ Chronomancer rewinds time to avoid critical state!");
                        if (caretaker.restore(hero)) {
                            timesRewound++;
                            System.out.println("✓ State restored to before trap!");
                        }
                    } else {
                        System.out.println("Hero presses forward despite injuries.");
                    }
                }
            } else {
                System.out.println("\n✨ No trap detected. You successfully appraise the artifacts!");

                for (Artifact artifact : chamberInventory.getArtifacts()) {
                    hero.addArtifact(artifact);
                }
            }

            hero.displayStatus();

            if (chamber % 2 == 0) {
                System.out.println("\n📈 Artifact Power Assessment:");
                PowerRatingVisitor powerVisitor = new PowerRatingVisitor();
                hero.getInventory().accept(powerVisitor);
                System.out.printf("  - Average Power Rating: %.1f%n", powerVisitor.getAveragePower());
                System.out.printf("  - Total Power: %d%n", powerVisitor.getTotalPower());
            }
        }

        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║     FINAL CHAMBER: THE CHRONOMANCER        ║");
        System.out.println("╚════════════════════════════════════════════╝");

        caretaker.save(hero);

        System.out.println("\nThe Chronomancer appears before you!");
        System.out.println("'You have done well, mortal. But one final test awaits...'");

        int finalChallengeDamage = 50 + random.nextInt(51);
        System.out.println("\n💥 The Chronomancer unleashes a temporal blast: " + finalChallengeDamage + " damage!");
        hero.takeDamage(finalChallengeDamage);

        if (!hero.isAlive()) {
            System.out.println("\n⌛ The Chronomancer takes pity and rewinds time...");
            if (caretaker.restore(hero)) {
                timesRewound++;
                System.out.println("✓ You are given another chance!");

                int secondAttemptDamage = 40;
                System.out.println("💫 A gentler blast hits you: " + secondAttemptDamage + " damage!");
                hero.takeDamage(secondAttemptDamage);

                if (!hero.isAlive()) {
                    System.out.println("✗ Even fate cannot save you now.");
                    return new VaultRunResult(false, artifactsAppraised, totalValue,
                            timesRewound, hero.getHp(), hero.getMana(), hero.getGold());
                }
            } else {
                return new VaultRunResult(false, artifactsAppraised, totalValue,
                        timesRewound, hero.getHp(), hero.getMana(), hero.getGold());
            }
        }

        System.out.println("\n✨ The Chronomancer nods approvingly. ✨");
        System.out.println("'You have mastered the flow of time. These artifacts are yours.'");

        int finalReward = 500 + random.nextInt(501);
        hero.addGold(finalReward);
        System.out.println("🏆 Bonus reward: " + finalReward + " gold for completing the vault!");

        return new VaultRunResult(true, artifactsAppraised, totalValue,
                timesRewound, hero.getHp(), hero.getMana(), hero.getGold());
    }

    private Inventory generateChamberLoot(int chamber) {
        Inventory inventory = new Inventory();
        int numArtifacts = 2 + random.nextInt(3);

        for (int i = 0; i < numArtifacts; i++) {
            Artifact artifact = createRandomArtifact(chamber);
            inventory.add(artifact);
        }

        return inventory;
    }

    private Artifact createRandomArtifact(int chamber) {
        int type = random.nextInt(5);
        int value = 50 + chamber * 20 + random.nextInt(51);

        switch (type) {
            case 0:
                String[] weapons = {"Dragon's Wrath", "Shadow Blade", "Frostmourne", "Thunderfury"};
                String[] weaponTypes = {"Sword", "Bow", "Staff", "Dagger"};
                return new Weapon(
                        weapons[random.nextInt(weapons.length)],
                        value,
                        15 + random.nextInt(36),
                        weaponTypes[random.nextInt(weaponTypes.length)]
                );
            case 1:
                String[] potions = {"Elixir of Life", "Potion of Might", "Draught of Clarity"};
                String[] potionTypes = {"Healing", "Strength", "Mana"};
                return new Potion(
                        potions[random.nextInt(potions.length)],
                        value,
                        30 + random.nextInt(71),
                        potionTypes[random.nextInt(potionTypes.length)]
                );
            case 2:
                String[] scrolls = {"Fireball", "Time Stop", "Teleport", "Resurrection"};
                return new Scroll(
                        scrolls[random.nextInt(scrolls.length)],
                        value,
                        scrolls[random.nextInt(scrolls.length)],
                        20 + random.nextInt(41)
                );
            case 3:
                return new Ring(
                        "Ring of " + (random.nextBoolean() ? "Power" : "Wisdom"),
                        value,
                        5 + random.nextInt(16),
                        3 + random.nextInt(13)
                );
            default:
                String[] armors = {"Dragon Scale", "Mithril Plate", "Shadow Leather"};
                String[] armorTypes = {"Heavy", "Medium", "Light"};
                return new Armor(
                        armors[random.nextInt(armors.length)],
                        value,
                        10 + random.nextInt(31),
                        armorTypes[random.nextInt(armorTypes.length)]
                );
        }
    }
}