import com.alibaba.fastjson.JSONArray;
import org.apache.http.client.methods.HttpGet;

import java.io.*;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Main {

    public static void main(String[] args) throws IOException {

        //存储最终结果的字符串
        String str = "";

        //url。即postman最上面的url。
        //这里url中携带了两个对我们有用的参数，page和pagesize。
        //该案例中page=1，pagesize=20，意思是请求排行榜第一页的数据，每页20个
        String url = "https://api.bilibili.com/pgc/season/index/result?season_version=-1&area=-1&is_finish=-1&copyright=-1&season_status=-1&season_month=-1&year=-1&style_id=-1&order=3&st=1&sort=0&page=1&season_type=1&pagesize=20&type=1";

        //使用刚才的函数获取服务返回的数据
        String re = getInfo(url);

        //将返回的数据格式化成json对象
        JSONObject reJo = JSONObject.parseObject(re);

        //以下是对返回的数据进行处理，得到我们需要的格式。下一章会详解json和解析处理的方法
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
        //输出处理好的数据
        System.out.println(str);
    }

    //该函数的功能是模拟请求，发送给服务器，并返回服务器的响应内容
    //返回值类型为String(字符串),传入一个String型参数url
    public static String getInfo(String url) throws IOException {

        //要返回的内容
        String body = ""; 

        //创建一个GET请求
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet(url);

        //给请求添加请求头（即postman 'Headers' 选项卡下的字段），字段名为'cookie'
        getRequest.setHeader("cookie", "_uuid=F4B45698-3D56-FB85-2481-B885888D853047527infoc; buvid3=CD98B41C-CAF5-45B6-99CA-EC93B49AAC1418563infoc; bfe_id=7b6a677b97d4786cd8b2f807c787d88b; PVID=1");

        //发送请求
        CloseableHttpResponse response = client.execute(getRequest);

        //接收服务器返回的内容
        HttpEntity entity = response.getEntity();

        //若返回的内容不为空，以utf-8字符集格式化内容，存在变量'body'中
        if (entity != null) {
            body = EntityUtils.toString(entity, "UTF-8");
        }

        //以下两行是销毁创建的资源，释放内存
        EntityUtils.consume(entity);
        response.close();

        //返回服务器返回的内容
        return body;
    }
}
