package com.xtuone.justhistogram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * iFinVer
 * 2016/2/19 14:23.
 */
public class JustHistogram extends View {

    private List<Summery> data;
    private boolean mobileMode;

    private Paint mPaint;
    private int mTextColor;
    private int mTextSize16;
    private int mTextSize10;
    private RectF mRect;
    private long maxData;
    private String mTextLine1;
    private String mTextLine2;

    public JustHistogram(Context context) {
        super(context);
        init(context);
    }


    public JustHistogram(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public JustHistogram(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mTextColor = Color.parseColor("#cccccc");
        mTextSize16 = sp2px(context, 15);
        mTextSize10 = sp2px(context, 10);
        mRect = new RectF();
    }

    /**
     * @param mobileMode true 显示移动流量,false显示WiFi流量
     */
    public void setData(List<Summery> summeryList, boolean mobileMode) {
        this.data = summeryList;
        this.mobileMode = mobileMode;
        //查找最大的数值
        maxData = 0;
        if (data != null && data.size() > 0) for (Summery summery : summeryList) {
            long sum;
            if (mobileMode) {
                sum = summery.getRec_sys_mobile() + summery.getSend_sys_mobile();
            } else {
                sum = summery.getRec_sys_wifi() + summery.getSend_sys_wifi();
            }
            maxData = maxData < sum ? sum : maxData;
        }
        //计算两条标线数值
        mTextLine1 = Formatter.formatFileSize(getContext(), maxData / 3);
        mTextLine2 = Formatter.formatFileSize(getContext(), maxData / 3 * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long timeSpan = System.currentTimeMillis();
        //画基本框架
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int topMargin = 10;
        int botMargin = 60;
        int rightSpace = 90;
        int lineSpace = (height - botMargin) / 3;
        int histogramSpace = height - topMargin - botMargin;


        mPaint.setTextSize(mTextSize10);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(mTextColor);
        //第一条流量线(60M)
        int linePosition = lineSpace + topMargin;
        canvas.drawLine(0, linePosition, width - rightSpace, linePosition, mPaint);
        canvas.drawText(mTextLine2, width - rightSpace + 10, linePosition + 5, mPaint);
        //第二条流量线(30M)
        linePosition = lineSpace * 2 + topMargin;
        canvas.drawLine(0, linePosition, width - rightSpace, linePosition, mPaint);
        canvas.drawText(mTextLine1, width - rightSpace + 10, linePosition + 5, mPaint);
        //第三条流量线(基线 0M)
        linePosition = lineSpace * 3 + topMargin;
        canvas.drawText("0.0 B", width - rightSpace + 10, linePosition + 5, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0, linePosition, width - rightSpace, linePosition, mPaint);
        //流量标注

        //日期标注短线
        mPaint.setTextSize(mTextSize16);
        float bz = ((float) width - rightSpace) / 4;
        mPaint.setStrokeWidth(3);
        canvas.drawLine(bz, linePosition, bz, linePosition + 10, mPaint);

        canvas.drawLine(bz * 2, linePosition, bz * 2, linePosition + 10, mPaint);

        canvas.drawLine(bz * 3, linePosition, bz * 3, linePosition + 10, mPaint);

        if (data == null || data.size() <= 0) return;

        //取最后一天的数据
        //日期标注
        long date = Summery.calculateDate(System.currentTimeMillis());
        String ft = long2StringDate(date - 4);
        float v = mPaint.measureText(ft);
        canvas.drawText(ft, bz * 3 - v / 2, linePosition + 10 + mTextSize16, mPaint);

        ft = long2StringDate(date - 9);
        v = mPaint.measureText(ft);
        canvas.drawText(ft, bz * 2 - v / 2, linePosition + 10 + mTextSize16, mPaint);

        ft = long2StringDate(date - 14);
        v = mPaint.measureText(ft);
        canvas.drawText(ft, bz - v / 2, linePosition + 10 + mTextSize16, mPaint);
        //画系统的柱状图
        float rectAndMarginWidth = bz / 5;
        float rectWidth = rectAndMarginWidth * 0.6f;
        float histoPointer = bz * 4;
        int dataPointer = 0;
        mPaint.setColor(mTextColor);
        for (int i = 0; i < 15 && data.size() >= dataPointer + 1; i++, date--) {
            Summery summery = data.get(dataPointer);
            histoPointer -= rectAndMarginWidth;
            if (summery.getDate() == date) {
                dataPointer++;

                long sum;
                if (mobileMode) {
                    sum = summery.getRec_sys_mobile() + summery.getSend_sys_mobile();
                } else {
                    sum = summery.getRec_sys_wifi() + summery.getSend_sys_wifi();
                }
                if (sum == 0) continue;

                //计算当前的柱的左变的x点
                //计算当前柱的高度
                int heightOfHisto = (int) (histogramSpace * ((double) sum / maxData));
                mRect.set(histoPointer, linePosition - heightOfHisto, histoPointer + rectWidth, linePosition - 1);
                canvas.drawRect(mRect, mPaint);
            }
        }
        //画app使用流量的柱状图
        histoPointer = bz * 4;
        dataPointer = 0;
        date = Summery.calculateDate(System.currentTimeMillis());
        mPaint.setColor(Color.GREEN);
        for (int i = 0; i < 15 && data.size() >= dataPointer + 1; i++, date--) {
            Summery summery = data.get(dataPointer);
            histoPointer -= rectAndMarginWidth;
            if (summery.getDate() == date) {
                dataPointer++;

                long sum;
                if (mobileMode) {
                    sum = summery.getRec_mine_mobile() + summery.getSend_mine_mobile();
                } else {
                    sum = summery.getRec_mine_wifi() + summery.getSend_mine_wifi();
                }
                if (sum == 0) continue;

                //计算当前的柱的左变的x点
                //计算当前柱的高度
                int heightOfHisto = (int) (histogramSpace * ((double) sum / maxData));
                mRect.set(histoPointer, linePosition - heightOfHisto, histoPointer + rectWidth, linePosition - 1);
                canvas.drawRect(mRect, mPaint);
            }
        }
        timeSpan = System.currentTimeMillis() - timeSpan;
        Log.d("Just", "绘制一次耗时:" + timeSpan);
    }

    @NonNull
    private String long2StringDate(long value) {
        return String.valueOf(value % 10000 / 100) + "月" + String.valueOf(value % 100) + "日";
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
