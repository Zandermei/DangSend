package com.two.dangsend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.two.dangsend.MyApplication;
import com.two.dangsend.R;
import com.two.dangsend.utils.ScreenUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentOneAdapter extends BaseAdapter {

    private String[] title = {"巧克力蛋糕","草莓蛋糕"};
    private int[] imgs = {R.drawable.dg_two,R.drawable.db_one};
    private LayoutInflater inflater;
    private Context mContext;

    public FragmentOneAdapter(Context context){
        this.inflater= LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            view = inflater.inflate(R.layout.adapter_one,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.oneTv.setText(title[i]);
        Glide.with(mContext).load(imgs[i]).into(holder.oneImg);

        return view;
    }
    class ViewHolder{

        @BindView(R.id.one_line)
        RelativeLayout lineOne;
        @BindView(R.id.one_img)
        ImageView oneImg;
        @BindView(R.id.one_tv)
        TextView oneTv;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            ScreenUtil.setLinearLayoutParams(lineOne,180,180,10,0,0,0);
            ScreenUtil.setRelativeLayoutParams(oneImg,120,120,0,0,0,0);
            ScreenUtil.setRelativeLayoutParams(oneTv,180,50,5,0,0,0);
            ScreenUtil.setTextSize(oneTv,28);
        }
    }
}
