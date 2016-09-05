package phoneisure.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import phoneisure.core.common.CharsetConstant;
import phoneisure.core.exception.ApiRemoteCallFailedException;

import java.io.*;
import java.net.*;
import java.util.Date;

/**
 * get data from api
 * Created by pengyi on 2015/7/27.
 */
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String urlConnection(String url, String pa, String method) {

        String response = null;

        try {
            logger.info(url, "http发送请求-----" + pa + "-----" + new Date());
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod(method);


            // Send data
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), CharsetConstant.UTF8_STRING));
            // pa为请求的参数
            pw.print(pa);
            pw.flush();
            pw.close();

            // Get the response!
            int httpResponseCode = conn.getResponseCode();
            if (httpResponseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("HTTP response code: " + httpResponseCode +
                        "\nurl:" + url);
            }

            InputStream inputStream = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, CharsetConstant.UTF8_STRING));
            StringBuilder builder = new StringBuilder();
            String readLine;
            while (null != (readLine = br.readLine())) {
                builder.append(readLine);
            }
            inputStream.close();
            response = builder.toString();
            logger.info(url, "http返回-----" + response + "-----" + new Date());

            conn.disconnect();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    /**
     * 微信统一下单
     *
     * @param url
     * @param param
     * @return
     */
    public static String wechatUnified(String url, String param) {
        String result = null;
        try {
            URL orderUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) orderUrl.openConnection();
            conn.setConnectTimeout(30000); // 设置连接主机超时（单位：毫秒)
            conn.setReadTimeout(30000); // 设置从主机读取数据超时（单位：毫秒)
            conn.setDoOutput(true); // post请求参数要放在http正文内，顾设置成true，默认是false
            conn.setDoInput(true); // 设置是否从httpUrlConnection读入，默认情况下是true
            conn.setUseCaches(false); // Post 请求不能使用缓存
            // 设定传送的内容类型是可序列化的java对象(如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");// 设定请求的方法为"POST"，默认是GET
            conn.setRequestProperty("Content-Length", param.length() + "");
            String encode = "utf-8";
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), encode);
            out.write(param.toString());
            out.flush();
            out.close();
            result = getOut(conn);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getOut(HttpURLConnection conn) throws IOException {
        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            return null;
        }
        // 获取响应内容体
        BufferedReader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String line = "";
        StringBuffer strBuf = new StringBuffer();
        while ((line = in.readLine()) != null) {
            strBuf.append(line).append("\n");
        }
        in.close();
        return strBuf.toString().trim();
    }

    /**
     * @param url 是加上？参数后的url
     * @return
     */
    public static String SendGET(String url) {
        String result = "";//访问返回结果
        BufferedReader read = null;//读取访问结果

        try {
            //创建url
            URL realurl = new URL(url);
            //打开连接
            URLConnection connection = realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立连接
            connection.connect();
            // 获取所有响应头字段
//             Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段，获取到cookies等
//             for (String key : map.keySet()) {
//                 System.out.println(key + "--->" + map.get(key));
//             }
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;//循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
