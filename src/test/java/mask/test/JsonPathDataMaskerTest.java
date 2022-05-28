package mask.test;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonPathDataMaskerTest {

    public static final int TIMES = 1000000;

    @Test
    void maskJsonField() {

        TestBean testBean = new TestBean();
        testBean.setAbc("fasd");

        TestSubElement test12 =new TestSubElement();
        test12.setAbc("dfadf");
        testBean.setTestList(Arrays.asList(test12));


        Fastjson1BeanValueFilter dataMasker =new Fastjson1BeanValueFilter();

        List<String> jsonKeys = new ArrayList();
        jsonKeys.add("mobile");
        jsonKeys.add("email");
        jsonKeys.add("userName");
        jsonKeys.add("crdNm");
        jsonKeys.add("dbtNm");
        jsonKeys.add("acctNo");
        jsonKeys.add("acctId");
        jsonKeys.add("crdActId");
        jsonKeys.add("dbtAcctId");
        jsonKeys.add("fullNm");
        jsonKeys.add("displayNm");
        jsonKeys.add("dbtId");
        jsonKeys.add("cardNo");
        jsonKeys.add("abc");
        dataMasker.setFastjsonKey(jsonKeys);

        Long start  = System.currentTimeMillis();
        for(int i =0;i<TIMES;i++){
            JSONObject.toJSONString(testBean,dataMasker);
        }
        System.out.println( "maskJsonField cost " +(System.currentTimeMillis()-start));
    }

    @Test
    void maskFastJsonPath() {
        TestBean testBean = new TestBean();
        testBean.setAbc("fasd");

        TestSubElement test12 =new TestSubElement();
        test12.setAbc("dfadf");
        testBean.setTestList(Arrays.asList(test12));

        String jsonStr = JSON.toJSONString(testBean);
        Fastjson2PathDataMasker masker = new Fastjson2PathDataMasker();

        masker.addJSONPath("$..mobile");
        masker.addJSONPath("$..email");
        masker.addJSONPath("$..userName");
        masker.addJSONPath("$..crdNm");
        masker.addJSONPath("$..dbtNm");
        masker.addJSONPath("$..acctNo");
        masker.addJSONPath("$..acctId");
        masker.addJSONPath("$..crdActId");
        masker.addJSONPath("$..dbtAcctId");
        masker.addJSONPath("$..fullNm");
        masker.addJSONPath("$..displayNm");
        masker.addJSONPath("$..dbtId");
        masker.addJSONPath("$..cardNo");
        masker.addJSONPath("$..abc");

        Long start  = System.currentTimeMillis();
        for(int i =0;i<TIMES;i++){
            masker.mask(jsonStr);
        }
        System.out.println( "maskFastJsonPath cost " +(System.currentTimeMillis()- start));


    }
}