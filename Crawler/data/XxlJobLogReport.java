1
https://raw.githubusercontent.com/miaoo92/xxl-job-mongo/master/src/main/java/com/avon/rga/core/model/XxlJobLogReport.java
package com.avon.rga.core.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "xxlJobLogReport")
public class XxlJobLogReport {

    @Id
    private int id;

    private Date triggerDay;

    private int runningCount;
    private int sucCount;
    private int failCount;

}
