package org.wangguangjie.groupkeymanagement;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.wangguangjie.groupkeymanagement.utils.NodeDeployFactory;
import org.wangguangjie.groupkeymanagement.view.NegotiationDeployView;

import java.lang.ref.WeakReference;

public class GKNegotiationActivity extends GKActivity {


    private TextView tv_title;

    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;


    private float mEnergy1;
    private float mEnergy2;
    private float mEnergy3;

    private NegotiationDeployView maps;

    static class MyHandler extends Handler{

        WeakReference<GKNegotiationActivity> mWeakReference;
        public MyHandler(GKNegotiationActivity gkNegotiationActivity){
            mWeakReference=new WeakReference<>(gkNegotiationActivity);
        }
        @Override
        public void handleMessage(Message msg){
            //
            ExperimentResultDialog dialog=new ExperimentResultDialog(mWeakReference.get(),R.style.loading_dialog);
            dialog.setResult(mWeakReference.get().mEnergy1,mWeakReference.get().mEnergy2,mWeakReference.get().mEnergy3);
            dialog.setTitles(mWeakReference.get().getResources().getString(R.string.kim),mWeakReference.get().getResources().getString(R.string.lee));
            dialog.show();
        }
    }
    MyHandler mMyHandler=new MyHandler(GKNegotiationActivity.this);


    @Override
    public void onCreate(Bundle onSaveInstanceState){
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_layout_negotiation);
        tv_title=findViewById(R.id.protocol2);
        mTextView1=findViewById(R.id.deploy);
        mTextView2=findViewById(R.id.distribute);
        mTextView3=findViewById(R.id.join);
        mTextView4=findViewById(R.id.delete);
        maps=findViewById(R.id.map);

        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GKNegotiationActivity.this,"分布式的群密钥协商协议",Toast.LENGTH_SHORT).show();
            }
        });
        mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //节点部署;
               // deploy();
                ParameterDialog dialog=new ParameterDialog(GKNegotiationActivity.this,R.style.loading_dialog);
                dialog.show();
            }
        });
        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maps.setDrawLine(true);
                maps.invalidate();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //密钥协商计算能量;
                        computeSK_Kim();
                        computeSK_Lee();
                        computeSK_OurScheme();
                        //
                        mMyHandler.sendEmptyMessage(0x111);

                    }
                }).start();
            }
        });

        mTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        join_Kim();
                        join_Lee();
                        join_OurScheme();
                        mMyHandler.sendEmptyMessage(0x111);
                    }
                }).start();
            }
        });
        mTextView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        delete_Kim();
                        delete_Lee();
                        delete_OurScheme();
                        mMyHandler.sendEmptyMessage(0x111);
                    }
                }).start();
            }
        });
    }

    //节点部署;
    private void deploy(){
        maps.setDrawLine(false);
        maps.setLists(NodeDeployFactory.nodeDeploy(maps.getWidth(),maps.getHeight()));
        maps.invalidate();
    }
    //


    private void computeSK_Kim(){
        float cost;
        cost=(float)(maps.getList().size()*Math.log(maps.getList().size()))*17;
        cost+=(float)(maps.getList().size()*Math.log(maps.getList().size())*8*16*(0.209+0.226)/1000);
        mEnergy1=cost;
    }
    private void computeSK_Lee(){
        float cost;
        cost=(float)(maps.getList().size()*Math.log(maps.getList().size()))*17;
        cost+=(float)(maps.getList().size()*Math.log(maps.getList().size())*8*16*(0.209+0.226)/1000);
        mEnergy2=cost;
    }
    private void computeSK_OurScheme(){
        float cost;
        cost=(maps.getList().size()*2-2)*17;
        cost+=(maps.getList().size()-1)*2*(0.209+0.226)*16*8/1000;
        mEnergy3=cost;
    }



    private void join_Kim(){
        float cost;
        cost=(float)(2*maps.getList().size()+3*Math.log(maps.getList().size())/2)*17;
        cost+=2*(Math.log(maps.getList().size()+1))*(0.209+0.226)*16*8/1000;
        mEnergy1=cost;
    }
    private void join_Lee(){
        float cost;
        cost=(float)(2*maps.getList().size()+3*Math.log(maps.getList().size())/2)*17;
        cost+=2*(Math.log(maps.getList().size()+1))*(0.209+0.226)*16*8/1000;
        mEnergy2=cost;
    }
    private void join_OurScheme(){
        float cost;
        cost=((maps.getList().size()+1)*2-2)*17;
        cost+=maps.getList().size()*2*(0.209+0.226)*16*8/1000;
        mEnergy3=cost;
    }

    private void delete_Kim(){
        float cost;
        cost=(float)(2*maps.getList().size()-3-Math.log(maps.getList().size())/2)*17;
        cost+=2*(Math.log(maps.getList().size()+1))*(0.209+0.226)*16*8/1000;
        mEnergy1=cost;
    }

    private void delete_Lee(){
        float cost;
        cost=(float)(2*maps.getList().size()-3-Math.log(maps.getList().size())/2)*17;
        cost+=2*(Math.log(maps.getList().size()+1))*(0.209+0.226)*16*8/1000;
        mEnergy2=cost;
    }

    private void delete_OurScheme(){
        float cost;
        cost=(maps.getList().size()-2)*2*17;
        cost+=(maps.getList().size()-2)*2*(0.209+0.226)*16*8/1000;
        mEnergy3=cost;
    }
    @Override
    public void setNodeNumber(int n) {
        NodeDeployFactory.NUMBER_OF_NODES=n;
        deploy();
    }
}
