package dongting.bwei.com.ecommerce.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dongting.bwei.com.ecommerce.R;


public abstract class BaseMvpFragment<V,T extends BasePresenter<V>> extends Fragment {


    public T presenter ;

    public abstract T initPresenter();

    public BaseMvpFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_base_mvp, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = initPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.attach((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
