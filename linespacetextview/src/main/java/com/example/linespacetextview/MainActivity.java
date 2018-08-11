package com.example.linespacetextview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    private TextView mTv;

    private EditText mEt1;
    private EditText mEt2;
    private EditText mEt3;


    /**
     * 行间距offset
     */
    int lineSpacingExtraDp = 0;
    /**
     * 段落间距offset
     */
    int paragraphSpacingDp = 3;


    String text = "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊\n" +
            "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊\n" +
            "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊\n" +
            "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);

        mEt1 = findViewById(R.id.et1);
        mEt2 = findViewById(R.id.et2);
        mEt3 = findViewById(R.id.et3);


        mEt1.setText("15");
        mEt2.setText("0");
        mEt3.setText("3");

//        mTv.post(new Runnable() {
//            @Override
//            public void run() {
//                change();
//            }
//        });

        change();


    }


    void change() {
        mTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, getDp(mEt1));
        mTv.setLineSpacing(dip2px(getDp(mEt2)), 1);
        mTv.setText(insertSpaceSpan(mTv, text, dip2px(getDp(mEt3))));
    }


    public void click(View view) {
        change();
    }


    private CharSequence insertSpaceSpan(TextView textView, String content, int paragraphSpacingOffset) {
        if (!content.contains("\n")) {
            return content;
        }
        content = content.replace("\n", "\n\r");
        int previousIndex = content.indexOf("\n\r");
        List<Integer> nextParagraphBeginIndexes = new ArrayList<>();
        nextParagraphBeginIndexes.add(previousIndex);
        while (previousIndex != -1) {
            previousIndex = content.indexOf("\n\r", previousIndex + 2);
            if (previousIndex != -1) {
                nextParagraphBeginIndexes.add(previousIndex);
            }
        }
        //获取行高（包含文字高度和行距）
        float lineHeight = textView.getLineHeight();
        //把\r替换成透明长方形（宽:1px，高：字高+段距）
        SpannableString spanString = new SpannableString(content);
        Drawable d = ContextCompat.getDrawable(this, R.drawable.paragraph_space);
        d.setBounds(0, 0, 0, (int) (lineHeight + paragraphSpacingOffset));
        for (int index : nextParagraphBeginIndexes) {
            spanString.setSpan(new ImageSpan(d), index + 1, index + 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return spanString;
    }


    public int dip2px(float dipValue) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) (dipValue * dm.density + 0.5f);
    }


    private int getDp(EditText editText) {
        String t = editText.getText().toString();
        return Integer.parseInt(t);
    }

}
