/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
package org.proto1.protofront;

import java.util.List;

import org.activiti.engine.task.Task;
import org.activiti.rest.service.api.RestResponseFactory;
import org.activiti.rest.service.api.runtime.task.TaskResponse;
import org.proto1.workflow.ActivitiTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activiti")
public class ActivitiController {
	@Autowired
	ActivitiTaskService activitiTaskService;
	 @Autowired	
	  protected RestResponseFactory restResponseFactory;
	 
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public List<TaskResponse> getTasks() {
		List<Task> tasks = activitiTaskService.getUserTasks();
		/*List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
		for (Task task : tasks) {
			dtos.add(new TaskRepresentation(task.getId(), task.getName()));
		}
		dtos.add(new TaskRepresentation("999", "test"));
		
		return dtos;*/
		return restResponseFactory.createTaskResponseList(tasks);
	}		

	@RequestMapping(value = "/tasks/{taskId}/accepted", method = RequestMethod.POST)
	public void acceptTask(@PathVariable String taskId) {
		activitiTaskService.setAccepted(taskId, true);
	}

	@RequestMapping(value = "/tasks/{taskId}/rejected", method = RequestMethod.POST)
	public void rejectTask(@PathVariable String taskId) {
		activitiTaskService.setAccepted(taskId, false);
	}

	static class TaskRepresentation {

		private String id;
		private String name;

		public TaskRepresentation(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

}
