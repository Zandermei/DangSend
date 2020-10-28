/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.two.dangsend.ui;


import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.two.dangsend.utils.ActivityManager;
import com.two.dangsend.utils.ScreenUtil;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity {


	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		ActivityManager.getAppManager().addActivity(this);
		if (beforeSetLayout()) {
			setLayout();
			ButterKnife.bind(this);
			ScreenUtil.setScreenData(this);
			initView();
			setUpView();
		} else {
			finish();
		}
	}



	protected abstract boolean beforeSetLayout();// 返回true则显示该view，false直接finish

	protected abstract void setLayout();// 设置显示的view

	protected abstract void initView();// 初始化view

	protected abstract void setUpView();// 设置view的数据等
}
