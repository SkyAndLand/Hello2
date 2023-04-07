package com.example.plant.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.plant.Object.PlantData;
import com.example.plant.R;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements View.OnClickListener {
    private static final String TAG = "HomeAdapter";
    private ArrayList<PlantData> plantData;//存放植物基本数据
    //2
    private  OnItemClickListener myOnItemClickListener=null;



    //构造器
    public HomeAdapter(ArrayList<PlantData> plantData) {
        this.plantData = plantData;

    }


    /*加载item布局文件，并将MyViewHolder的对象返回*/
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_plantdata,
                parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        //3、
        view.setOnClickListener(this);


        return holder;
    }
    /*给将获取的数据设置到对应的控件上*/
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.get().load(plantData.get(position).getA()).into(holder.plant_image);
        holder.plant_name.setText(plantData.get(position).getB());//植物名称
        holder.time.setText(plantData.get(position).getK());//时间
        Log.d(TAG, "onBindViewHolder: "+plantData.get(position).getK());
        holder.temperature_value.setText(plantData.get(position).getC()+"℃");//温度
        holder.humidity_value.setText(plantData.get(position).getD()+"%");//湿度
        holder.illumination_value.setText(plantData.get(position).getF()+"ls");//光照
        holder.ph_value.setText(plantData.get(position).getG());//ph

        holder.itemView.setTag(position);




    }


    /*获取植物item条数*/
    @Override
    public int getItemCount() {
        return plantData.size();
    }
    //4、将点击事件转移给外面的调用者
    @Override
    public void onClick(View v) {
        if(myOnItemClickListener!=null){
            //通过getTag获取position
            myOnItemClickListener.onItemClick(v,(int)v.getTag());
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView plant_image;
        TextView plant_name;
        TextView temperature_value;
        TextView humidity_value;
        TextView illumination_value;
        TextView ph_value;
        TextView time;





        public MyViewHolder(View itemview) {
            super(itemview);
            /**布局文件中找到一个id为"……"的什么类型ImageView）控件，并将其实例化为一个名为"……"的变量。*/
            //植物图片
            plant_image = (ImageView) itemview.findViewById(R.id.plant_item_image);


            //植物名称
            plant_name = (TextView) itemview.findViewById(R.id.plant_item_name);
            //温度
            temperature_value = (TextView) itemview.findViewById(R.id.plant_item_temperature_value);
            //湿度
            humidity_value = (TextView) itemview.findViewById(R.id.plant_item_humidity_value);
            //光照
            illumination_value = (TextView) itemview.findViewById(R.id.plant_item_illumination_value);
            //PH
            ph_value = (TextView) itemview.findViewById(R.id.plant_item_ph_value);
            time = (TextView) itemview.findViewById(R.id.plant_item_updatetime);

            //给item设置监听事件
            itemview.setOnClickListener(this);
        }

        //通过:implements View.OnClickListener(实现item点击接口)
        @Override
        public void onClick(View v) {


        }
    }

    //1
    public  static  interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public  void setMyOnItemClickListener(OnItemClickListener listener){
        this.myOnItemClickListener=listener;
    }


}