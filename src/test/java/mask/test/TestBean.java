package mask.test;


import java.util.List;

public class TestBean {

    
    private String abc;

    private List<TestSubElement> testList;
    private String userName;

    public String getAbc() {
        return abc;
    }

    public void setAbc(String abc) {
        this.abc = abc;
    }

    public List<TestSubElement> getTestList() {
        return testList;
    }

    public void setTestList(List<TestSubElement> testList) {
        this.testList = testList;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}
