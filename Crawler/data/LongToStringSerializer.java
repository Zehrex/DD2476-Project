137
https://raw.githubusercontent.com/201206030/novel-plus/master/novel-admin/src/main/java/com/java2nb/common/jsonserializer/LongToStringSerializer.java
package com.java2nb.common.jsonserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class LongToStringSerializer extends JsonSerializer<Long> {
    @Override
    public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if(value != null){
            jsonGenerator.writeString(value+"");
        }else{
            jsonGenerator.writeNull();
        }

    }
}
