package com.bilbobegins1997.toDoList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
class TypeController {

    private final EmployeeRepository repository;
    private final TypeModelAssembler assembler;

    @GetMapping("/")
    public String chatroom() {
        return "Welcome to the chatroom !";
    }

    TypeController(EmployeeRepository repository, TypeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/todo/list/type")
    CollectionModel<EntityModel<Type>> all() {
        List<EntityModel<Type>> employees = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(TypeController.class).all()).withSelfRel());

    }


    @PostMapping("/todo/list/type")
    Type newEmployee(@RequestBody Type newEmployee) {
        return repository.save(newEmployee);
    }

    // Single item
    @GetMapping("/todo/list/type/{id}")
    EntityModel<Type> one(@PathVariable Long id) {
        Type employee = repository.findById(id) //
                .orElseThrow(() -> new TypeNotFoundException(id));

        return assembler.toModel(employee);

    }



    @PutMapping("/todo/list/type/{id}")
    Type replaceEmployee(@RequestBody Type newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setType(newEmployee.getType());
                    return repository.save(employee);
                })
                .orElseGet(() -> repository.save(newEmployee));
    }

    @DeleteMapping("/todo/list/type/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
