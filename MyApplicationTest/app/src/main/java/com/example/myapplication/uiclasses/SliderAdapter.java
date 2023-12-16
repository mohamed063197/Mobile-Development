package com.example.myapplication.uiclasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);

    }

    int images[] = {
            R.drawable.img_scan,
            R.drawable.img_getinformed,
            R.drawable.img_toremember
    };

    int descs[] = {
            R.string.mainScanDesc,
            R.string.mainInformedDesc,
            R.string.mainRememberDesc
    };

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == (ConstraintLayout) object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        //layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout,container,false);

        //Hooks
        ImageView img = view.findViewById(R.id.sliderImage);
        TextView desc = view.findViewById(R.id.sliderDesc);

        img.setImageResource(images[position]);
        desc.setText(descs[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((ConstraintLayout)object);
    }

}
