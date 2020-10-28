package com.two.dangsend.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
	private View rootView;// 缓存Fragment view
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		View view = setLayout(inflater, container);
//		ButterKnife.bind(this,view);
//		return view;
		
		if (rootView == null)
		{
			rootView = setLayout(inflater, container);
			ButterKnife.bind(this,rootView);
			
			initView();
			setUpView();
		}
		// 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
		ViewGroup parent = (ViewGroup) rootView.getParent();
		if (parent != null)
		{
			parent.removeView(rootView);
		}
		return rootView;
	}

	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		rootView = null;
	}
	
	protected abstract View setLayout(LayoutInflater inflater, ViewGroup container);
	protected abstract void setUpView();
	protected abstract void initView();
}
