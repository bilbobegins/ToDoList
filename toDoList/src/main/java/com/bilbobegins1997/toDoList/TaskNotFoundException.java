package com.bilbobegins1997.toDoList;

public class TaskNotFoundException extends RuntimeException {

    TaskNotFoundException(Long id) {
        super("Could not find Type " + id);
    }
}
