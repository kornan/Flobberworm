package com.flobberworm.framework.views.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;


/**
 * BannerAdapter
 * Created by KORNAN on 2016/9/20.
 */
public class BannerAdapter extends PagerAdapter {

    private Context context;
    private List<String> bannerList;
    private BannerView.OnBannerListener onBannerListener;

    public BannerAdapter(Context context) {
        this.context = context;
        bannerList = new ArrayList<>();
    }

    public BannerView.OnBannerListener getOnBannerListener() {
        return onBannerListener;
    }

    public void setOnBannerListener(BannerView.OnBannerListener onBannerListener) {
        this.onBannerListener = onBannerListener;
    }

    public BannerAdapter(Context context, List<View> viewList) {
        this.context = context;
//        this.viewList = viewList;
    }

    public List<String> getBannerList() {
        return bannerList;
    }

    @Override
    public int getCount() {
//        return (viewList == null) ? 0 : viewList.size();
        if (bannerList != null && bannerList.size() == 1) {
            return bannerList.size();
        } else if (bannerList == null || bannerList.size() == 0) {
            return 0;
        } else
            return Integer.MAX_VALUE;
    }


    private ImageView getImageView(int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setBackgroundColor(context.getResources().getColor(R.color.common_gray));
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Glide.with(context)
                .load(bannerList.get(position))
//                .placeholder(R.drawable.default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        return imageView;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        bannerList.get(position % bannerList.size());
        ImageView imageView = getImageView(position % bannerList.size());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBannerListener != null) {
                    onBannerListener.onBannerItemClick(v, position % bannerList.size());
                }
            }
        });
        container.addView(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    public void destroyItem(ViewGroup view, int i, Object object) {
        view.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        int index = bannerList.indexOf(object);
        if (index == -1)
            return POSITION_NONE;
        else
            return index;
    }
}
