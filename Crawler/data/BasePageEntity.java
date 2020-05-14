10
https://raw.githubusercontent.com/IzzyPrime/Admin/master/src/main/java/com/kalvin/kvf/common/entity/BasePageEntity.java
package com.kalvin.kvf.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BasePageEntity {

    @TableField(exist = false)
    private int current = 1;

    @TableField(exist = false)
    private int size = 10;

}
