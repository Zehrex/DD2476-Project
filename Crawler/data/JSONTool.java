9
https://raw.githubusercontent.com/jerryygit/zrDSL-flink/master/src/main/java/tools/JSONTool.java
package tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONTool {
    public static JSONObject parseObj(String str) {
        return JSONObject.parseObject(str);
    }

    public static JSONArray parseArray(String str) {
        return JSONArray.parseArray(str);
    }
}
