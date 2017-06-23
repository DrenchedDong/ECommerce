package dongting.bwei.com.ecommerce.network;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 作者:${董婷}
 * 日期:2017/6/20
 * 描述:
 */

public  abstract class BaseObserver implements Observer<String> {
    /**
     * 非抽象类实现接口，需要实现所有方法，抽象类则不用
     * @param d
     */
    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onNext(@NonNull String s) {
            onSuccess(s);
    }

    @Override
    public void onError(@NonNull Throwable e) {
            onFailed();
    }

    @Override
    public void onComplete() {

    }

    public  abstract void onSuccess(String result);
    public abstract  void onFailed();
}
