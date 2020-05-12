2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/chainofresponsibility/NoteApprover.java
package com.wz.behavioral.chainofresponsibility;

/**
 * @author 隔壁老王
 * @create 2020-05-08 20:16
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//具体处理者角色：笔记审批者
public class NoteApprover extends Approver {

    @Override
    public void deploy(Course course) {
        if(course.getNote() != null && !"".equals(course.getNote())){
            System.out.println(course.getName() + "含有笔记，笔记审核OK");
            if(approver != null){
                approver.deploy(course);
            }
        }else{
            System.out.println(course.getName() + "不含有笔记，笔记审核不通过");
            return;
        }
    }
}
