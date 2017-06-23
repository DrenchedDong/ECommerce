package dongting.bwei.com.ecommerce;

import android.app.Application;
/**
 * 作者:${董婷}
 * 日期:2017/6/20
 * 描述:
 */
public class IApplication extends Application {


    public static IApplication application ;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

    }
}
