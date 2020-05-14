12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/model/process/wizard/SetExecutorsStep.java
package ru.bgcrm.model.process.wizard;

import ru.bgcrm.util.ParameterMap;

public class SetExecutorsStep extends Step {
    public SetExecutorsStep(ParameterMap config) {
        super(config);
    }

    @Override
    public String getJspFile() {
        return "/WEB-INF/jspf/usermob/process/process/wizard/step_set_executors.jsp";
    }

    @Override
    public SetExecutorsStepData newStepData(WizardData data) {
        return new SetExecutorsStepData(this, data);
    }
}