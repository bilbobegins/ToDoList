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
class TaskController {

    private final TaskRepository repository;
    private final TaskModelAssembler assembler;

    @GetMapping("/")
    public String chatroom() {
        return "Welcome to the chatroom !";
    }

    TaskController(TaskRepository repository, TaskModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/todo/list/task")
    CollectionModel<EntityModel<Task>> all() {
        List<EntityModel<Task>> employees = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(TaskController.class).all()).withSelfRel());

    }


    @PostMapping("/todo/list/task")
    Task newEmployee(@RequestBody Task newEmployee) {
        return repository.save(newEmployee);
    }

    // Single item
    @GetMapping("/todo/list/task/{id}")
    EntityModel<Task> one(@PathVariable Long id) {
        Task employee = repository.findById(id) //
                .orElseThrow(() -> new TaskNotFoundException(id));

        return assembler.toModel(employee);

    }



    @PutMapping("/todo/list/task/{id}")
    Task replaceEmployee(@RequestBody Task newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setTask(newEmployee.getTask());
                    return repository.save(employee);
                })
                .orElseGet(() -> repository.save(newEmployee));
    }

    @DeleteMapping("/todo/list/task/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
