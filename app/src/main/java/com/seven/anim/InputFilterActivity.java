package com.seven.anim;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.seven.anim.widget.SEditText;

public class InputFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_input);

        initViewAction();
    }

    private void initViewAction() {
        SEditText editInput = findViewById(R.id.edit_input);
        editInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                String replace = s1.replace(" ", "");
                Log.i("", "replace:" + replace);
            }
        });
    }
}
