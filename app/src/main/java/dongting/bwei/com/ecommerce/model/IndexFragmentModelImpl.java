package dongting.bwei.com.ecommerce.model;

import com.google.gson.Gson;

import dongting.bwei.com.ecommerce.bean.IndexBean;
import dongting.bwei.com.ecommerce.network.BaseObserver;
import dongting.bwei.com.ecommerce.network.RetrofitFactory;
import dongting.bwei.com.ecommerce.view.IndexFragmentView;

/**
 * 作者:${董婷}
 * 日期:2017/6/20
 * 描述:
 */

public class IndexFragmentModelImpl implements IndexFragmentView{

     IndexFragmentListener listener ;

     public IndexFragmentModelImpl(IndexFragmentListener listener){
        this.listener=listener;
    }

//上新   "http://lib.suning.com/app/redbaby/80000_8.2.0-155.json",
    //车床 "http://lib.suning.com/app/redbaby/babycarriage-35.json"
    private String [] url = {
                    "http://lib.suning.com/app/redbaby/babydiapers-64.json",
                    "http://lib.suning.com/app/redbaby/babymilk-41.json",
                    "http://lib.suning.com/app/redbaby/BabyBottles-56.json",
                    "http://lib.suning.com/app/redbaby/babytoys-50.json",
            "http://lib.suning.com/app/redbaby/babyclothes-49.json",
            "http://lib.suning.com/app/redbaby/babybooks-39.json"} ;

    public void getData(int position) {
        RetrofitFactory.get(url[position])
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        IndexBean indexBean =  gson.fromJson(result, IndexBean.class);
                        //System.out.println("model数据返回成功：indexBean = " + indexBean.toString());
                        listener.onSuccess(indexBean);
                    }

                    @Override
                    public void onFailed() {
                        listener.onFail();
                        //System.out.println("model数据返回失败" );
                    }
                });
    }

    public interface  IndexFragmentListener{
        void onSuccess(IndexBean indexBean);
        void onFail();
    }
}
