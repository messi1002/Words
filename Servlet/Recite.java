package Servlet;

import net.sf.json.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: wjy
 * @Date: 2018/9/27 01:31
 * @Description:
 */

public class Recite extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String user = (String) request.getSession().getAttribute("user");
        String[] s= new String[3];
        s[0] = null;
        s[1] = request.getParameter("word1");
        s[2] = request.getParameter("word2");
        System.out.println();
        System.out.println(user);
        String[] s1= new String[0];
        try {
            s1 = begin(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conMysql(s,s1,user);
        request.setAttribute("mean1",s1[1]);
        request.setAttribute("mean2",s1[2]);
        System.out.println(s1[1]);
        System.out.println(s1[2]);
        request.getRequestDispatcher("../recite.jsp").forward(request,response);
    }

    private void conMysql(String[] s,String[] s1,String user) {
        LocalDate date = LocalDate.now();
        String sql = "INSERT INTO words.allWords Values (null,?,?,?,?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" +
                    "words?useSSL=true", "root", "993344");
            PreparedStatement pre = con.prepareStatement(sql);
            for (int i = 0; i < s.length; i++) {
                if (s[i] != null & s[i] != "") {
                    pre.setString(3, String.valueOf(date));
                    pre.setString(4, user);
                    pre.setString(1, s[i]);
                    pre.setString(2, s1[i]);
                    pre.executeUpdate();
                }
            }
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public String[] begin(String[] s) throws Exception {
        String[] s2 = new String[3];
        for (int i = 1; i < s.length; i++)
        {
            if (s[i] != null & s[i] != "")
            {
                String appKey = "608bf4011b8c9552";
                String salt = String.valueOf(System.currentTimeMillis());
                String from = "zh-CHS";
                String to = "EN";
                String query = s[i];
                String sign = md5(appKey + query + salt + "HOTrykK9PWyZiHJpKwvnqqiPFGbEpJPQ");
                Map params = new HashMap();
                params.put("q", query);
                params.put("from", from);
                params.put("to", to);
                params.put("sign", sign);
                params.put("salt", salt);
                params.put("appKey", appKey);
                String r = requestForHttp("http://openapi.youdao.com/api", params);
                JSONObject json1 = new JSONObject(r);
                String jiexi = json1.getString("web");
                System.out.println(r);
                System.out.println(jiexi);
                s2[i] = jiexi.substring(11,40);
            }
        }
        return s2;
    }

    public String requestForHttp(String url, Map requestParams) throws Exception{
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /**HttpPost*/
        HttpPost httpPost = new HttpPost(url);
        System.out.println(new JSONObject(requestParams).toString());
        List params = new ArrayList();
        Iterator it = requestParams.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry en = (Map.Entry) it.next();
            String key = (String) en.getKey();
            String value = (String) en.getValue();
            if (value != null) {
                params.add(new BasicNameValuePair(key, value));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
        /**HttpResponse*/
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try{
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, "utf-8");
            EntityUtils.consume(httpEntity);
        }finally{
            try{
                if(httpResponse!=null){
                    httpResponse.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public String md5(String string) {
        if(string == null){
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try{
            byte[] btInput = string.getBytes("utf-8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }catch(NoSuchAlgorithmException | UnsupportedEncodingException e){
            return null;
        }
    }

    public  String getUrlWithQueryString(String url, Map params) {
        if (params == null) {
            return url;
        }
        StringBuilder builder = new StringBuilder(url);
        if (url.contains("?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }

        int i = 0;
        for (Object key : params.keySet()) {
            String value = (String) params.get(key);
            if (value == null) { // 过滤空的key
                continue;
            }
            if (i != 0) {
                builder.append('&');
            }
            builder.append(key);
            builder.append('=');
            builder.append(encode(value));
            i++;
        }
        return builder.toString();
    }

    public String encode(String input) {
        if (input == null) {
            return "";
        }
        try {
            return URLEncoder.encode(input, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return input;
    }
}
