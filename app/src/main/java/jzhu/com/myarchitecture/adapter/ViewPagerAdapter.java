package jzhu.com.myarchitecture.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import jzhu.com.myarchitecture.R;

public class ViewPagerAdapter extends PagerAdapter {


    private String[] list;

    private LayoutInflater inflater;

    public ViewPagerAdapter(String[] list, Context context){
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = inflater.inflate(R.layout.test_item,null);

        TextView textView = view.findViewById(R.id.num_text);
        textView.setText(String.valueOf(position));
        container.addView(view);
        return view;
    }



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
