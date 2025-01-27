package com.github.ylinker.finalreality.model.character;

import com.github.ylinker.finalreality.model.weapon.IWeapon;

/**
 * This represents a character that a player can control.
 * This is different from what the CPU can control
 *
 * @author Yuval Linker
 */
public interface IPlayerCharacter extends ICharacter {

    /**
     * Equips a weapon to the character.
     * @return
     */
    boolean equip(IWeapon weapon);

    /**
     * Return this character's equipped weapon.
     */
    IWeapon getEquippedWeapon();
}
