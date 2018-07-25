package com.example.linespacetextview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    private TextView mTv;

    int lineSpacingExtra = 5;
    int paragraphSpacing = 3;

    String text = "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊\n" +
            "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊\n" +
            "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊\n" +
            "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.tv);


        mTv.setText(insertSpaceSpan(mTv, text));
    }


    private CharSequence insertSpaceSpan(TextView textView, String content) {
        if (!content.contains("\n")) {
            return content;
        }
        content = content.replace("\n", "\n\r");

        int previousIndex = content.indexOf("\n\r");
        //记录每个段落开始的index，第一段没有，从第二段开始
        List<Integer> nextParagraphBeginIndexes = new ArrayList<>();
        nextParagraphBeginIndexes.add(previousIndex);
        while (previousIndex != -1) {
            int nextIndex = content.indexOf("\n\r", previousIndex + 2);
            previousIndex = nextIndex;
            if (previousIndex != -1) {
                nextParagraphBeginIndexes.add(previousIndex);
            }
        }
        //获取行高（包含文字高度和行距）
        float lineHeight = textView.getLineHeight();

        //把\r替换成透明长方形（宽:1px，高：字高+段距）
        SpannableString spanString = new SpannableString(content);
        Drawable d = ContextCompat.getDrawable(this, R.drawable.paragraph_space);
        float density = getResources().getDisplayMetrics().density;
        //int强转部分为：行高 - 行距 + 段距
        int bottom = (int) ((lineHeight - lineSpacingExtra * density) / 1.2 + (paragraphSpacing - lineSpacingExtra) * density);
        d.setBounds(0, 0,
                1, 70);
        for (int index : nextParagraphBeginIndexes) {
            // \r在String中占一个index
            Log.d(TAG, "insertSpaceSpan: index=" + index);
            spanString.setSpan(new ImageSpan(d), index + 2, index + 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return spanString;
    }


    public void click(View view) {
        mTv.setText(insertSpaceSpan(mTv, text));
//        SpannableString spanString = new SpannableString(text);
//        Drawable d = ContextCompat.getDrawable(this, R.drawable.paragraph_space);
//        d.setBounds(0, 0,
//                5, 100);
//        spanString.setSpan(new ImageSpan(d), 2, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        mTv.setText(spanString);

    }
}
