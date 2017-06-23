package dongting.bwei.com.ecommerce.network;

import java.util.concurrent.TimeUnit;

import dongting.bwei.com.ecommerce.network.cookie.CookiesManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者:${董婷}
 * 日期:2017/6/20
 * 描述:
 */

public class RetrofitFactory {

    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cookieJar(new CookiesManager())
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(20,TimeUnit.SECONDS)
            //.addInterceptor(new LoggingInterceptor())
            .build();

    public  static ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://qbh.2dyt.com")
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            //把 以前的 call 转化成 Observable
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiService.class);

    public static  Observable<String>  get(String url){

         return apiService.get(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
