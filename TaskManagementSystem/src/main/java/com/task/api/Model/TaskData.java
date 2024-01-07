package com.task.api.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tasks")
public class TaskData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int task_id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="date_created")
	private LocalDate date_created;
	
	@Column(name="due_date")
	private LocalDate due_date;
	
	@Column(name="status")
	private String status;
	
	
	public TaskData(int task_id, String title, String description, LocalDate date_created, LocalDate due_date,
			String status) {
		super();
		this.task_id = task_id;
		this.title = title;
		this.description = description;
		this.date_created = date_created;
		this.due_date = due_date;
		this.status = status;
	}

	public TaskData() {
		super();
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate_created() {
		return date_created;
	}

	public void setDate_created(LocalDate date_created) {
		this.date_created = date_created;
	}

	public LocalDate getDue_date() {
		return due_date;
	}

	public void setDue_date(LocalDate due_date) {
		this.due_date = due_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	@Override
	public String toString() {
		return "Task_Data [task_id=" + task_id + ", title=" + title + ", description=" + description + ", date_created="
				+ date_created + ", due_date=" + due_date + ", status=" + status + "]";
	}




}
