package jzhu.com.libdata.network;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class InterceptorUtil {

    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        HttpLoggingInterceptor
                httpLoggingInterceptor =
                new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    public static Interceptor HeaderInterceptor(){

        Interceptor interceptor = chain -> {
            Request request = chain.request()
                                   .newBuilder()
                                   .addHeader("Accept","application/vnd.github.v3+json")
                                   .addHeader("Content-Type",
                                              "application/json")
                                   .addHeader("charset", "UTF-8")
                                   .build();
            return chain.proceed(request);
        };

        return interceptor;

    }


}
