14
https://raw.githubusercontent.com/FanChael/MVVM/master/librarys/lib_network/src/main/java/com/hl/lib_network/controller/presenter/BaseControlPresenter.java
package com.hl.lib_network.controller.presenter;

import com.hl.anotation.NotProguard;
import com.hl.lib_network.NetWork;
import com.hl.lib_network.controller.BaseControlContract;
import com.hl.lib_network.net.exception.ExceptionHandle;
import com.hl.lib_network.net.response.HttpResponse;
import com.hl.lib_network.net.response.ParseManager;
import com.hl.lib_network.net.response.ResponsePreHandle;
import com.hl.lib_network.net.response.TypeCallBack;
import com.hl.lib_network.net.retrofit.BaseObserver;
import com.hl.lib_network.net.url.NetUrl;
import com.hl.lib_network.service.BaseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *@Description: 请求服务表示层
 *@Author: hl
 *@Time: 2018/11/21 13:54
 */
@NotProguard
public class BaseControlPresenter implements BaseControlContract.Presenter {
    private BaseControlContract.View view;                      ///< 数据【对象】请求
    private BaseService baseService;                            ///< 请求服务
    private List<Disposable> subscriptionList = new ArrayList<>();

    private HashMap<String, Object> externParams = new HashMap<>();
    private HashMap<String, String> headers = new HashMap<>();
    private String lastRequestTime = "";
    private boolean bNeedRequestime = false;

    /**
     * 请求方法对应的方法的列表 - 多次连续调用如果已经存在请求，不再请求；防止多次请求
     */
    private List<String> requestList = new ArrayList<>();

    private BaseControlPresenter() {
        this.baseService = NetWork.getmRService(NetUrl.base_url, BaseService.class, 60);
    }

    public BaseControlPresenter(BaseControlContract.View _view) {
        this();
        this.view = _view;
    }

    /**
     * 添加请求参数
     *
     * @param key
     * @param value
     * @return
     */
    public BaseControlPresenter addParam(String key, Object value) {
        externParams.put(key, value);
        return this;
    }

    /**
     * 添加请求Header
     *
     * @param key
     * @param value
     * @return
     */
    public BaseControlPresenter addHeaders(String key, String value) {
        headers.put(key, value);
        return this;
    }

    /**
     * 是否请求时带上一次请求时间RequestTime参数(当前页刷新的话，这个可能有用)
     *
     * @param bNeedRequestime
     */
    public BaseControlPresenter enableRequestTime(boolean bNeedRequestime) {
        this.bNeedRequestime = bNeedRequestime;
        return this;
    }

    @Override
    public void releaseResource() {
        for (int i = 0; i < subscriptionList.size(); ++i) {
            if (null != subscriptionList.get(i) && !subscriptionList.get(i).isDisposed()) {
                subscriptionList.get(i).dispose();
            }
        }
        requestList.clear();
        subscriptionList.clear();
        ///< 清空cookiejar内容
        NetWork.clearByCookie(NetUrl.base_url);
    }

    @Override
    public void requestData(String _function, TypeCallBack _resultCallBack, HashMap<String, String> _paramList, boolean _bShowDialog) {
        getPostData(_function, _resultCallBack, _paramList, true, _bShowDialog);
    }

    @Override
    public void postData(String _function, TypeCallBack _resultCallBack, HashMap<String, String> _paramList, boolean _bShowDialog) {
        getPostData(_function, _resultCallBack, _paramList, false, _bShowDialog);
    }

    /**
     * get post请求分别处理
     *
     * @param _function
     * @param _paramList
     * @param bIsRequest
     */
    private void getPostData(final String _function, final TypeCallBack _resultCallBack, HashMap<String, String> _paramList, boolean bIsRequest, final boolean _bShowDialog) {
        // 添加当前请求标识,防止多次请求
        synchronized (BaseControlPresenter.class){
            if (addRequestFlag(_function)) {
                return;
            }
        }
        ///< 显示进度条
        if (_bShowDialog) {
            view.showDialog();
        }
        HashMap<String, String> paramList = null;
        if (null == _paramList) {
            paramList = new HashMap<>();
        } else {
            paramList = new HashMap<>(_paramList);
        }
        // 加入额外请求参数
        if (null != externParams && externParams.size() > 0) {
            for (Map.Entry<String, Object> entry : externParams.entrySet()) {
                if (entry.getValue() instanceof Integer) {
                    paramList.put(entry.getKey(), String.valueOf(entry.getValue()));
                } else {
                    paramList.put(entry.getKey(), (String) entry.getValue());
                }
            }
            externParams.clear();
        }
        // 是否带上上一次请求的时间
        if (bNeedRequestime && !lastRequestTime.equals("")) {
            paramList.put("starttime", lastRequestTime);
        }

        Observable<HttpResponse<String>> observable = null;
        if (bIsRequest) {
            observable = baseService.getData(_function, paramList);
        } else {
            observable = baseService.postData(_function, paramList);
        }
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new ResponsePreHandle<>(new ResponsePreHandle.CallMe<Object, String>() {
                    @Override
                    public Object onCall(String data, String requestTime) {
                        lastRequestTime = requestTime;
                        return ParseManager.getBean(data, _resultCallBack);
                    }
                }))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Object>() {

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onErrors(ExceptionHandle.ResponeThrowable responeThrowable) {
                        ///< 接下来就可以根据状态码进行处理...
                        int statusCode = responeThrowable.code;
                        switch (statusCode) {
                            case ExceptionHandle.ERROR.TOKEN:
                                view.onLoinOut(_function, new Object[]{statusCode, responeThrowable.message});
                                view.showToast("你的账号异常，请重新登录，谢谢!");
                                break;
                            case ExceptionHandle.ERROR.THIRD_BIND:
                                ///< 加载重试
                                if (_bShowDialog) {
                                    view.retryDialog();
                                }
                                // 需要绑定账号 - 此时其实是成功的一种情况，所以直接return.
                                view.onThirdBind(_function, 11);

                                // 清除本次请求
                                clearRequestFlag(_function);
                                return;
                            default:
                                view.showToast(responeThrowable.message);
                                break;
                        }
                        ///< 加载重试
                        if (_bShowDialog) {
                            view.retryDialog();
                        }
                        view.onFailed(_function, responeThrowable.message);

                        // 清除本次请求
                        clearRequestFlag(_function);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        subscriptionList.add(d);
                    }

                    @Override
                    public void onSuccess(Object data) {
                        view.onSucess(_function, data);
                        ///< 显示进度条
                        if (_bShowDialog) {
                            view.disDialog();
                        }

                        // 清除本次请求
                        clearRequestFlag(_function);
                    }
                });
    }

    /**
     * 添加当前请求标识
     *
     * @param _function
     */
    private boolean addRequestFlag(String _function) {
        if (requestList.contains(_function)) {
            return true;
        }
        requestList.add(_function);
        return false;
    }

    /**
     * 清除当前请求标识
     *
     * @param _function
     */
    private void clearRequestFlag(String _function) {
        // 清除当前请求标识
        if (requestList.contains(_function)) {
            requestList.remove(_function);
        }
    }
}