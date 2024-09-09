package com.bilbobegins1997.toDoList;

public class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(Long id) {
        super("Could not find Type " + id);
    }
}
