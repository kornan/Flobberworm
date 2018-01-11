package com.flobberworm.framework.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.Gravity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.flobberworm.framework.BaseApplication;

import java.util.concurrent.ExecutionException;


/**
 * GlideHelper
 * Created by KORNAN on 2016/12/28.
 */

public class GlideHelper {

    public static void showImageViewTransform(ImageView imageView, String imageUrl){
        Glide.with(BaseApplication.getInstance())
                .load(imageUrl)
                .crossFade()
                .bitmapTransform(new BlurTransformation(BaseApplication.getInstance()))  // “23”：设置模糊度(在0.0到25.0之间)，默认”25";"4":图片缩放比例,默认“1”。
                .into(imageView);
    }

    public static void showImageView(ImageView imageView, String imageUrl) {
        Glide.with(BaseApplication.getInstance())
                .load(imageUrl)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void showItemImageView(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
//                    .placeholder(R.drawable.default_image_background)
                .fitCenter()
                .crossFade()
                .override(120, 180)//override(120, 180)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void setRoundImage(final Context context, final ImageView imageView, String uri) {
        Glide.with(context)
                .load(uri)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(getBitmapImageViewTarget(context, imageView));
    }

    public static void setRoundImage(Context context, ImageView imageView, Integer resourceId) {
        Glide.with(context)
                .load(resourceId)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(getBitmapImageViewTarget(context, imageView));


//        Glide.with(context)
//                .load(resourceId)
//                .transform(new GlideCircleTransform(context))
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageView);
    }

    public static Bitmap getBitmap(Context context, String uri) throws ExecutionException, InterruptedException {
        return Glide.with(context)
                .load(uri)
                .asBitmap()
                .into(60, 60)
                .get();
    }

    /**
     * 设置圆形图
     *
     * @param context
     * @param imageView
     * @return
     */
    private static BitmapImageViewTarget getBitmapImageViewTarget(final Context context, final ImageView imageView) {

        return new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(BaseApplication.getInstance().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setBackground(circularBitmapDrawable);
            }
        };
    }

    private static RoundedBitmapDrawable createRoundImageWithBorder(Context context, Bitmap bitmap) {
        //原图宽度
        int bitmapWidth = bitmap.getWidth();
        //原图高度
        int bitmapHeight = bitmap.getHeight();
        //边框宽度 pixel
        int borderWidthHalf = 20;

        //转换为正方形后的宽高
        int bitmapSquareWidth = Math.min(bitmapWidth, bitmapHeight);

        //最终图像的宽高
        int newBitmapSquareWidth = bitmapSquareWidth + borderWidthHalf;

        Bitmap roundedBitmap = Bitmap.createBitmap(newBitmapSquareWidth, newBitmapSquareWidth, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundedBitmap);
        int x = borderWidthHalf + bitmapSquareWidth - bitmapWidth;
        int y = borderWidthHalf + bitmapSquareWidth - bitmapHeight;

        //裁剪后图像,注意X,Y要除以2 来进行一个中心裁剪
        canvas.drawBitmap(bitmap, x / 2, y / 2, null);
        Paint borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidthHalf);
        borderPaint.setColor(Color.WHITE);

        //添加边框
        canvas.drawCircle(canvas.getWidth() / 2, canvas.getWidth() / 2, newBitmapSquareWidth / 2, borderPaint);

        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), roundedBitmap);
        roundedBitmapDrawable.setGravity(Gravity.CENTER);
        roundedBitmapDrawable.setCircular(true);
        return roundedBitmapDrawable;
    }

}
