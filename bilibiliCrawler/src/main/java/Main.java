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
    public static void main(String[] args) throws IOException {
        String str = "";
        String url = "https://api.bilibili.com/pgc/season/index/result?season_version=-1&area=-1&is_finish=-1&copyright=-1&season_status=-1&season_month=-1&year=-1&style_id=-1&order=3&st=1&sort=0&page=1&season_type=1&pagesize=3222&type=1";
        String re = getInfo(url);
        JSONObject reJo = JSONObject.parseObject(re);
        if(reJo.getString("code").equals("0"))
        {
            JSONArray list = reJo.getJSONObject("data").getJSONArray("list");
            for(int  i = 0;i < list.size(); i ++) {
                JSONObject data = list.getJSONObject(i);
                str += (i+1);
                str += " ";
                str += data.getString("title");
                str += " ";
                str += data.getString("order");
                str += "\n";
                str += data.getString("link");
                str += "\n";
                str += "--------------------------------------------";
                str += "\n";
            }
        }
        System.out.println(str);
    }

    public static String getInfo(String url) throws IOException {
        String body = "";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpPost = new HttpGet(url);

        httpPost.setHeader("authority", "api.bilibili.com");
        httpPost.setHeader("method", "GET");
        httpPost.setHeader("path", "/pgc/season/index/result?season_version=-1&area=-1&is_finish=-1&copyright=-1&season_status=-1&season_month=-1&year=-1&style_id=-1&order=3&st=1&sort=0&page=1&season_type=1&pagesize=20&type=1");
        httpPost.setHeader("scheme", "https");
        httpPost.setHeader("accept", "*/*");
        httpPost.setHeader("accept-encoding", "gzip, deflate, br");
        httpPost.setHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
        httpPost.setHeader("cookie", "_uuid=F4B45698-3D56-FB85-2481-B885888D853047527infoc; buvid3=CD98B41C-CAF5-45B6-99CA-EC93B49AAC1418563infoc; bfe_id=7b6a677b97d4786cd8b2f807c787d88b; PVID=1");
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
}
