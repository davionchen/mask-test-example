package mask.test;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Fastjson2PathDataMasker {
    
    private List<JSONPath> maskJsonPaths =new ArrayList<>(16) ;

    public void addJSONPath(String s) {
         this.maskJsonPaths.add(JSONPath.of(s));
    }
    public List<JSONPath> getMaskJsonPaths() {
        return maskJsonPaths;
    }

    public void setMaskJsonPaths(List<JSONPath> maskJsonPaths) {
        this.maskJsonPaths = maskJsonPaths;
    }

    public String mask(String jsonStr) {
        if(maskJsonPaths==null || maskJsonPaths.isEmpty()){
            return jsonStr;
        }
        JSONObject object = JSON.parseObject(jsonStr);

        for (JSONPath jsonPath : maskJsonPaths) {
            jsonPath.setCallback(object,  new Function<String,String>() {
                @Override
                public String apply(String s) {
                    return "sha256@" + DigestUtils.sha256Hex( s);
                }
            });
        }
        return object.toString();
    }
}

