package com.bilbobegins1997.toDoList;

public class ToDoListNotFoundException extends RuntimeException  {


    ToDoListNotFoundException(Long id){
        super("Could not find employee " + id);
    }

}
