package qzhenghao.cn.i_today.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import qzhenghao.cn.i_today.beans.MainDataItemBean;


/**
 * Created by 89399 on 2017/10/10.
 *
 */
public class JsonUtils {

    /**
     * 对返回的json进行处理
     */
    public static ArrayList<MainDataItemBean> getItem(String s) throws JSONException {

        ArrayList<MainDataItemBean> mainDataItemBeans = new ArrayList<MainDataItemBean>();
        if (s != null && !"".equals(s)) {

            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject != null) {
                String message = jsonObject.optString("message");//判断请求状态
                if (message.equals("success")) {
                    JSONObject data = jsonObject.optJSONObject("data");
                    if (data != null) {
                        JSONArray dataArray = data.optJSONArray("data");
                        int dataLength = dataArray.length();
                        for (int i = 0; i < dataLength; i++) {
                            MainDataItemBean mainItemBean = new MainDataItemBean(dataArray.optJSONObject(i));
                            mainDataItemBeans.add(mainItemBean);
                        }
                    }
                }
            }
        }
        return mainDataItemBeans;


    }

    /**
     * unicode解码（unicode编码转中文）
     * @param theString
     * @return
     */
    public static String uToh(String theString){

        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);

                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    /**
     * 进行json切割
     * @param j
     * @return
     */
    public static String spil(String j){
        int i = j.indexOf("(");
        int i1 = j.lastIndexOf(")");

        String substring = j.substring(i+1,i1);

        return substring;

    }


}
