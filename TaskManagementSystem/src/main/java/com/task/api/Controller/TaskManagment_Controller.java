package com.task.api.Controller;

import java.time.LocalDate;
//import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.api.Model.TaskData;
import com.task.api.Repository.TaskManagement_Repo;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/tms")
public class TaskManagment_Controller {
	
	@Autowired
	TaskManagement_Repo taskManagementRepo;
	
	 @GetMapping("/tasks")
	    public ResponseEntity<List<TaskData>> getAllTasks() {
		 try
	        {
	            List<TaskData> taskList = new ArrayList<>();
	            taskManagementRepo.findAll().forEach(taskList::add);
	            return new ResponseEntity<>(taskList, HttpStatus.OK);
	        }
	        catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 @GetMapping("/tasks/{task_id}")
		public ResponseEntity<TaskData> getTaskById(@PathVariable int task_id) {
			Optional<TaskData> task = taskManagementRepo.findById(task_id);
			if (task.isPresent()) {
				return new ResponseEntity<TaskData>(task.get(), HttpStatus.FOUND);
			} else {
				return new ResponseEntity<TaskData>(HttpStatus.NOT_FOUND);
			}
		}
	 
	 
	 @PostMapping("/tasks")
	 public ResponseEntity<Object> createTask(@RequestBody TaskData tasksData, HttpServletResponse response) {
	     try {
	         // Save the task to the database
	    	  tasksData.setDate_created(LocalDate.now()); 
	          taskManagementRepo.save(tasksData);

	          return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/tms/tasks").build();
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create task");
	     }
	 }
	 
	 @PutMapping("/tasks/{task_id}")
	 public ResponseEntity<Object> updateTaskById(@PathVariable int task_id, @RequestBody TaskData taskData) {
	     try {
	         Optional<TaskData> taskInfo = taskManagementRepo.findById(task_id);
	         if (taskInfo.isPresent()) {
	             TaskData existingTaskData = taskInfo.get();
	             
	             // Update the existing task data with the new values
	             existingTaskData.setTitle(taskData.getTitle());
	             existingTaskData.setDescription(taskData.getDescription());
	             existingTaskData.setDue_date(taskData.getDue_date());
	             existingTaskData.setStatus(taskData.getStatus());
	             
	             // Save the updated task data
	             taskManagementRepo.save(existingTaskData);
	             // Return a success response with the updated task data
	             return ResponseEntity.status(HttpStatus.OK).header("Location", "/tms/tasks").build();
	         } else {
	             // Task with the specified ID not found
	             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
	         }
	     } catch (Exception e) {
	         // Log the exception for debugging
	         e.printStackTrace();

	         // Return an error response with a meaningful message
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update task");
	     }
	 }

	 @DeleteMapping("/deleteTasks")
	 public ResponseEntity<String> deleteTasksByIds(@RequestBody List<Integer> taskIds) {
	     try {
	    	 System.out.println("Received task IDs: " + taskIds);
	         for (Integer taskId : taskIds) {
	             taskManagementRepo.deleteById(taskId);
	         }
	         return ResponseEntity.ok("Tasks Deleted Successfully");
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete tasks");
	     }
	 }
	 
	 @DeleteMapping("/deleteAllTasks")
	 public ResponseEntity<Object> deleteAllTasks(){
		 try {
			 taskManagementRepo.deleteAll();
			 return ResponseEntity.status(HttpStatus.OK).body("All Tasks Deleted");
		 }
		 catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete tasks");
					
		}
	 }	
}




