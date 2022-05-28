package mask.test;


import com.alibaba.fastjson.serializer.ValueFilter;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

public class Fastjson1BeanValueFilter implements ValueFilter {


    private List<String> fastjsonKey =new ArrayList<>();


    @Override
    public Object process(Object object, String filedName, Object fieldValue) {
        if (fieldValue == null) {
            return fieldValue;
        }
        if (!(fieldValue instanceof String) || ((String) fieldValue).length() == 0) {
            return fieldValue;
        }
        Object maskedValue = keyDataMask(filedName, fieldValue);
        return maskedValue;
    }

    public Object keyDataMask(String filedName, Object fieldValue) {
        if (fastjsonKey != null && fastjsonKey.contains(filedName)) {
            return "sha256@" + DigestUtils.sha256Hex((String) fieldValue);
        }
        return fieldValue;
    }


    public void addFastjsonKey(String key){
        this.fastjsonKey.add(key);
    }

    public List<String> getFastjsonKey() {
        return fastjsonKey;
    }

    public void setFastjsonKey(List<String> fastjsonKey) {
        this.fastjsonKey = fastjsonKey;
    }
}
