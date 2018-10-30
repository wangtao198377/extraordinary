package com.xitao.http;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class HttpConnectUtils {

    public static CookieStore createCookie(String cookieStr) {
        if (StringUtils.isEmpty(cookieStr)) return null;

        String[] cookieArr = cookieStr.split("; ");
        BasicCookieStore Store = new BasicCookieStore();
        if (ArrayUtils.isEmpty(cookieArr)) return null;
        Map<String, String> cookieMap = new HashMap<>();
        for (String item : cookieArr) {
            if (item.indexOf("=") != -1) {
                String[] itemArr = item.split("=");
                cookieMap.put(itemArr[0], itemArr[1]);
            }
        }
        Set<Map.Entry<String, String>> entries = cookieMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            Store.addCookie(new BasicClientCookie(entry.getKey(), entry.getValue()) {
            });
        }
        return Store;
    }

    public static void main(String[] args) throws Exception{
       // login();
        getRequest(null);
    }
    public static void getRequest(String[] args) throws Exception {


        String cookieStr = "ipLoc-djd=1-72-2799-0; ipLocation=%u5317%u4EAC; pinId=ntJ_J0scEus; pin=taowcdl; unick=taowcdl; _tp=P1enQS73%2Fa6PfD7S%2Fhidmg%3D%3D; _pst=taowcdl; user-key=c12a177d-bd90-4bc6-a3c6-f4a3f5cd0cd5; shshshfpb=165cebb4b45944f91b83892dbd0d8860347bc74540f466de35b3392d4f; shshshfpa=05e1acaf-bff1-c381-b9eb-05467566caf1-1533023139; __jdu=15319056953001606606091; __jdv=50436146|direct|-|none|-|1534208824356; PCSYCityID=1; TrackID=16SZOo1x9iu_2YpGdP1_oNCsXWMpgnkrw4VUl58wwcpvRMTMMhYxve9EbSmiUKLRZW7_TMCqKbbhhpVg6V6NIvyY_qorJPsFTTFUFo_ilW8COk9fL4dPsK0os6_K8M4l_; areaId=1; shshshfp=88d44753edd89901e612ab70e5c5bc67; cn=4; __jda=137720036.15319056953001606606091.1531905695.1534745353.1534750007.94; __jdc=137720036; __jdb=137720036.4.15319056953001606606091|94.1534750007; 3AB9D23F7A4B3C9B=YEERFCSYF6C376Y2OY3YFZQWZX2HZM445WDZHVJLX6Z26LBQWJOVPG2UVTX3NBM7MAO5URCJDIRYGR3GB2NO6D5LWA; erp1.jd.com=A24BA6A4D25A66A25FD7692E8E9BE68938F928E25F68D470DE575391A6356EA8D20687F095091EF90758CBFF3A7A41FE785726F05003DEA9BF3156980E3A49BAF1E8C9A128953A9928DCB8AB6B3DC782; sso.jd.com=d50aea0a6ffa42639f8b1564732eb357";

        String url = "http://ump.jd.com/performanceDetail/initPage.action?datas.xData=1534750740000&datas.yData=23&datas.tpName=tp99&datas.accessKey=orderbank.web.orderbank.serviceorder.createOrderDirectly&datas.type=tp&datas.dType=oneMinute&datas.appId=3889";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        CookieStore store = createCookie(cookieStr);
        System.out.println("cookie::"+store.toString());

        HttpClientContext localContext = HttpClientContext.create();
        // Bind custom cookie store to the local context
        localContext.setCookieStore(store);

        CloseableHttpResponse response1 = httpclient.execute(httpGet,localContext);
        // The underlying HTTP connection is still held by the response object
        // to allow the response content to be streamed directly from the network socket.
        // In order to ensure correct deallocation of system resources
        // the user MUST call CloseableHttpResponse#close() from a finally clause.
        // Please note that if response content is not fully consumed the underlying
        // connection cannot be safely re-used and will be shut down and discarded
        // by the connection manager.
        try {
            System.out.println(response1.getStatusLine());

            HttpEntity entity1 = response1.getEntity();
//            // do something useful with the response body
//            // and ensure it is fully consumed
//            EntityUtils.consume(entity1);

            InputStream inputStream = entity1.getContent();

            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            System.out.println(sb.toString());

        } finally {
            response1.close();
        }
    }

    public static String login() throws Exception {

        HttpPost httpPost = new HttpPost("https://ssa.jd.com/sso/login?ReturnUrl=http%3A%2F%2Ferp.jd.com%2F");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username", "wangtao68"));
        nvps.add(new BasicNameValuePair("password", "1q2w3e4r%T6y"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response2 = httpclient.execute(httpPost);

        try {
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            InputStream inputStream = entity2.getContent();

            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            System.out.println(sb.toString());
        } finally {
            response2.close();
        }
        return null;

    }
}
