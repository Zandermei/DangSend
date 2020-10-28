package com.two.dangsend.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.two.dangsend.R;
import com.two.dangsend.model.OrderModel;
import com.two.dangsend.utils.ScreenUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterOrderList extends RecyclerView.Adapter<AdapterOrderList.HolderList> {


    private List<OrderModel> models;

    public AdapterOrderList(List<OrderModel> modelList){
        this.models = modelList;
    }

    @NonNull
    @Override
    public HolderList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_list,parent,false);

        return new HolderList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderList holder, int position) {
        OrderModel model =models.get(position);
        holder.orderBtn.setVisibility(View.GONE);
        holder.orderImg.setVisibility(View.GONE);
        holder.orderLineTop.setVisibility(View.GONE);
        if(model.getOrderSucess().equals("1")){
            holder.goodsMoney.setText("合计"+model.getOrderMoney());
            holder.goodsName.setText(model.getOrderName());
            holder.orderAddressTv.setText(model.getOrderAddress());
            holder.orderPhoneAndName.setText(model.getOrderPeopleName()+"  "+model.getOrderPhone());
            holder.orderTime.setText(model.getOrderTime());


            holder.orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(order != null){
                        order.delete(model.getOrderNum());
                    }
                }
            });

            holder.orderLineTop.setVisibility(View.VISIBLE);
            holder.orderBtn.setVisibility(View.VISIBLE);
            holder.orderImg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }



    public class HolderList extends RecyclerView.ViewHolder {
        @BindView(R.id.order_line_top)
        RelativeLayout orderLineTop;
        @BindView(R.id.order_line_center)
        RelativeLayout orderLineCenter;
        @BindView(R.id.order_center_img)
        ImageView orderImg;
        @BindView(R.id.order_center_time)
        TextView orderTime;
        @BindView(R.id.order_phone_name)
        TextView orderPhoneAndName;
        @BindView(R.id.order_address_tv)
        TextView orderAddressTv;
        @BindView(R.id.order_line_money)
        RelativeLayout orderLineMoney;
        @BindView(R.id.order_goods_name)
        TextView goodsName;
        @BindView(R.id.order_goods_money)
        TextView goodsMoney;




        @BindView(R.id.order_line_btom)
        RelativeLayout orderLineBtom;
        @BindView(R.id.order_btn)
        Button orderBtn;

        public HolderList(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            ScreenUtil.setRelativeLayoutParams(orderLineTop,0,380,15,0,25,25);
            ScreenUtil.setRelativeLayoutParams(orderLineCenter,0,50,10,0,20,20);
            ScreenUtil.setRelativeLayoutParams(orderImg,35,35,0,0,0,0);
            ScreenUtil.setRelativeLayoutParams(orderTime,360,45,0,0,15,0);
            ScreenUtil.setRelativeLayoutParams(orderPhoneAndName,0,60,15,0,20,20);
            ScreenUtil.setRelativeLayoutParams(orderAddressTv,0,45,10,0,20,20);
            ScreenUtil.setTextSize(orderTime,26);
            ScreenUtil.setTextSize(orderPhoneAndName,32);
            ScreenUtil.setTextSize(orderAddressTv,28);
            ScreenUtil.setRelativeLayoutParams(orderLineMoney,0,80,30,0,0,0);
            ScreenUtil.setRelativeLayoutParams(goodsName,430,50,0,0,20,0);
            ScreenUtil.setRelativeLayoutParams(goodsMoney,200,50,0,0,0,20);
            ScreenUtil.setTextSize(goodsMoney,24);
            ScreenUtil.setTextSize(goodsName,32);



            ScreenUtil.setRelativeLayoutParams(orderLineBtom,0,60,0,0,0,0);
            ScreenUtil.setRelativeLayoutParams(orderBtn,180,55,0,0,0,15);
            ScreenUtil.setTextSize(orderBtn,28);


        }
    }


    public interface deleteOrder{
        void delete(String num);
    }
    private deleteOrder order;

    public void setdeleteOrder(deleteOrder deleteOrder){
        this.order = deleteOrder;
    }
}
