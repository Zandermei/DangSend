package com.two.dangsend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.two.dangsend.MyApplication;
import com.two.dangsend.R;
import com.two.dangsend.utils.ScreenUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentOneRecycAdapter extends RecyclerView.Adapter<FragmentOneRecycAdapter.ViewHolder> {
    private Context context = MyApplication.getApplication();
    private List<Integer> imgList;
    private List<String> nameList;
    private List<String> moneyList;

    public FragmentOneRecycAdapter(List<Integer> img,List<String> name,List<String> money){
        this.imgList = img;
        this.nameList = name;
        this.moneyList = money;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_one_recyc,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.goodsMoney.setText(moneyList.get(position));
        holder.goodsName.setText(nameList.get(position));
        Glide.with(context).load(imgList.get(position)).into(holder.recycImg);


        holder.recycLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onclickMsgLook != null){
                    onclickMsgLook.onLCick(nameList.get(position),moneyList.get(position),position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyc_line)
        RelativeLayout recycLine;
        @BindView(R.id.recyc_img)
        ImageView recycImg;
        @BindView(R.id.recyc_goods_name)
        TextView goodsName;
        @BindView(R.id.recyc_goods_money)
        TextView goodsMoney;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            ScreenUtil.setLinearLayoutParams(recycLine,350,500,10,10,5,5);
            ScreenUtil.setRelativeLayoutParams(recycImg,350,380,0,0,0,0);
            ScreenUtil.setRelativeLayoutParams(goodsName,350,75,5,0,15,0);
            ScreenUtil.setRelativeLayoutParams(goodsMoney,350,40,0,0,15,0);
            ScreenUtil.setTextSize(goodsName,26);
            ScreenUtil.setTextSize(goodsMoney,30);

        }
    }

    public interface OnclickMsgLook{
        void onLCick(String name,String money,int id);
    }
    private OnclickMsgLook onclickMsgLook;

    public void setOnclickMsgLook(OnclickMsgLook look){
     this.onclickMsgLook = look;
    }
}
