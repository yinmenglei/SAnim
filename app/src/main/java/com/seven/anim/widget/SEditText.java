package com.seven.anim.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressLint("AppCompatCustomView")
public class SEditText extends EditText {

    // 不允许输入表情-不允许输入特出字符
    private static final String spe = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]"
            + "|[\u2600-\u27ff]|[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*"
            + "（）——+|{}【】‘；：”“’。，、？]";


    private InputFilter speFilter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern emoji = Pattern.compile(spe, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
            Matcher emojiMatcher = emoji.matcher(source);

            if (emojiMatcher.find()) {
                show();
                return "";
            }
            return null;
        }
    };

    private void show() {
        Toast.makeText(getContext(), "不支持此输入", Toast.LENGTH_SHORT).show();
    }

    public SEditText(Context context) {
        this(context, null);
    }

    public SEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public SEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        this.setFilters(new InputFilter[]{speFilter});
    }

}