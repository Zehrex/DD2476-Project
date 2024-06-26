1
https://raw.githubusercontent.com/miaoo92/xxl-job-mongo/master/src/main/java/com/avon/rga/core/model/XxlJobLog.java
package com.avon.rga.core.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * xxl-job log, used to track trigger process
 * @author xuxueli  2015-12-19 23:19:09
 */
@Data
@Document(collection = "xxlJobLog")
public class XxlJobLog {

	private long id;
	
	// job info
	private int jobGroup;
	private int jobId;

	// execute info
	private String executorAddress;
	private String executorHandler;
	private String executorParam;
	private String executorShardingParam;
	private int executorFailRetryCount;
	
	// trigger info
	private Date triggerTime;
	private int triggerCode;
	private String triggerMsg;
	
	// handle info
	private Date handleTime;
	private int handleCode;
	private String handleMsg;

	// alarm info
	private int alarmStatus;


}
