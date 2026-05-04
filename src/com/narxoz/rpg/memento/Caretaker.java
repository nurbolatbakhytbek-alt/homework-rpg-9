package com.narxoz.rpg.memento;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import java.util.Stack;

public class Caretaker {
    private final Stack<HeroMemento> savePoints = new Stack<>();

    public void save(Hero hero) {
        HeroMemento memento = hero.createMemento();
        savePoints.push(memento);
        System.out.println("[CARETAKER] Save point created. Total saves: " + savePoints.size());
    }

    public boolean restore(Hero hero) {
        if (savePoints.isEmpty()) {
            System.out.println("[CARETAKER] No save points available!");
            return false;
        }

        HeroMemento memento = savePoints.pop();
        hero.restoreMemento(memento);
        System.out.println("[CARETAKER] State restored. Remaining saves: " + savePoints.size());
        return true;
    }

    public boolean hasSavePoints() {
        return !savePoints.isEmpty();
    }

    public void clear() {
        savePoints.clear();
        System.out.println("[CARETAKER] All save points cleared.");
    }

    public int getSaveCount() {
        return savePoints.size();
    }
}