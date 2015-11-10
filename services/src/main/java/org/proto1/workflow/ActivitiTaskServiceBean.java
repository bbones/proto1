/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
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
 * GNU v2 license text in root directory of project
 *******************************************************************************/
package org.proto1.workflow;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ActivitiTaskServiceBean implements ActivitiTaskService {
	private final Logger log = LoggerFactory
			.getLogger(ActivitiTaskServiceBean.class);


	@Autowired
	private TaskService taskService;

	public List<Task> getUserTasks() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		log.debug(authentication.getName());
		List<Task> tasks = taskService.createTaskQuery()
				.taskCandidateOrAssigned(authentication.getName())
				.taskCandidateGroupIn(getRoles(authentication))
				.orderByTaskCreateTime().desc().list();
		return tasks;

	}

	private List<String> getRoles(Authentication authentication) {
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority ga : authentication.getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		return roles;
	}

	public void setAccepted(String taskId, boolean accepted) {
		Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
		taskService.setVariable(taskId, task.getTaskDefinitionKey()+"Accepted", accepted);
		taskService.complete(taskId);
		
	}

}
