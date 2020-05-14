11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-common/src/main/java/com/yf/mtcc/common/domain/MtccTransaction.java
package com.yf.mtcc.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: 记录事务日志的bean
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MtccTransaction {

    private String transactionId;

    private Integer phase;

    private Integer role;

    private Integer retryTimes;

    private Integer version;

    private String targetClass;

    private String targetMethod;

    private String confirmMethod;

    private String cancelMethod;

    private Date createTime;

    private Date updateTime;

    private List<MtccParticipant> participantList;


    public MtccTransaction(String transId){
        this.transactionId = transId;
        participantList = new ArrayList<>();
    }


    public void addParticipant(final MtccParticipant participant){
        if(participantList == null){
            participantList = new ArrayList<>();
        }
        participantList.add(participant);
    }

}
