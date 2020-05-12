1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/model/single/Student.java
package com.mybatis.model.single;

import lombok.Data;

/*单表操作*/
@Data
public class Student {
    private String id;
    private String name;
    private String sex;
    private String classid;

    public Student(String id, String name, String sex, String classid) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.classid = classid;
    }
}
