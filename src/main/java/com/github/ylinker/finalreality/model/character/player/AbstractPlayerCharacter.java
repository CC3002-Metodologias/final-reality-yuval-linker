package com.github.ylinker.finalreality.model.character.player;

import com.github.ylinker.finalreality.model.character.AbstractCharacter;
import com.github.ylinker.finalreality.model.character.IPlayerCharacter;
import com.github.ylinker.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * An abstract class that holds the common behaviour of all the playable characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Yuval Linker Groisman
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {

    protected IWeapon equippedWeapon = null;
    protected int attack;
    protected int weight;

    /**
     * The base constructor of every Playable Character.
     * @param name
     *      The character's name
     * @param health
     *      The character's initial health points
     * @param attack
     *      The character's base attack
     * @param defense
     *      The character's initial defense
     */
    protected AbstractPlayerCharacter(@NotNull String name, final int health,
                                      final int attack, final int defense) {
        super(name, health, attack, defense);
        this.attack = attack;
        this.weight = this.baseWeight;
    }

    /**
     * Returns the character's attack. This includes base attack and equipped weapon's damage.
     * @return An int with the character's true attack points
     */
    @Override
    public int getAttack() {
        return attack;
    }

    /**
     * Returns the character's weight. This includes base weight and equipped weapon's weight.
     * @return An int with the character's true weight
     */
    @Override
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the current weapon that the character is equipped with
     * @return the equipped weapon
     */
    @Override
    public IWeapon getEquippedWeapon() {
        return equippedWeapon;
    }

    /**
     * Equips the character with a weapon
     * The weight of the character is now the base weight plus the weapon's weight
     * @param weapon
     *      The weapon that should be equipped
     * @return
     *      True if it equips the weapon, False otherwise
     */
    @Override
    public abstract boolean equip(IWeapon weapon);
}
