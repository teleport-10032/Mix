package teleport;

public class Main {
    public static void main(String[] args) throws Exception{
        String workspace = "workspace";
        String packageName = "com.teleport.demo3";
        String member = "int id;String name;int age;";
        String pojo = "user";
        String key = "name";
        Mapper mi = new Mapper(workspace,packageName,"Mapper",pojo+"Mapper.java" ,pojo ,member,key);
        MapperXml mx = new MapperXml(workspace,packageName,"Mybatis",pojo+"Mapper.xml" ,pojo ,member,key);
        Service si = new Service(workspace,packageName,"Service",pojo+"Service.java" ,pojo ,member,key);
        ServiceImpl sImpl = new ServiceImpl(workspace,packageName,"ServiceImpl",pojo+"ServiceImpl.java" ,pojo ,member,key);
        Controller controller = new Controller(workspace,packageName,"Controller",pojo+"Controller.java" ,pojo ,member,key);
        Pojo pj = new Pojo(workspace,packageName,"Pojo",pojo+".java" ,pojo ,member,key);
    }
}
