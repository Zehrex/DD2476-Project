2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/chainofresponsibility/VideoApprover.java
package com.wz.behavioral.chainofresponsibility;

/**
 * @author 隔壁老王
 * @create 2020-05-08 20:23
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体处理者角色：视频审批者
public class VideoApprover extends Approver {

    @Override
    public void deploy(Course course) {
        if(course.getVideo() != null && !"".equals(course.getVideo())){
            System.out.println(course.getName() + "含有视频，视频审核OK");
            if(approver != null){
                approver.deploy(course);
            }
        }else{
            System.out.println(course.getName() + "不含有视频，视频审核不通过");
            return;
        }
    }
}
