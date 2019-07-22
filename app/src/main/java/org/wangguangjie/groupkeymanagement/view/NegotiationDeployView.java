package org.wangguangjie.groupkeymanagement.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import org.wangguangjie.groupkeymanagement.R;
import org.wangguangjie.groupkeymanagement.model.Node;

public class NegotiationDeployView extends DeployView {

    public NegotiationDeployView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NegotiationDeployView(Context context){
        super(context);
    }
    @Override
    public void onDraw(Canvas canvas){
       // super.onDraw(canvas);
        mPaint=new Paint();
        mPaint.setColor(getResources().getColor(R.color.node_color_black,null));
        //节点绘制;
        for(int i=0;i<mLists.size();i++){
            canvas.drawCircle(mLists.get(i).getX(),mLists.get(i).getY(),20,mPaint);
        }
        if(canDrawLine()){
            //对list进行排序;
            for(int i=0;i<mLists.size();i++){
                for(int j=mLists.size()-1;j>i;j--){
                    if(mLists.get(j).getX()<mLists.get(j-1).getX()){
                        float x=mLists.get(j).getX();
                        float y=mLists.get(j).getY();
                        mLists.get(j).setX(mLists.get(j-1).getX());
                        mLists.get(j).setY(mLists.get(j-1).getY());
                        mLists.get(j-1).setX(x);
                        mLists.get(j-1).setY(y);
                    }
                }
            }
            mPaint.setColor(getResources().getColor(R.color.node_color1,null));
            for(int i=0;i<mLists.size()-1;i++){
                canvas.drawLine(mLists.get(i).getX(),mLists.get(i).getY(),mLists.get(i+1).getX(),mLists.get(i+1).getY(),mPaint);
            }
            canvas.drawLine(mLists.get(0).getX(),mLists.get(0).getY(),mLists.get(mLists.size()-1).getX(),mLists.get(mLists.size()-1).getY(),mPaint);
        }
    }

}
