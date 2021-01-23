package com.company;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.LinkedList;

public class Main {

    public static String send(String url, JSONObject jsonObject) throws ParseException, IOException {
        String body = "";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        StringEntity s = new StringEntity(jsonObject.toString(), "utf-8");
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                "application/json"));
        httpPost.setEntity(s);

        httpPost.setHeader("X-Judge-Server-Token", "e99f7ff1ebb40a91320378dcc36e550a66f22ed468fe7fcacc0604938460488f");
        httpPost.setHeader("Content-Type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            body = EntityUtils.toString(entity, "UTF-8");
            System.out.println(body);
        }
        EntityUtils.consume(entity);
        response.close();
        return body;
    }


    public static void main(String[] args) throws IOException {
        JSONObject jo = new JSONObject();
        jo.put("src","#include \"stdio.h\"\n" +
                "int main()\n" +
                "{\n" +
                "    int a , b;\n" +
                "    scanf(\"%d %d\",&a,&b);\n" +
                "    printf(\"%d\",a+b);\n" +
                "    return 0;\n" +
                "}\n");
        jo.put("max_cpu_time",3000);
        jo.put("max_memory",134217728);

        JSONObject compile = new JSONObject();
        compile.put("src_name", "main.c");
        compile.put("exe_name", "main");
        compile.put("max_cpu_time",3000);
        compile.put("max_real_time", 5000);
        compile.put("max_memory",134217728);
        compile.put("compile_command", "/usr/bin/gcc -DONLINE_JUDGE -O2 -w -fmax-errors=3 -std=c99 {src_path} -lm -o {exe_path}");

        JSONObject run = new JSONObject();
        run.put("command", "{exe_path}");
        run.put("seccomp_rule", "c_cpp");
        LinkedList<String> l = new LinkedList<>();
        l.add("LANG=en_US.UTF-8");
        l.add("LANGUAGE=en_US:en");
        l.add("LC_ALL=en_US.UTF-8");
        run.put("env",l);

        JSONObject language_config = new JSONObject();
        language_config.put("compile",compile);
        language_config.put("run",run);

        jo.put("language_config",language_config);
        jo.put("test_case_id","normal");
        send("http://localhost:8081/judge",jo);
    }
}