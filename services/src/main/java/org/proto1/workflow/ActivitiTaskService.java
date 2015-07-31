package org.proto1.workflow;

import java.util.List;

import org.activiti.engine.task.Task;

public interface ActivitiTaskService {
	List<Task> getUserTasks();

	void setAccepted(String taskId, boolean accepted);
}
