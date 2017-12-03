package qzhenghao.cn.i_today.config.instrumentation;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import qzhenghao.cn.i_today.config.FrescoConfig;

/**
 * Author： fanyafeng
 * Data： 16/10/26 19:16
 * Email: fanyafeng@live.cn
 */
public class AppConfig extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext(), FrescoConfig.getsImagePipelineConfig(this));
//        LitePal.initialize(getApplicationContext());
//        initDisplayOpinion();
    }

//    private void initDisplayOpinion() {
//        DisplayMetrics dm = getResources().getDisplayMetrics();
//        DisplayUtil.density = dm.density;
//        DisplayUtil.densityDPI = dm.densityDpi;
//        DisplayUtil.screenWidthPx = dm.widthPixels;
//        DisplayUtil.screenhightPx = dm.heightPixels;
//        DisplayUtil.screenWidthDip = DisplayUtil.px2dip(getApplicationContext(), dm.widthPixels);
//        DisplayUtil.screenHightDip = DisplayUtil.px2dip(getApplicationContext(), dm.heightPixels);
//    }
}
