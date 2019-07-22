package org.wangguangjie.groupkeymanagement.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;

import org.wangguangjie.groupkeymanagement.R;

public class DistributionDeployView extends DeployView {

    public DistributionDeployView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DistributionDeployView(Context context){
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        mPaint=new Paint();
        mPaint.setColor(getResources().getColor(R.color.node_color1,null));
        int x11=getWidth()/4;
        int x12=x11+getWidth()/2;
        int y11=getHeight()/6;
        int y21=y11+getHeight()/3;
        int y31=y21+getHeight()/3;
        canvas.drawCircle(x11,y11,30,mPaint);
        canvas.drawCircle(x12,y11,30,mPaint);
        canvas.drawCircle(x11,y21,30,mPaint);
        canvas.drawCircle(x12,y21,30,mPaint);
        canvas.drawCircle(x11,y31,30,mPaint);
        canvas.drawCircle(x12,y31,30,mPaint);
        canvas.drawCircle(getWidth()/2,getHeight()/2,50,mPaint);

        mPaint.setColor(getResources().getColor(R.color.node_color_black,null));
        //节点绘制;
        for(int i=0;i<mLists.size();i++){
            canvas.drawCircle(mLists.get(i).getX(),mLists.get(i).getY(),15,mPaint);
        }
        //绘制线段;
        if(canDrawLine()){
            mPaint.setStrokeWidth(7.5f);
            mPaint.setColor(getResources().getColor(R.color.line_color1,null));
            canvas.drawLine(x11,y11,getWidth()/2,getHeight()/2,mPaint);
            canvas.drawLine(x12,y11,getWidth()/2,getHeight()/2,mPaint);
            canvas.drawLine(x11,y21,getWidth()/2,getHeight()/2,mPaint);
            canvas.drawLine(x12,y21,getWidth()/2,getHeight()/2,mPaint);
            canvas.drawLine(x11,y31,getWidth()/2,getHeight()/2,mPaint);
            canvas.drawLine(x12,y31,getWidth()/2,getHeight()/2,mPaint);


            //try
            {
                // Thread.sleep(500);
                mPaint.setColor(getResources().getColor(R.color.node_color_black,null));
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setAntiAlias(true);
                mPaint.setStrokeWidth(2);
                mPaint.setPathEffect(new DashPathEffect(new float[] { 0, 10, 35, 2 }, 1));
                //绘制长度为4的实线后再绘制长度为4的空白区域，0位间隔
                //canvas.drawCircle(width, height, mRadius - 5, mPaintCircle);
                canvas.drawLine(0,0,getWidth()/2,0,mPaint);
                canvas.drawLine(getWidth()/2,0,getWidth()/2,getHeight()/3,mPaint);
                canvas.drawLine(getWidth()/2,getHeight()/3,0,getHeight()/3,mPaint);
                canvas.drawLine(7,getHeight()/3,7,0,mPaint);
                //Thread.sleep(500);
                canvas.drawLine(getWidth()/2,0,getWidth(),0,mPaint);
                canvas.drawLine(getWidth(),0,getWidth(),getHeight()/3,mPaint);
                canvas.drawLine(getWidth(),getHeight()/3,getWidth()/2,getHeight()/3,mPaint);
                canvas.drawLine(getWidth()/2,getHeight()/3,getWidth()/2,0,mPaint);

                //Thread.sleep(500);
                canvas.drawLine(getWidth()/2,getHeight()/3,getWidth(),getHeight()/3,mPaint);
                canvas.drawLine(getWidth(),getHeight()/3,getWidth(),getHeight()*2/3,mPaint);
                canvas.drawLine(getWidth(),getHeight()*2/3,getWidth()/2,getHeight()*2/3,mPaint);
                canvas.drawLine(getWidth()/2,getHeight()*2/3,getWidth()/2,getHeight()/3,mPaint);

                //Thread.sleep(500);
                canvas.drawLine(0,getHeight()/3,getWidth()/2,getHeight()/3,mPaint);
                canvas.drawLine(getWidth()/2,getHeight()/3,getWidth()/2,getHeight()*2/3,mPaint);
                canvas.drawLine(getWidth()/3,getHeight()*2/3,0,getHeight()*2/3,mPaint);
                canvas.drawLine(7,getHeight()*2/3,7,getHeight()/3,mPaint);

                //Thread.sleep(500);
                canvas.drawLine(0,getHeight()*2/3,getWidth()/2,getHeight()*2/3,mPaint);
                canvas.drawLine(getWidth()/2,getHeight()*2/3,getWidth()/2,getHeight(),mPaint);
                canvas.drawLine(getWidth()/2,getHeight(),0,getHeight(),mPaint);
                canvas.drawLine(7,getHeight(),7,getHeight()*2/3,mPaint);

                //Thread.sleep(500);
                canvas.drawLine(getWidth()/2,getHeight()*2/3,getWidth(),getHeight()*2/3,mPaint);
                canvas.drawLine(getWidth(),getHeight()*2/3,getWidth(),getHeight(),mPaint);
                canvas.drawLine(getWidth(),getHeight(),getWidth()/2,getHeight(),mPaint);
                canvas.drawLine(getWidth()/2,getHeight(),getWidth()/2,getHeight()*2/3,mPaint);

            }
        }
    }
}
