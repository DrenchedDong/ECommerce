package dongting.bwei.com.ecommerce.presenter;

import dongting.bwei.com.ecommerce.base.BasePresenter;
import dongting.bwei.com.ecommerce.model.SplashModelImpl;
import dongting.bwei.com.ecommerce.view.SplashView;

/**
 * 作者:${董婷}
 * 日期:2017/6/19
 * 描述:
 */
public class SplashPresenter extends BasePresenter<SplashView> {

    SplashModelImpl splashModel;

        public SplashPresenter(){
            splashModel =new SplashModelImpl();

        }

    public void getData(){
        splashModel.getData();
    };
}
