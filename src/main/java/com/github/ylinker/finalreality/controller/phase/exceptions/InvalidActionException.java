package com.github.ylinker.finalreality.controller.phase.exceptions;

/**
 * @author Yuval Linker
 *
 * Exception that alerts that an invalid action is being taken.
 * This means an action that can't be done in the current phase.
 */
public class InvalidActionException extends Exception {
    public InvalidActionException(String msg) {
        super(msg);
    }
}
