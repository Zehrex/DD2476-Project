10
https://raw.githubusercontent.com/pkxing/CodeGenerator/master/CodeGenerator/src/main/resources/template/dao/Mapper.java
package ${package_mapper};
import ${package_pojo}.${Table};
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author: PKXING
 * @Description:${Table}的Dao
 *****/
public interface ${Table}Mapper extends Mapper<${Table}> {
}
