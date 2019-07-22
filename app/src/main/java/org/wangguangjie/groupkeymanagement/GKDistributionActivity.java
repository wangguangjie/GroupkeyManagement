package org.wangguangjie.groupkeymanagement;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.wangguangjie.groupkeymanagement.utils.NodeDeployFactory;
import org.wangguangjie.groupkeymanagement.view.DeployView;

import java.lang.ref.WeakReference;

public class GKDistributionActivity extends GKActivity{


    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private TextView tv_title;
    private DeployView maps;

    private float mEnergy1;
    private float mEnergy2;
    private float mEnergy3;

    static class MyHandler extends Handler{
        WeakReference<GKDistributionActivity> mWeakReference;
        public MyHandler(GKDistributionActivity context){
            mWeakReference=new WeakReference<>(context);
        }
        @Override
        public void handleMessage(Message msg){
            ExperimentResultDialog dialog=new ExperimentResultDialog(mWeakReference.get(),R.style.loading_dialog);
            dialog.setResult(mWeakReference.get().mEnergy1,mWeakReference.get().mEnergy2,
                    mWeakReference.get().mEnergy3);
            dialog.setTitles(mWeakReference.get().getResources().getString(R.string.veltri),mWeakReference.get().getResources().getString(R.string.porambage));
            dialog.show();
        }
    }
    MyHandler sMyHandler=new MyHandler(this);


    @Override
    public void onCreate(Bundle onSaveInstanceState){
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_layout_distribution);
        tv_title=findViewById(R.id.protocol1);
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //协议描述;
                Toast.makeText(GKDistributionActivity.this,"中心化的群密钥分发协议",Toast.LENGTH_SHORT).show();
            }
        });
        mTextView1=findViewById(R.id.deploy);
        mTextView2=findViewById(R.id.distribute);
        mTextView3=findViewById(R.id.join);
        mTextView4=findViewById(R.id.delete);
        maps=findViewById(R.id.map);
        mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //首先选择节点数量，在选择节点数量之后进行节点部署;
                Dialog dialog=new ParameterDialog(GKDistributionActivity.this,R.style.loading_dialog);
                dialog.show();
            }
        });
        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //群密钥分发;
                maps.setDrawLine(true);
                maps.invalidate();
                //并且四种协议中密钥初始分发所需要的能耗;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        establishGroupKey_Luca();
                        establishGroupKey_Pawani();
                        establishGroupKey_MyScheme();
                        sMyHandler.sendEmptyMessage(0x111);
                    }
                }).start();
            }
        });
        mTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //群成员加入;

                //计算成员加入时四种协议所消耗的能量;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        join_Luca(10);
                        join_Pawani(10);
                        join_MyScheme(10);
                        sMyHandler.sendEmptyMessage(0x111);
                    }
                }).start();
            }
        });
        mTextView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //群成员离开

                //计算成员离开是四种协议所消耗的能量;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        delete_Luca(10);
                        delete_Pawani(10);
                        delete_MyScheme(10);
                        sMyHandler.sendEmptyMessage(0x111);
                    }
                }).start();
            }
        });
    }

    /**
     * 节点部署;
     */
     protected void deploy(){
        maps.setDrawLine(false);
        //随机获取节点;
        maps.setLists(NodeDeployFactory.nodeDeploy(maps.getWidth(),maps.getHeight()));
        //刷新map，重新绘制节点;
        maps.invalidate();
    }

    /**
     * 计算我的方案建立密钥所消耗的能量;
     */
    public void establishGroupKey_MyScheme(){
        //在群密钥建立阶段，由于异或和哈希操作耗费能力太少，在统计能力过程中我们忽略他们的能耗；
        //首先统计子群KDC的能耗;
        float cost;
        cost=maps.getList().size()*17;
        //统计每个群成员的能耗;
        cost=cost*2;
        cost+=0.209*maps.getList().size()*16*8/1000;
        cost+=maps.getList().size()*0.226*maps.getList().size()*16*8/6/1000;
        mEnergy3=cost;
    }

    /**
     * 计算Pwaani等人的方案建立密钥所消耗的能量;
     */
    public void establishGroupKey_Pawani(){
        float cost=0;
        cost=2*maps.getList().size()*17+2*maps.getList().size()*17;
        float te=(16*8* maps.getList().size()+86);
        cost+=(te*0.209+te*0.226*6+6*te*0.209+(16*8*maps.getList().size()+86)*maps.getList().size()*0.226)/1000;
        mEnergy2=cost;
    }

    /**
     * 计算Luca等人的方案建立密钥所消耗的能量;
     */
    public void establishGroupKey_Luca(){
        float cost;
        cost=(maps.getList().size()*0.209f*16*8*1000+maps.getList().size()*0.226f*16*8*1000)/1000;
        mEnergy1=cost;
    }

    /**
     * 计算加入阶段所消耗的能量;
     */
    public void joinPhase_MyScheme(){

    }

    public void joinPhase_Pawani(){

    }

    public void joinPhase_Luca(){

    }

    /**
     * 计算离开阶段所消耗的能量
     */
    public void deletePhase_MyScheme(){

    }

    public void deletePhase_Pawani(){

    }

    public void deletePhase_Luca(){

    }


    public void join_MyScheme(int n){
        float cost;
        cost=n*(0.209f*16*8+0.226f*16*8*maps.getList().size()/6)/1000;
        mEnergy3=cost;
    }

    public void join_Pawani(int n){
        float cost;
        cost=n*(0.209f*16*8+6*0.226f*16*8+6*0.209f*16*8+0.226f*maps.getList().size()*16*8)/1000;
        mEnergy2=cost;
    }

    public void join_Luca(int n){
        float cost;
        cost=n*(0.209f*16*8*10+0.226f*16*8*10)/1000;
        mEnergy1=cost;
    }


    /**
     * 当固定节点数量，针对不同的离开节点的数量进行能量统计
     * @param n 离开的节点数量;
     */
    public void delete_MyScheme(int n){
        float cost;
        cost=maps.getList().size()*17/6+2*maps.getList().size()*17/6;
        cost+=0.209*maps.getList().size()*16*8/1000;
        cost+=maps.getList().size()*0.226*maps.getList().size()*16*8/6/1000;
        mEnergy3=cost;
    }

    public void delete_Pawani(int n){
        float cost;
        cost=2*maps.getList().size()*17+2*maps.getList().size()*17;
        float te=(16*8* maps.getList().size()+86);
        cost+=(te*0.209+te*0.226*6+6*te*0.209+(16*8*maps.getList().size()+86)*maps.getList().size()*0.226)/1000;
        mEnergy2=cost;
    }

    public void delete_Luca(int n){
        float cost;
        int m=n;
        n=maps.getList().size();
        //d=3;
        int d=3;
        cost=(float)((d-1)*(Math.log(n)/Math.log(d))*(Math.log(n)/Math.log(d)-1)*5/2);
        cost+=1.5f*n*5;
        cost+=(d-1)*(Math.log(n)/Math.log(d))*(Math.log(n)/Math.log(d)-1)*16*8*0.209/1000;
        cost+=1.5f*n*16*8*0.226/1000;
        mEnergy1=cost;
    }


    @Override
    public void setNodeNumber(int n) {
        NodeDeployFactory.NUMBER_OF_NODES=n;
        deploy();
    }
}
