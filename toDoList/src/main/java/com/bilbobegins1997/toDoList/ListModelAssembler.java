package com.bilbobegins1997.toDoList;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ListModelAssembler implements RepresentationModelAssembler<ToDoList, EntityModel<ToDoList>> {

    @Override
    public EntityModel<ToDoList> toModel(ToDoList list) {

        return EntityModel.of(list, //
                linkTo(methodOn(EmployeeController.class).one(list.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("list"));
    }
}
