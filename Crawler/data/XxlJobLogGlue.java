1
https://raw.githubusercontent.com/miaoo92/xxl-job-mongo/master/src/main/java/com/avon/rga/core/model/XxlJobLogGlue.java
package com.avon.rga.core.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * xxl-job log for glue, used to track job code process
 * @author xuxueli 2016-5-19 17:57:46
 */
@Data
@Document(collection = "xxlJobLogGlue")
public class XxlJobLogGlue {

	private int id;
	private int jobId;					// 任务主键ID
	private String glueType;		// GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum
	private String glueSource;
	private String glueRemark;
	private Date addTime;
	private Date updateTime;

}
