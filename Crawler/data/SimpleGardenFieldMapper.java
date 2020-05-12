2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/mapper/SimpleGardenFieldMapper.java
package com.gardle.service.mapper;

import com.gardle.domain.GardenField;
import com.gardle.service.dto.SimpleGardenFieldDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleGardenFieldMapper {

    GardenField toGardenField(SimpleGardenFieldDTO simpleGardenFieldDTO);

    SimpleGardenFieldDTO toDTO(GardenField gardenField);
}
