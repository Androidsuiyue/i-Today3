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


}
