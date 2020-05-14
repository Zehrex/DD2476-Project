12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/process/ProcessRequestEvent.java
package ru.bgcrm.event.process;

import ru.bgcrm.event.UserEvent;
import ru.bgcrm.model.process.ProcessType;
import ru.bgcrm.struts.form.DynActionForm;

public class ProcessRequestEvent extends UserEvent {
	private ProcessType processType;
	private String forwardJspName;

	public ProcessRequestEvent(DynActionForm form, ProcessType processType) {
		super(form);
		this.processType = processType;
	}

	public ProcessType getProcessType() {
		return processType;
	}

	public String getForwardJspName() {
		return forwardJspName;
	}

	public void setForwardJspName(String forwardJspName) {
		this.forwardJspName = forwardJspName;
	}
}
