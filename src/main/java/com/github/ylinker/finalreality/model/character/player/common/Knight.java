package com.github.ylinker.finalreality.model.character.player.common;

import com.github.ylinker.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.ylinker.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A class that holds all the information of a single knight character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker Groisman
 */
public class Knight extends AbstractPlayerCharacter {

    /**
     * Creates a new knight character.
     *
     * @param name
     *     the character's name
     * @param health
     *      The character's initial health points
     * @param attack
     *      The character's initial attack
     * @param defense
     *      The character's initial defense
     */
    public Knight(@NotNull String name, final int health, final int attack, final int defense) {
        super(name, health, attack, defense);
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
     * Test another object to see if it is equal to this knight
     * @param o
     *    The other object
     * @return true if it is equal, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knight)) {
            return false;
        }
        final Knight that = (Knight) o;
        return getName().equals(that.getName());
    }

    @Override
    public boolean equip(IWeapon weapon) {
        if(isAlive()) {
            IWeapon myWeapon = weapon.equipToKnight();
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
