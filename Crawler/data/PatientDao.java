2
https://raw.githubusercontent.com/chandureddys/spring-boot-doc-appointment/master/src/main/java/com/docappointment/project/dao/PatientDao.java
package com.docappointment.project.dao;

import com.docappointment.project.entity.Patient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PatientDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public boolean savePatientInfo(Patient patient){
        entityManager.persist(patient);
        return true;
    }
}
