package org.wangguangjie.groupkeymanagement.utils;

import android.util.Log;

import org.wangguangjie.groupkeymanagement.model.Node;

import java.util.ArrayList;
import java.util.Random;

public class NodeDeployFactory {

    static final int DEFAULT_ROW=3;
    static final int DEFAUT_COL=2;

    static final int DEFAULT_WIDTH=120;
    static final int DEFAULT_HEIGHT=120;

    static public int NUMBER_OF_NODES=120;

    static public ArrayList<Node> nodeDeploy(float width,float height){
        return nodeDeploy(width,height,DEFAULT_ROW,DEFAUT_COL,NUMBER_OF_NODES);
    }

    /**
     * 对节点进行布局;
     * @param row
     * @param col
     * @return
     */
    static public ArrayList<Node> nodeDeploy(float width,float height,int row,int col,int n){
        ArrayList<Node> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            Random random=new Random();
            float x=random.nextFloat()*width;
            float y=random.nextFloat()*height;
            Log.d("Test111",x+" : "+y);
            Node node=new Node(x,y);
            list.add(node);
        }
        return list;
    }
}
