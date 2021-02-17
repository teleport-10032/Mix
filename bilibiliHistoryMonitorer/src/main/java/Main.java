
import com.alibaba.fastjson.JSONArray;
import org.apache.http.client.methods.HttpGet;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Main {

    private static Integer cacheTime = 1000;

    public static void main(String[] args){
        Random r = new Random();
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    cacheTime = (int) (r.nextInt(10000)+10000);
                    try {
                        getOnce();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(cacheTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static void writeToFile(String content)
    {
        String saveFile = "history.txt";
        File file = new File(saveFile);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            if (!file.exists()) {
                boolean hasFile = file.createNewFile();
                if(hasFile){
                    System.out.println("file not exists, create new file");
                }
                fos = new FileOutputStream(file);
            } else {
//                System.out.println("file exists,write");
                fos = new FileOutputStream(file, true);
            }

            osw = new OutputStreamWriter(fos, "utf-8");

            //先读取，重复的content则不追加
            FileInputStream inputStream = new FileInputStream(saveFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String tmp = "";
            List<String> list = new ArrayList<>();
            while((tmp = bufferedReader.readLine()) != null)
            {
                list.add(tmp);
            }
            //close
            inputStream.close();
            bufferedReader.close();

            //str == 文件内容 content == 将要追加的内容
            int len = list.size();
//            System.out.println(len);

            //是否需要追加记号
            int flag = 0;
            String compare = "";
            int cnt = 0;
            for(int i = 0; i < len ; i ++)
            {
                compare += list.get(i);
                compare += "\n";
                cnt ++;
                if(cnt == 4)
                {
                    cnt = 0;
//                    System.out.println("compare:"+compare);
//                    System.out.println("content:"+content);
                    if(content.equals(compare))
                    {
                        flag = 1;
                    }
                    compare = "";
                }
            }
            if(flag == 0)
            {
                osw.write(content);
                System.out.println("write");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void getOnce() throws IOException {
        String str = "";
        String re = getHistory();
        JSONObject reJo = JSONObject.parseObject(re);
        if(reJo.getString("code").equals("0"))
        {
            JSONArray list = reJo.getJSONObject("data").getJSONArray("list");
            for(int  i = 0;i < list.size(); i ++) {
                JSONObject data = list.getJSONObject(i);
                str += data.getString("title");
                str += "\n";
                String timeStamp  = data.getString("view_at");
                str += timeStamp2Date(timeStamp,"yyyy-MM-dd HH:mm:ss");
                str += "\n";
                str += data.getString("author_name");
                str += "  ";
                str += data.getJSONObject("history").getString("bvid");
                str += data.getString("progress");
                str += "\n";
                str += "--------------------------------------------";
                str += "\n";
                writeToFile(str);
                str = "";
            }
        }
    }

    public static String getHistory() throws IOException {
        String body = "";
        String url = "https://api.bilibili.com/x/web-interface/history/cursor?max=0&view_at=0&business=";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpPost = new HttpGet(url);

        httpPost.setHeader("authority", "api.bilibili.com");
        httpPost.setHeader("method", "GET");
        httpPost.setHeader("path", "/x/web-interface/history/cursor?max=0&view_at=0&business=");
        httpPost.setHeader("scheme", "https");
        httpPost.setHeader("accept", "*/*");
        httpPost.setHeader("accept-encoding", "gzip, deflate, br");
        httpPost.setHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
        httpPost.setHeader("cookie", "yourCookieHere");
        httpPost.setHeader("origin", "https://www.bilibili.com");
        httpPost.setHeader("referer", "https://www.bilibili.com/");
        httpPost.setHeader("sec-ch-ua", "\"Chromium\";v=\"88\", \"Google Chrome\";v=\"88\", \";Not A Brand\";v=\"99\"");
        httpPost.setHeader("sec-ch-ua-mobile", "?0");
        httpPost.setHeader("sec-fetch-dest", "empty");
        httpPost.setHeader("sec-fetch-mode", "cors");
        httpPost.setHeader("sec-fetch-site", "same-site");
        httpPost.setHeader("user-agent", "");
        httpPost.setHeader("", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36");

        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            body = EntityUtils.toString(entity, "UTF-8");
        }
        EntityUtils.consume(entity);
        response.close();
        return body;
    }

    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }

}
