10
https://raw.githubusercontent.com/pkxing/CodeGenerator/master/CodeGenerator/src/main/java/com/pkx/code/build/PojoBuilder.java
package com.pkx.code.build;

import java.util.Map;

/****
 * @Author: PKXING
 * @Description:Pojo构建
 * @Date  PKXING 19:13
 *****/
public class PojoBuilder {


    /***
     * 构建Pojo
     * @param dataModel
     */
    public static void builder(Map<String,Object> dataModel){
        // 生成Pojo层文件
        BuilderFactory.builder(dataModel,
                "/template/pojo",
                "Pojo.java",
                TemplateBuilder.PACKAGE_POJO,
                ".java");
    }

}
