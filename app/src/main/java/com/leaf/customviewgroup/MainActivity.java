package com.leaf.customviewgroup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.leaf.corner.CornerConstraintLayout;

public class MainActivity extends Activity {

    private CornerConstraintLayout cornerConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cornerConstraintLayout = findViewById(R.id.corner_layout);
        findViewById(R.id.modify_bnt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cornerConstraintLayout.setCornderRadius(30, 0, 40, 0);
            }
        });
    }
}
