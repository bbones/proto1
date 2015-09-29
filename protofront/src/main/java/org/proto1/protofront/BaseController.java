package org.proto1.protofront;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {

	private final Logger log = LoggerFactory.getLogger(BaseController.class);

	@InitBinder
	public void binder(WebDataBinder binder) {
    	log.debug("Custom date editor init");
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
		    public void setAsText(String value) {
		    	log.debug("Custom date editor");
		        try {
		            setValue(new SimpleDateFormat("dd.MM.yyyy").parse(value));
		        } catch(ParseException e) {
		            setValue(null);
		        }
		    }

		    public String getAsText() {
		        return new SimpleDateFormat("dd.MM.yyyy").format((Date) getValue());
		    }        

		});	    // as shown above
	}

}
