package com.two.dangsend.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.two.dangsend.MyApplication;
import com.two.dangsend.R;
import com.two.dangsend.adapter.AdapterOrderList;
import com.two.dangsend.db.DBOpenHelp;
import com.two.dangsend.model.OrderModel;
import com.two.dangsend.utils.AppLog;
import com.two.dangsend.utils.SharedPreferencesUtils;

import java.util.List;

import butterknife.BindView;

public class FragmentThree extends BaseFragment implements AdapterOrderList.deleteOrder{

    @BindView(R.id.recyc_three)
    RecyclerView recyclerView;

    private AdapterOrderList orderList;

    private List<OrderModel> modelList;
    @Override
    protected View setLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_three,container,false);
    }

    @Override
    protected void setUpView() {
        setAdapter();
    }

    @Override
    protected void initView() {

    }

    private void setAdapter() {
        modelList = DBOpenHelp.getInstance(getActivity()).getOrderData(SharedPreferencesUtils.getAccount(getActivity()));
        //获取数据
        orderList = new AdapterOrderList(modelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(orderList);
        orderList.setdeleteOrder(this::delete);
    }

    @Override
    public void delete(String num) {
        OrderModel model = new OrderModel();
        model.setOrderSucess("0");//取消订单
        DBOpenHelp.getInstance(getActivity()).updataOrderData(model,num);
        setAdapter();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(recyclerView != null){
                setAdapter();
            }
        }
    }
}
