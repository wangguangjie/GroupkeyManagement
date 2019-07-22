package org.wangguangjie.groupkeymanagement;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExperimentResultDialog extends Dialog {

    GKActivity mContext;

    TextView textView1;
    TextView textView2;
    TextView textView3;

    public ExperimentResultDialog(@NonNull GKActivity context) {
        super(context);
        mContext=context;
    }

    public ExperimentResultDialog(GKActivity context, int themeId){
        super(context,themeId);
        mContext=context;
    }

    @Override
    public void onCreate(Bundle onSaveInstanceState){
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.layout_experiment_result_dialog);
        textView1=findViewById(R.id.scheme1);
        textView2=findViewById(R.id.scheme2);
        textView3=findViewById(R.id.scheme3);
        Button button=findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        textView1.setText(s1+": "+mEnergy1);
        textView2.setText(s2+": "+mEnergy2);
        textView3.setText(s3+": "+mEnergy3);
    }

    private float mEnergy1;
    private float mEnergy2;
    private float mEnergy3;

    private String s1;
    private String s2;
    private String s3;
    public void setResult(float e1,float e2,float e3){
        mEnergy1=e1;
        mEnergy2=e2;
        mEnergy3=e3;
    }

    public void setTitles(String ss1,String ss2){
        s1=ss1;
        s2=ss2;
        s3="我们的协议";
    }
}
