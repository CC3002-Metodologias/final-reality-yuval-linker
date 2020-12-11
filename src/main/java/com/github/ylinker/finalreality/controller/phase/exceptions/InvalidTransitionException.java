package com.github.ylinker.finalreality.controller.phase.exceptions;

/**
 * @author Yuval Linker
 * An exception that alerts that an invalid transition is taking place.
 * This means going from one phase to another that isn't supposed to go to.
 */
public class InvalidTransitionException extends Exception {
    public InvalidTransitionException(String msg) {
        super(msg);
    }
}
