package teleport;

import java.io.IOException;
import java.util.ArrayList;

public class MapperXml {

    public MapperXml(String workspace, String packageName, String dirName, String fileName, String pojoName, String member, String keyMember) throws IOException {
        new DirInit(workspace,dirName);
        new Writer(createContent(packageName,pojoName,member,keyMember),workspace+"/"+dirName+"/"+fileName);
    }

    public String createContent(String packageName,String pojoName,String member,String keyMember)
    {
        String arr[] = member.split(";");
        int arrLen = arr.length;
        StringBuilder str  = new StringBuilder("");
        str.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE mapper\n" +
                "        PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n" +
                "        \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
                "<mapper namespace=\""+packageName+".Mapper."+pojoName+"Mapper\">\n");
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
        str.append("    <select id=\"get").append(upperPojoName).append("List\" resultType=\"").append(pojoName).append("\">\n");
        str.append("        select * from ").append(pojoName).append(" where ").append(keyMember).append(" like \"%\"#{").append("key").append("}\"%\" order by id limit #{start},#{num}\n");
        str.append("    </select>\n");
        str.append("    <select id=\"get").append(upperPojoName).append("Num\" resultType=\"int\">\n");
        str.append("        select count(*) from ").append(pojoName).append(" where ").append(keyMember).append(" like \"%\"#{").append("key").append("}\"%\"\n");
        str.append("    </select>\n");
        str.append("    <select id=\"get").append(upperPojoName).append("ById\" resultType=\"").append(pojoName).append("\">\n");
        str.append("        select * from ").append(pojoName).append(" where id=#{id}\n");
        str.append("    </select>\n");
        str.append("    <insert id=\"add").append(upperPojoName).append("\" parameterType=\"").append(pojoName).append("\">\n");
        str.append("        insert into "+pojoName+"(");
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
        str.append(") values(");
        for(int i = 0 ; i < memLen ; i ++) {
            if(i % 2 != 0) {
                if(i != memLen - 1){
                    str.append("#{").append(mem.get(i)).append("},");
                }
                else{
                    str.append("#{").append(mem.get(i)).append("}");
                }
            }
        }
        str.append(")\n");
        str.append("    </insert>\n");
        str.append("    <update id=\"update").append(upperPojoName).append("ById\" parameterType=\"").append(pojoName).append("\">\n");
        str.append("        update ").append(pojoName).append(" set ");
        for(int i = 0 ; i < memLen ; i ++) {
            if(i % 2 != 0 && !mem.get(i).equals("id")) {
                if(i != memLen - 1){
                    str.append(mem.get(i)).append("=#{").append(mem.get(i)).append("},");
                }
                else{
                    str.append(mem.get(i)).append("=#{").append(mem.get(i)).append("}");
                }
            }
        }
        str.append(" where id=#{id}\n");
        str.append("    </update>\n");
        str.append("    <delete id=\"delete").append(upperPojoName).append("ById\" parameterType=\"int\">\n");
        str.append("        delete from ").append(pojoName).append(" where id=#{id}\n");
        str.append("    </delete>\n");
        str.append("</mapper>");
        return str.toString();
    }
}
