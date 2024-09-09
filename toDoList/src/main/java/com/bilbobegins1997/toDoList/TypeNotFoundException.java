package com.bilbobegins1997.toDoList;

public class TypeNotFoundException extends RuntimeException {

    TypeNotFoundException(Long id) {
        super("Could not find Type " + id);
    }
}
