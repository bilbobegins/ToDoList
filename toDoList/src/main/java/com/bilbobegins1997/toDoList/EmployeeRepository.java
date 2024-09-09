package com.bilbobegins1997.toDoList;

import org.springframework.data.jpa.repository.JpaRepository;

 interface EmployeeRepository extends JpaRepository<Type, Long> {

}