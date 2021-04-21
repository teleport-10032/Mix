package teleport;

import java.io.IOException;
import java.util.ArrayList;

public class Mapper {
    public Mapper(String workspace, String packageName, String dirName, String fileName, String pojoName, String member, String keyMember) throws IOException {
        new DirInit(workspace,dirName);
        new Writer(createContent(packageName,dirName,pojoName,member,keyMember),workspace+"/"+dirName+"/"+fileName);
    }
    public String createContent(String packageName,String dirName,String pojoName,String member,String keyMember)
    {
        String arr[] = member.split(";");
        int arrLen = arr.length;
        StringBuilder str  = new StringBuilder("");
        str.append("package ").append(packageName).append(".").append(dirName).append(";\n");
        str.append("@Mapper\n" +
                "@Repository\n");
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

        str.append("public interface ").append(pojoName).append("Mapper {\n");
        str.append("    List<").append(pojoName).append("> get").append(upperPojoName).append("List(@Param(\"start\") int start, @Param(\"num\") int num, @Param(\"key\") String key);\n");
        str.append("    int get").append(upperPojoName).append("Num(@Param(\"key\") String key);\n");
        str.append("    ").append(pojoName).append(" get").append(upperPojoName).append("ById(@Param(\"id\") int id);\n");
        str.append("    int add").append(upperPojoName).append("(");
        for(int i = 0 ; i < memLen ; i ++) {
            if(i % 2 != 0) {
                if(i != memLen - 1){
                    str.append("@Param(\"").append(mem.get(i)).append("\") "+mem.get(i-1)+" ").append(mem.get(i)).append(",");
                }
                else{
                    str.append("@Param(\"").append(mem.get(i)).append("\") "+mem.get(i-1)+" ").append(mem.get(i));
                }
            }
        }
        str.append(");\n");
        str.append("    int update").append(upperPojoName).append("ById(");
        for(int i = 0 ; i < memLen ; i ++) {
            if(i % 2 != 0) {
                if(i != memLen - 1){
                    str.append("@Param(\"").append(mem.get(i)).append("\") "+mem.get(i-1)+" ").append(mem.get(i)).append(",");
                }
                else{
                    str.append("@Param(\"").append(mem.get(i)).append("\") "+mem.get(i-1)+" ").append(mem.get(i));
                }
            }
        }
        str.append(");\n");
        str.append("    int delete"+upperPojoName+"ById(@Param(\"id\") int id);\n");
        str.append("}");

        return str.toString();
    }
}
