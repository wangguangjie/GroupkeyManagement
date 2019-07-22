package org.wangguangjie.groupkeymanagement;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ParameterDialog extends Dialog {

    private GKActivity mContext;

    public ParameterDialog(@NonNull GKActivity context) {
        super(context);
        mContext=context;
    }
    public ParameterDialog(GKActivity context,int themId){
        super(context,themId);
        mContext=context;
    }
    @Override
    public void onCreate(Bundle onSaveInstanceState){
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.layout_parameters_dialog);
        final EditText editText=findViewById(R.id.edt1);
        Button  button=findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input=editText.getText().toString();
                if(input!=null){
                    try{
                        int n= Integer.parseInt(input);
                        ParameterDialog.this.dismiss();
                        mContext.setNodeNumber(n);
                    }catch (Exception e){
                        editText.setHint("请输入正确的数字!");
                    }
                }else{
                    editText.setHint("请输入节点数!");
                }
            }
        });
    }
}
