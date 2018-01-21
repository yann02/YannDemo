package com.yann.yanndemo.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yann.yanndemo.R;
import com.yann.yanndemo.ui.dialog.DialogActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_dialog = (Button) findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog:
                startActivity(new Intent(this, DialogActivity.class));
                break;
            default:
                break;
        }
    }
}
