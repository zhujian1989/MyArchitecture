package jzhu.com.libdata.network;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;

public class InterceptorUtil {
    public static String TAG = "InterceptorUtil";

    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        HttpLoggingInterceptor
                httpLoggingInterceptor =
                new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    public static Interceptor HeaderInterceptor(){

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                                       .newBuilder()
                                       .addHeader("Content-Type",
                                                  "application/json")
                                       .addHeader("charset", "UTF-8")
                                       .build();
                return chain.proceed(request);
            }
        };

        return interceptor;

    }


}
