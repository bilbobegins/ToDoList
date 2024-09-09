package com.bilbobegins1997.toDoList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class TypeModelAssembler implements RepresentationModelAssembler<Type, EntityModel<Type>> {

    @Override
    public EntityModel<Type> toModel(Type employee) {

        return EntityModel.of(employee, //
                linkTo(methodOn(TypeController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(TypeController.class).all()).withRel("employees"));
    }
}
