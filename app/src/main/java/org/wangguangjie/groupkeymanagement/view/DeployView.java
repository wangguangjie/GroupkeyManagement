package org.wangguangjie.groupkeymanagement.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import org.wangguangjie.groupkeymanagement.R;
import org.wangguangjie.groupkeymanagement.model.Node;

import java.util.ArrayList;

public class DeployView extends View {

    protected ArrayList<Node> mLists=new ArrayList<>();

    protected boolean drawLine=false;

    protected Paint mPaint;

    public DeployView(Context context) {
        super(context);
    }
    public DeployView(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
    }

    public void setLists(ArrayList<Node> data){
        mLists=data;
    }

    public void setDrawLine(boolean canDrawLine){
        drawLine=canDrawLine;
    }
    protected boolean canDrawLine(){
        return drawLine;
    }

    public ArrayList<Node> getList(){
        return mLists;
    }

}
