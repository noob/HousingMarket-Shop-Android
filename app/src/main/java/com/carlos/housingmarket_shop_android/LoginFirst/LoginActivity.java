package com.carlos.housingmarket_shop_android.LoginFirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.carlos.housingmarket_shop_android.R;

public class LoginActivity extends AppCompatActivity {

    private TextView topText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        topText = (TextView) super.findViewById(R.id.topText);

        init();

    }

    private void init() {
        topText.setText("商户登录");
    }
}
