package com.task.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.api.Model.TaskData;

public interface TaskManagement_Repo extends JpaRepository<TaskData, Integer> {

}
