package com.github.ylinker.finalreality.model.character.player.mage;

import com.github.ylinker.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A class that holds all the information of a single black mage character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker Groisman
 */
public class BlackMage extends AbstractMage {

    /**
     * Creates a new black mage character.
     *
     * @param name
     *     the character's name
     * @param mana
     *     the character's mana
     * @param health
     *      The character's initial health points
     * @param attack
     *      The character's initial attack
     * @param defense
     *      The character's initial defense
     */
    public BlackMage(@NotNull String name, final int health,
                     final int attack, final int defense,
                     final int mana) {
        super(name, health, attack, defense, mana);
    }

    /**
     * Makes a unique hashcode for this knight
     * @return an int that is the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    /**
     * Test another object to see if it is equal to this black mage
     * @param o
     *    The other object
     * @return true if it is equal, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlackMage)) {
            return false;
        }
        final BlackMage that = (BlackMage) o;
        return getName().equals(that.getName());
    }

    @Override
    public boolean equip(IWeapon weapon) {
        if(isAlive()) {
            IWeapon myWeapon = weapon.equipToBlackMage();
            if (myWeapon != null) {
                this.equippedWeapon = myWeapon;
                this.attack = this.baseAttack + myWeapon.getDamage();
                this.weight = this.baseWeight + myWeapon.getWeight();
                return true;
            }
        }
        return false;
    }
}
