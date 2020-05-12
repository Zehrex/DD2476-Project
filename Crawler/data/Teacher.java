1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/model/single/Teacher.java
package com.mybatis.model.single;

import lombok.Data;

/*单表操作*/
@Data
public class Teacher {
    private String teacherId;
    private String teacherName;
    private String teacherSex;
    private String teacherAddress;

    public Teacher(String teacherId, String teacherName, String teacherSex, String teacherAddress) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherSex = teacherSex;
        this.teacherAddress = teacherAddress;
    }
}
