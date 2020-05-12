2
https://raw.githubusercontent.com/chandureddys/spring-boot-doc-appointment/master/src/main/java/com/docappointment/project/PatientsController.java
package com.docappointment.project;


import com.docappointment.project.entity.Patient;
import com.docappointment.project.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1")
public class PatientsController {
    @Autowired
    private PatientDao patientDao;

    @RequestMapping(method = RequestMethod.POST, value = "/patient")
    public Boolean insertPatientInfo(@RequestBody Patient patient) {
        return patientDao.savePatientInfo(patient);

    }
}
