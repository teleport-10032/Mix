package teleport;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    public Controller(String workspace, String packageName, String dirName, String fileName, String pojoName, String member, String keyMember) throws IOException {
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


        str.append("@RestController\n" +
                "public class "+pojoName+"Controller {\n" +
                "\n" +
                "    @Autowired\n" +
                "    "+pojoName+"Service "+pojoName+"ServiceObject;\n");

        str.append("    @GetMapping(\"get"+upperPojoName+"List\")\n" +
                "    public String get"+upperPojoName+"List(@RequestParam(\"page\") int page, @RequestParam(\"pre\") int pre,@RequestParam(\"key\") String key)\n" +
                "            throws JsonProcessingException {\n" +
                "        return "+pojoName+"ServiceObject.get"+upperPojoName+"List(page,pre,key);\n" +
                "    }\n");

        str.append("    @GetMapping(\"get"+upperPojoName+"ById\")\n" +
                "    public String get"+upperPojoName+"ById(@RequestParam(\"id\") int id) throws JsonProcessingException {\n" +
                "        return "+pojoName+"ServiceObject.get"+upperPojoName+"ById(id);\n" +
                "    }\n");

        str.append("    @PostMapping(\"add"+upperPojoName+"\")\n" +
                "    public String add"+upperPojoName+"(");
        for(int i = 0 ; i < memLen ; i ++) {
            if(i % 2 != 0) {
                if(i != memLen - 1){
                    str.append("@RequestParam(\""+mem.get(i)+"\") "+mem.get(i - 1)).append(" ").append(mem.get(i)).append(",");
                }
                else{
                    str.append("@RequestParam(\""+mem.get(i)+"\") "+mem.get(i - 1)).append(" ").append(mem.get(i));
                }
            }
        }
        str.append(") throws JsonProcessingException {\n" +
                "        return "+pojoName+"ServiceObject.add"+upperPojoName+"(");
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
        str.append(");\n" +
                "    }\n");
        str.append("    @PutMapping(\"update"+upperPojoName+"\")\n" +
                "    public String update"+upperPojoName+"(");
        for(int i = 0 ; i < memLen ; i ++) {
            if(i % 2 != 0) {
                if(i != memLen - 1){
                    str.append("@RequestParam(\""+mem.get(i)+"\") "+mem.get(i - 1)).append(" ").append(mem.get(i)).append(",");
                }
                else{
                    str.append("@RequestParam(\""+mem.get(i)+"\") "+mem.get(i - 1)).append(" ").append(mem.get(i));
                }
            }
        }
        str.append(") throws JsonProcessingException {\n" +
                "        return "+pojoName+"ServiceObject.update"+upperPojoName+"ById(");
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
        str.append(");\n" +
                "    }\n");

        str.append("    @DeleteMapping(\"delete"+upperPojoName+"ById\")\n" +
                "    public String delete"+upperPojoName+"ById(@RequestParam(\"id\") int id) throws JsonProcessingException {\n" +
                "        return "+pojoName+"ServiceObject.delete"+upperPojoName+"ById(id);\n" +
                "    }\n");
        str.append("}");

        return str.toString();
    }
}
