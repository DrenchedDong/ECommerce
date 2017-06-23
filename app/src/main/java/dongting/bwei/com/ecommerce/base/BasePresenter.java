package dongting.bwei.com.ecommerce.base;

/**
 * 作者:${董婷}
 * 日期:2017/6/19
 * 描述:presenter中持有view，通过view的接口跟activity进行交互
 */

public class BasePresenter<T> {

    public  T view;

    //view是在activity中创建，持有this
    public  void attach(T view){
        this.view=view;
    }

    //将当前view置为空，防止内存泄漏
    public  void detach(){
        this.view=null;
    }
}
