package vueHelper;

import java.io.File;

public class Main {
    public static String member = "id Id;name 姓名;age 年龄;";
    public static void main(String[] args) {
        File dir = new File("workspace2");
        if(!dir.exists())
            dir.mkdir();

//        System.out.println(form());
//        System.out.println(formRules());
//        System.out.println(sendData());
//        System.out.println(getData());
//        System.out.println(tableCol());
//        System.out.println(formItem());
    }

    public static String formItem()
    {
        StringBuilder str = new StringBuilder("");
        String mem[] = member.split(";");
        int memLen = mem.length;
        for(int i = 0 ; i < memLen ; i ++)
        {
            String arr[] = mem[i].split(" ");
            str.append("<el-form-item label=\""+arr[1]+"\" prop=\""+arr[0]+"\">\n" +
                    "   <el-input v-model=\"addForm."+arr[0]+"\"></el-input>\n" +
                    "</el-form-item>\n");
        }
        return str.toString();
    }

    public static String tableCol()
    {
        StringBuilder str = new StringBuilder("");
        String mem[] = member.split(";");
        int memLen = mem.length;
        for(int i = 0 ; i < memLen ; i ++)
        {
            String arr[] = mem[i].split(" ");
            str.append("<el-table-column label=\""+arr[1]+"\" prop=\""+arr[0]+"\" min-width=\"5%\"></el-table-column>\n");
        }
        return str.toString();
    }

    public static String form()
    {
        StringBuilder str = new StringBuilder("");
        String mem[] = member.split(";");
        int memLen = mem.length;
        for(int i = 0 ; i < memLen ; i ++)
        {
            String arr[] = mem[i].split(" ");
            str.append(""+arr[0]+":'',\n");
        }
        return str.toString();
    }

    public static String formRules()
    {
        StringBuilder str = new StringBuilder("");
        String mem[] = member.split(";");
        int memLen = mem.length;
        for(int i = 0 ; i < memLen ; i ++)
        {
            String arr[] = mem[i].split(" ");
            str.append(arr[1]+": [\n");
            str.append("    { required: true, message: '请输入"+arr[1]+"', trigger: 'blur' }\n");
            str.append("],\n");
        }
        return str.toString();
    }
    public static String sendData()
    {
        StringBuilder str = new StringBuilder("");
        String mem[] = member.split(";");
        int memLen = mem.length;
        for(int i = 0 ; i < memLen ; i ++)
        {
            String arr[] = mem[i].split(" ");
            str.append(""+arr[0]+":this.addForm."+arr[0]+",\n");
        }
        str.append("token:window.localStorage.getItem(\"token\"),\n");
        return str.toString();
    }
    public static String getData()
    {
        StringBuilder str = new StringBuilder("");
        String mem[] = member.split(";");
        int memLen = mem.length;
        for(int i = 0 ; i < memLen ; i ++)
        {
            String arr[] = mem[i].split(" ");
            str.append("this.addForm."+arr[0]+" = "+arr[0]+",\n");
        }
        return str.toString();
    }

}
