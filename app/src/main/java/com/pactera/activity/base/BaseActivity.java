package com.pactera.activity.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;

import com.pactera.MyApplication;
import com.pactera.utils.SharedPreferencesUtils;
import com.pactera.websocket.JWebSocketClient;

import java.net.URI;


/**
 * 基础Activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Integer page = 1;
    protected String token = SharedPreferencesUtils.INSTANCE.getstring("token", "");
    protected String username = SharedPreferencesUtils.INSTANCE.getstring("username", "");
    protected String user_id = SharedPreferencesUtils.INSTANCE.getstring("user_id", "");

    protected KProgressHUD hud;

    private String TAG = "BaseActivity";

    JWebSocketClient client;

    URI uri = URI.create("ws://192.168.103.32:8888");

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        checkAuth();

        MyApplication.Companion.getActivityList().add(this);

        //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
        Window window = getWindow();

        //设置 paddingTop
//        ViewGroup rootView = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
//        rootView.setPadding(0, getStatusBarHeight(), 0, 0);

//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //设置顶部状态栏颜色
//        window.setStatusBarColor(Color.TRANSPARENT);

        initView(savedInstanceState);

        initListener();

        initData();

        client = new JWebSocketClient(uri) {
//            @Override
//            public void onMessage(String message) {
//                //message就是接收到的消息
//                Log.e("JWebSClientService", message);
//                System.out.println("JWebSClientService connected 哈哈哈哈哈哈");
//            }
        };

    }

    protected abstract void initView(Bundle savedInstanceState);

    protected void initListener(){}

    protected void initData(){}


    @Override
    protected void onResume() {
        super.onResume();
        checkAuth();
    }


    public void checkAuth(){
        // 初始化 SharePreferences
        SharedPreferencesUtils.INSTANCE.init(MyApplication.Companion.getInstance(), "userInfo");

//        if (token==null || token.equals("")) {
//            startActivity(new Intent(BaseActivity.this, LoginActivity.class));
//            finish();
//            System.out.println("没有token");
//        }

    }


    /**
     * 利用反射获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    // 隐藏软键盘
    public void dismissKeyBoard(){
        // 隐藏软键盘
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm!=null){
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override
    public void finish() {
        super.finish();
        dismissKeyBoard();
    }

    /**
     * 是否应该检查权限
     * @return
     */
    public boolean showCheckPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return true;
        } else {
            return false;
        }
    }

}
