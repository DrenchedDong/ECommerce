package dongting.bwei.com.ecommerce.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import dongting.bwei.com.ecommerce.R;

/**
 * 作者:${董婷}
 * 日期:2017/6/19
 * 描述:
 */

public abstract class BaseMvpActivity<V,T extends BasePresenter<V>> extends FragmentActivity {

    public T presenter ;

    public abstract T initPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_mvp);

        presenter = initPresenter();

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
