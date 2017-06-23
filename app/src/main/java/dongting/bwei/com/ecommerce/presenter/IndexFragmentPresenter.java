package dongting.bwei.com.ecommerce.presenter;

import dongting.bwei.com.ecommerce.base.BasePresenter;
import dongting.bwei.com.ecommerce.bean.IndexBean;
import dongting.bwei.com.ecommerce.model.IndexFragmentModelImpl;
import dongting.bwei.com.ecommerce.view.IndexFragmentView;
import dongting.bwei.com.ecommerce.view.IndexView;

/**
 * 作者:${董婷}
 * 日期:2017/6/20
 * 描述:
 */

public class IndexFragmentPresenter extends BasePresenter<IndexFragmentView> implements IndexFragmentModelImpl.IndexFragmentListener{

    IndexView indexView;
    IndexFragmentModelImpl indexFragmentModel;

        public  IndexFragmentPresenter(IndexView indexView){
            this.indexView=indexView;

        this.indexFragmentModel=new IndexFragmentModelImpl(this);
        }

        public void getData(int position ){
            indexFragmentModel.getData(position);
        }

    @Override
    public void onSuccess(IndexBean indexBean) {
        indexView.onsuccess(indexBean);
    }

    @Override
    public void onFail() {
        indexView.onFail();
    }
}
