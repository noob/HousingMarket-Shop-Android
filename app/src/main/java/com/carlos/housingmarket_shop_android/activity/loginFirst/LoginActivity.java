package com.carlos.housingmarket_shop_android.activity.loginFirst;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carlos.housingmarket_shop_android.R;
import com.carlos.housingmarket_shop_android.util.NO;
import com.carlos.sxl.use.manager.AppManager;
import com.carlos.sxl.use.util.L;
import com.carlos.sxl.use.util.NetUtil;
import com.carlos.sxl.use.util.T;
import com.carlos.sxl.use.util.Tools;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.topText)
    TextView topText;
    @BindView(R.id.mobile_edit)
    EditText mobileEdit;
    @BindView(R.id.verification_code_edit)
    EditText verificationCodeEdit;
    @BindView(R.id.verification_code_btn)
    Button verificationCodeBtn;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;
    private Context context;
    private AppManager appManager;
    private Intent intent;
    private final static String TAG = "LoginActivity";

    private boolean isSend = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context = LoginActivity.this;
        appManager = AppManager.getInstance();
        appManager.addActivity(this);

        init();

    }

    private void init() {
        topText.setText("商户登录");
        initSMSSDK();
        verificationCodeBtn.setOnClickListener(null);
        mobileEdit.addTextChangedListener(new TextWatcher() {
            CharSequence temp;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
                if (temp.length() == 11 && isSend) {
                    verificationCodeBtn.setBackgroundColor(getResources().getColor(R.color.theme_color));
                }else {
                    verificationCodeBtn.setBackgroundColor(getResources().getColor(R.color.some_gray));
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
                if (temp.length() == 11 && isSend) {
                    verificationCodeBtn.setBackgroundColor(getResources().getColor(R.color.theme_color));
                }else {
                    verificationCodeBtn.setBackgroundColor(getResources().getColor(R.color.some_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (temp.length() == 11 && isSend) {
                    verifyPhone();
                } else {
                    verificationCodeBtn.setBackgroundColor(getResources().getColor(R.color.some_gray));
                    verificationCodeBtn.setOnClickListener(null);
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Tools.isClick()) {
                    return;
                }
                if (TextUtils.isEmpty(mobileEdit.getText().toString()) || mobileEdit.getText().toString().length() < 11) {
                    T.showShort(context, "请输入手机号码");
                } else if (TextUtils.isEmpty(verificationCodeEdit.getText().toString())) {
                    T.showShort(context, "请输入验证码");
                } else if (NetUtil.isConnected(context, true)) {
                    SMSSDK.submitVerificationCode("86", mobileEdit.getText().toString(), verificationCodeEdit.getText().toString());
                }
            }
        });

    }

    /**
     * 验证按钮监听
     */
    private void verifyPhone() {
        verificationCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetUtil.isConnected(context, true)) {
                    verificationCodeBtn.setBackgroundColor(getResources().getColor(R.color.gray_shipping));
                    verificationCodeBtn.setClickable(false);
                    isSend = false;
                    SMSSDK.getVerificationCode("86", mobileEdit.getText().toString());

                    // 倒计时 60 秒后可以重新获取验证码
                    new CountDownTimer(1000 * 60, 1000) {

                        @Override
                        public void onTick(long l) {
                            String s = "重新获取(" + (l / 1000) + ")";
                            verificationCodeBtn.setText(s);
                        }

                        @Override
                        public void onFinish() {
                            verificationCodeBtn.setClickable(true);
                            isSend = true;
                            verificationCodeBtn.setBackgroundColor(getResources().getColor(R.color.theme_color));
                            verificationCodeBtn.setText(R.string.get_verification_code);
                        }
                    }.start();

                }
            }
        });

    }

    //自定义Handler
    private final static int SMSSID = 0;
    private final static int GET_SERVER_RESULT = 11;
    private Handler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {

        private final WeakReference<LoginActivity> mActivity;

        public MyHandler(LoginActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final LoginActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case SMSSID:
                        int event = msg.arg1;
                        int result = msg.arg2;
                        Object data = msg.obj;
                        L.d(TAG + " result", result + "");
                        L.d(TAG + " event", event + "");
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            if(data instanceof Boolean) {
                                if((Boolean)data) {
                                    activity.intent = new Intent(activity, PerfectInfoActivity.class);
                                    activity.startActivity(activity.intent);
                                    activity.exit();
                                }
                            } else {
                                //短信注册成功后，返回MainActivity
                                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {   //提交验证码成功
                                    L.i(TAG, "验证成功");
//                                activity.userLogin();
                                    activity.intent = new Intent(activity, PerfectInfoActivity.class);
                                    activity.startActivity(activity.intent);
                                    activity.exit();
                                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                                    L.i(TAG, "验证码已经发送");
                                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {   //返回支持发送验证码的国家列表
                                    L.i(TAG, "获取国家列表成功");
                                }
                            }

                        } else {
                            if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                                L.i(TAG, "获取验证码失败");
                            } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                                L.i(TAG, "验证码错误");
                                T.showShort(activity.context, "验证码错误");
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }


    }

    private EventHandler eventHandler;

    private void initSMSSDK() {
        SMSSDK.initSDK(this, NO.SMSAPPKEY, NO.SMSAPPSECRET);

        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message message = new Message();
                message.arg1 = event;
                message.arg2 = result;
                message.obj = data;
                mHandler.sendMessage(message);
            }
        };
    }


    @Override
    protected void onResume() {
        super.onResume();
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    private void exit() {
        LoginActivity.this.finish();
    }


}
