package dongting.bwei.com.ecommerce.model;

import dongting.bwei.com.ecommerce.network.BaseObserver;
import dongting.bwei.com.ecommerce.network.RetrofitFactory;

/**
 * 作者:${董婷}
 * 日期:2017/6/19
 * 描述:
 */

public class SplashModelImpl {

    public void getData() {

        RetrofitFactory.get("http://qhb.2dyt.com/Bwei/login?username=11111111111&password=1&postkey=1503d")
                .subscribe(new BaseObserver() {
            @Override
            public void onSuccess(String result) {
                System.out.println("result = " + result);
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
