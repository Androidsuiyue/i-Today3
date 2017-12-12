package qzhenghao.cn.i_today;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import qzhenghao.cn.i_today.beans.MainDataItemBean;
import qzhenghao.cn.i_today.utils.DevicePreferences;
import qzhenghao.cn.i_today.utils.StringUtil;

import static org.junit.Assert.*;
import static qzhenghao.cn.i_today.network.NetUtil.GZIP;
import static qzhenghao.cn.i_today.network.NetUtil.HEADER_KEY_ACCEPT_ENCODING;
import static qzhenghao.cn.i_today.network.NetUtil.HEADER_KEY_CONTENT_ENCODING;
import static qzhenghao.cn.i_today.network.NetUtil.USER_AGENT;
import static qzhenghao.cn.i_today.network.NetUtil.decompress;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test_1() throws Exception{
        String url ="http://api.cntv.cn/epg/epginfo?c=cctv1,cctv2,cctv3,cctv4,cctveurope,cctvamerica,cctv5,cctv6,cctv7&serviceId=channel&d=20171208&t=jsonp&cb=setItem1";

        String  s =httpGetUtil(url);




        if (!StringUtil.isNullOrEmpty(s)) {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject != null) {
                String setItem1 = jsonObject.optString("setItem1");//判断请求状态
                if (setItem1.equals("setItem1")) {
                    JSONObject data = jsonObject.optJSONObject("data");
                    if (data != null) {
                        JSONArray dataArray = data.optJSONArray("data");
                        int dataLength = dataArray.length();
                        for (int i = 0; i < dataLength; i++) {
                            MainDataItemBean mainItemBean = new MainDataItemBean(dataArray.optJSONObject(i));
                            mainItemBeanList.add(mainItemBean);
                        }
                        mainAdapter.notifyDataSetChanged();
                        return;
                    }
                }
            }
        }





    }

    public  String spil(String j){
        int i = j.indexOf("(");
        int i1 = j.indexOf(")");
        String substring = j.substring(i+1,i1);
return substring

    }
    private static String decompress(InputStream is) {
        StringBuffer sb = new StringBuffer();

        try {
            GZIPInputStream gis = new GZIPInputStream(is);

            InputStreamReader isr = new InputStreamReader(gis, "UTF-8");
            java.io.BufferedReader br = new java.io.BufferedReader(isr);
            sb = new StringBuffer();
            String tempbf;
            while ((tempbf = br.readLine()) != null) {
                sb.append(tempbf);
                sb.append("\n");
            }
            isr.close();
            gis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    public  String httpGetUtil( String url) {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).build();
        String result = null;

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
//                .url(url.replace("+", "%2B"))//FIXME: hmac issue
                .get()
                .header(USER_AGENT, "")
//                .header("Host","m2.qiushibaike.com")
                .header(HEADER_KEY_ACCEPT_ENCODING, GZIP);

//        Account account = new AccountManager(context).getAccount();
//
//        if (account.isLogined()) {
//            setTokenHeader(requestBuilder, account.getToken(), url, new DevicePreferences(context).getTokenId());
//        } else {
//        setTokenHeader(requestBuilder, null, url, new DevicePreferences(context).getUADeviceID());
//        }

        Request request = requestBuilder.build();
        Response response;

        try {
            response = mOkHttpClient.newCall(request).execute();
            Log.i("qzh", "The request httpGetUtil: " + url + " " + response.code());
            if (response.isSuccessful()) {
                if ("gzip".equals(response.header(HEADER_KEY_CONTENT_ENCODING))) {
                    result = decompress(response.body().byteStream());
                } else {
                    result = response.body().string();
                }
            } else {
                result = null;
            }
        } catch (Exception e) {

        }
        return result;
    }



}