package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.weapon.IWeapon;
import com.github.ylinker.finalreality.model.weapon.Weapon;

/**
 * This represents a character that a player can control.
 * This is different from what the CPU can control
 *
 * @author Yuval Linker
 */
public interface IPlayerCharacter {

    /**
     * Equips a weapon to the character.
     */
    void equip(IWeapon weapon);

    /**
     * Return this character's equipped weapon.
     */
    IWeapon getEquippedWeapon();
}
