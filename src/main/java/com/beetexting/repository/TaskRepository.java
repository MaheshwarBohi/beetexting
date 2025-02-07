package com.beetexting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beetexting.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
