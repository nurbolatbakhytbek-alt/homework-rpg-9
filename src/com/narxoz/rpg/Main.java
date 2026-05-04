package com.narxoz.rpg;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;
import com.narxoz.rpg.visitor.PowerRatingVisitor;
import com.narxoz.rpg.visitor.StatsDisplayVisitor;
import com.narxoz.rpg.visitor.ValueCalculatorVisitor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║     CHRONOMANCER'S VAULT - Homework 9             ║");
        System.out.println("║     Visitor Pattern + Memento Pattern             ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter your hero's name: ");
        String name = scanner.nextLine();

        System.out.println("\nChoose your class:");
        System.out.println("1. Warrior (High HP, Medium Mana, Medium Attack)");
        System.out.println("2. Mage (Low HP, High Mana, High Attack)");
        System.out.println("3. Paladin (Medium HP, Medium Mana, High Defense)");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();

        Hero hero;
        switch (choice) {
            case 1:
                hero = new Hero(name, 350, 100, 45);
                System.out.println("\n⚔️ Warrior chosen! High endurance.");
                break;
            case 2:
                hero = new Hero(name, 200, 200, 60);
                System.out.println("\n🔮 Mage chosen! Powerful spells, fragile body.");
                break;
            case 3:
                hero = new Hero(name, 300, 120, 40);
                System.out.println("\n🛡️ Paladin chosen! Balanced protector.");
                break;
            default:
                hero = new Hero(name, 250, 120, 45);
                System.out.println("\n⚔️ Default hero created.");
        }

        System.out.println("\nPress ENTER to enter the Chronomancer's Vault...");
        scanner.nextLine();
        scanner.nextLine();

        System.out.println("\n📚 VISITOR PATTERN DEMONSTRATION");
        System.out.println("Creating sample artifacts to showcase Visitor pattern...");

        Inventory demoInventory = new Inventory();
        demoInventory.add(new Weapon("Excalibur", 500, 45, "Sword"));
        demoInventory.add(new Potion("Health Potion", 100, 80, "Healing"));
        demoInventory.add(new Scroll("Time Scroll", 300, "Haste", 35));
        demoInventory.add(new Ring("Ring of Protection", 250, 10, 8));
        demoInventory.add(new Armor("Dragon Plate", 600, 40, "Heavy"));

        System.out.println("\n--- StatsDisplayVisitor ---");
        StatsDisplayVisitor statsVisitor = new StatsDisplayVisitor();
        demoInventory.accept(statsVisitor);

        System.out.println("\n--- ValueCalculatorVisitor ---");
        ValueCalculatorVisitor valueVisitor = new ValueCalculatorVisitor();
        demoInventory.accept(valueVisitor);
        System.out.println("Total Inventory Value: " + valueVisitor.getTotalValue() + " gold");

        System.out.println("\n--- PowerRatingVisitor ---");
        PowerRatingVisitor powerVisitor = new PowerRatingVisitor();
        demoInventory.accept(powerVisitor);
        System.out.printf("Average Power Rating: %.1f%n", powerVisitor.getAveragePower());

        System.out.println("\n" + "=".repeat(60));
        System.out.println("🎮 STARTING VAULT EXPEDITION");
        System.out.println("=".repeat(60));

        ChronomancerEngine engine = new ChronomancerEngine(hero);
        VaultRunResult result = engine.runVault();

        result.display();

        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("📊 PATTERN SUMMARY");
        System.out.println("═══════════════════════════════════════════════════");

        System.out.println("\n🔹 VISITOR PATTERN:");
        System.out.println("   - Artifact hierarchy with accept() method");
        System.out.println("   - Three visitors: StatsDisplayVisitor, ValueCalculatorVisitor, PowerRatingVisitor");
        System.out.println("   - No instanceof checks needed");
        System.out.println("   - Easy to add new operations without modifying artifact classes");

        System.out.println("\n🔸 MEMENTO PATTERN:");
        System.out.println("   - HeroMemento captures complete hero state");
        System.out.println("   - Caretaker manages save points (Stack)");
        System.out.println("   - Automatic saves before each chamber");
        System.out.println("   - Restores state on trap failure or death");
        System.out.println("   - Times rewound: " + result.getTimesRewound());

        System.out.println("\n✅ Vault " + (result.isCompleted() ? "COMPLETED" : "FAILED"));
        System.out.println("   Artifacts appraised: " + result.getArtifactsAppraised());
        System.out.println("   Total value gained: " + result.getTotalValue() + " gold");

        scanner.close();
    }
}