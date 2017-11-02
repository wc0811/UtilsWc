package com.cw.utilswc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cw.utilswc.Utils.ToastUtils;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private int iCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;


        ToastUtils.showToast(context, "1s显示", 1);
        TextView tvShowToast = findViewById(R.id.tv_ShowToast);
        tvShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (iCount) {
                    case 0:
                        ToastUtils.showLongToast(context, "长时间显示");
                        break;
                    case 1:
                        ToastUtils.showToast(context, "短时间显示");
                        break;
                    case 2:
                        ToastUtils.showToast(context, "1s显示", 1000);
                        break;
                    case 3:
                        ToastUtils.showToast(context, "10s显示", 10000);
                        iCount = 0;
                        break;
                    default:
                        break;
                }
                iCount++;
            }
        });
    }
}
