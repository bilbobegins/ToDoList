package com.bilbobegins1997.toDoList;

import org.springframework.data.jpa.repository.JpaRepository;

 interface TaskRepository extends JpaRepository<Task, Long> {

}