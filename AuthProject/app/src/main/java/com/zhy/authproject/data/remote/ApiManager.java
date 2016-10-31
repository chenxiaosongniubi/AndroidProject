package com.zhy.authproject.data.remote;

import android.app.Application;

import com.zhy.authproject.data.remote.model.ActivityInfo;
import com.zhy.authproject.data.remote.model.BaseResponseFunc;
import com.zhy.authproject.data.remote.model.CommonInfo;
import com.zhy.authproject.data.remote.model.User;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhanghaoye on 10/24/16.
 */

public class ApiManager {
    private final ApiService apiService;

    private final Application application;

    public ApiManager(ApiService apiService, Application application) {
        this.apiService = apiService;
        this.application = application;
    }

    //登录
    public void login(String username, String password,SimpleCallback<User> simpleCallback){
        apiService.login(username,password)
                .flatMap(new BaseResponseFunc<User>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ExceptionSubscriber<User>(simpleCallback,application));
    }

   //获取活动列表
    public void getActivities(String id,String user_id,String access_token,SimpleCallback<ActivityInfo> simpleCallback){
        apiService.getActivityInfo(id,user_id,access_token)
                .flatMap(new BaseResponseFunc<ActivityInfo>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ExceptionSubscriber<ActivityInfo>(simpleCallback,application));
    }

    //修改活动信息
    public void modifyActivity(String activity_id,String user_id, String access_token,SimpleCallback<CommonInfo> simpleCallback){
        apiService.modifyActivity(activity_id,user_id,access_token)
                .flatMap(new BaseResponseFunc<CommonInfo>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ExceptionSubscriber<CommonInfo>(simpleCallback,application));
    }


}
