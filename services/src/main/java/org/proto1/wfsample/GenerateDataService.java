package org.proto1.wfsample;

import java.util.Calendar;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class GenerateDataService implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		Long someData = Calendar.getInstance().getTimeInMillis() % 2;		
		execution.setVariable("someData", someData);
	}
}
