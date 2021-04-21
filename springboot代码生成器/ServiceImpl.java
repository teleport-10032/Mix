package teleport;

import java.io.IOException;
import java.util.ArrayList;

public class ServiceImpl {
    public ServiceImpl(String workspace, String packageName, String dirName, String fileName, String pojoName, String member, String keyMember) throws IOException {
        new DirInit(workspace,dirName);
        new Writer(createContent(packageName,dirName,pojoName,member,keyMember),workspace+"/"+dirName+"/"+fileName);
    }
    public String createContent(String packageName,String dirName,String pojoName,String member,String keyMember)
    {
        String arr[] = member.split(";");
        int arrLen = arr.length;
        StringBuilder str  = new StringBuilder("");
        str.append("package ").append(packageName).append(".").append(dirName).append(";\n");
        ArrayList<String> mem = new ArrayList<>();
        for(int i = 0 ; i < arrLen; i ++) {
            //mem[0]=int; mem[1]=id;
            String tmp[] = arr[i].split(" ");
            int tmpLen = tmp.length;
            for(int j = 0 ; j < tmpLen ; j++)
                mem.add(tmp[j]);
        }
        int memLen = mem.size();
        int pojoNameLen = pojoName.length();
        String p1 = pojoName.substring(0,1);
        String p2 = pojoName.substring(1,pojoNameLen);
        String upperPojoName = p1.toUpperCase() + p2;


        str.append("@Service\n" + "public class ").append(pojoName).append("ServiceImpl implements ").append(pojoName).append("Service {\n").append("\n").append("    @Autowired\n").append("    ").append(pojoName).append("Mapper ").append(pojoName).append("MapperObject;\n");

        str.append("    @Override\n" + "    public String get").append(upperPojoName).append("List(int page, int pre, String key) throws JsonProcessingException {\n").append("        ObjectMapper mapper = new ObjectMapper();\n").append("        HashMap s = new HashMap();");
        str.append("int start = pre * (page - 1);\n" + "        int num = pre;" + "\n" + "        List<").append(pojoName).append("> list = ").append(pojoName).append("MapperObject.get").append(upperPojoName).append("List(start,num,key);\n").append("        s.put(\"data\",list);\n").append("        s.put(\"total\",").append(pojoName).append("MapperObject.get").append(upperPojoName).append("Num(key));\n").append("        s.put(\"error\",\"0\");");
        str.append("\n" +
                "        return mapper.writeValueAsString(s);\n" +
                "    }\n");

        str.append("    @Override\n" + "    public String get").append(upperPojoName).append("ById(int id) throws JsonProcessingException {\n").append("    ObjectMapper mapper = new ObjectMapper();\n").append("    HashMap s = new HashMap();\n");
        str.append("    s.put(\"data\",").append(pojoName).append("MapperObject.get").append(upperPojoName).append("ById(id));\n").append("        s.put(\"error\",\"0\");");
        str.append("\n" +
                "        return mapper.writeValueAsString(s);\n" +
                "    }\n");
        str.append("    @Override\n" +
                "    public String add"+upperPojoName+"(");
        for(int i = 0 ; i < memLen ; i ++) {
            if(i % 2 != 0) {
                if(i != memLen - 1){
                    str.append(mem.get(i - 1)).append(" ").append(mem.get(i)).append(",");
                }
                else{
                    str.append(mem.get(i - 1)).append(" ").append(mem.get(i));
                }
            }
        }
        str.append(") throws JsonProcessingException {\n" +
                "        ObjectMapper mapper = new ObjectMapper();\n" +
                "        HashMap s = new HashMap();\n");
        str.append("            if("+pojoName+"MapperObject.add"+upperPojoName+"(");
        for(int i = 0 ; i < memLen ; i ++) {
            if(i % 2 != 0) {
                if(i != memLen - 1){
                    str.append(mem.get(i)).append(",");
                }
                else{
                    str.append(mem.get(i));
                }
            }
        }
        str.append(") == 1)\n" +
                "                s.put(\"error\",\"0\");\n" +
                "            else\n" +
                "                s.put(\"error\",\"-2\");");
        str.append("\n" +
                "        return mapper.writeValueAsString(s);\n" +
                "    }\n");

        str.append("    @Override\n" +
                "    public String update"+upperPojoName+"ById(");
        for(int i = 0 ; i < memLen ; i ++) {
            if(i % 2 != 0) {
                if(i != memLen - 1){
                    str.append(mem.get(i - 1)).append(" ").append(mem.get(i)).append(",");
                }
                else{
                    str.append(mem.get(i - 1)).append(" ").append(mem.get(i));
                }
            }
        }
        str.append(") throws JsonProcessingException {\n" +
                "        ObjectMapper mapper = new ObjectMapper();\n" +
                "        HashMap s = new HashMap();\n");
        str.append("            if("+pojoName+"MapperObject.update"+upperPojoName+"ById(");
        for(int i = 0 ; i < memLen ; i ++) {
            if(i % 2 != 0) {
                if(i != memLen - 1){
                    str.append(mem.get(i)).append(",");
                }
                else{
                    str.append(mem.get(i));
                }
            }
        }
        str.append(") == 1)\n" +
                "                s.put(\"error\",\"0\");\n" +
                "            else\n" +
                "                s.put(\"error\",\"-2\");");
        str.append("\n" +
                "        return mapper.writeValueAsString(s);\n" +
                "    }\n");

        str.append("    @Override\n" +
                "    public String delete"+upperPojoName+"ById(int id) throws JsonProcessingException {\n" +
                "        ObjectMapper mapper = new ObjectMapper();\n" +
                "        HashMap s = new HashMap();\n");
        str.append("            if("+pojoName+"MapperObject.delete"+upperPojoName+"ById(id) == 1)\n" +
                "                s.put(\"error\",\"0\");\n" +
                "            else\n" +
                "                s.put(\"error\",\"-2\");");
        str.append("\n" +
                "        return mapper.writeValueAsString(s);\n" +
                "    }\n}");

        return str.toString();
    }
}
