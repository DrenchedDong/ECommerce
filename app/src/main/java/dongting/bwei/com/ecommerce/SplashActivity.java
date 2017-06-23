package dongting.bwei.com.ecommerce;

import android.content.Intent;
import android.os.Bundle;

import dongting.bwei.com.ecommerce.activitys.TabActivity;
import dongting.bwei.com.ecommerce.base.BaseMvpActivity;
import dongting.bwei.com.ecommerce.presenter.SplashPresenter;
import dongting.bwei.com.ecommerce.view.SplashView;

public class SplashActivity extends BaseMvpActivity<SplashView,SplashPresenter> {

    @Override
    public SplashPresenter initPresenter() {
        return new SplashPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        /*Timer timer = new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, TabActivity.class));
                finish();
            }
        };
        timer.schedule(timerTask,2000);*/

        startActivity(new Intent(SplashActivity.this, TabActivity.class));
        finish();
    }

}
