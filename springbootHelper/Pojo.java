package springbootHelper;

import java.io.IOException;
import java.util.ArrayList;

public class Pojo {
    public Pojo(String workspace, String packageName, String dirName, String fileName, String pojoName, String member, String keyMember) throws IOException {
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

        str.append("@Data\n" +
                "@NoArgsConstructor\n" +
                "@AllArgsConstructor\n" +
                "public class "+pojoName+"{\n");
        for(int i = 0 ; i < memLen ; i ++) {
            if(i % 2 != 0) {
                str.append("    "+mem.get(i - 1)).append(" ").append(mem.get(i)).append(";\n");
            }
        }
        str.append("}");
        return str.toString();
    }
}
