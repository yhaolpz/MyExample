package com.meitu.example.autosplittextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextview;
    private BreakTextView mTextview2;

    String text =
            "可能由于时间问题，都没有很好解决我的问题。将textview中的字符全角化没有效果，去除特殊字符或将所有中文标号替换为英文标号。这个有点效果，但是产品经理说文案不符合标准。改源代码担心出问题，影响其他的应用。自定义TextView时，canvas.setViewport()这个方法的api被删了。然后各种百度查资料，很多都是转过来转过去。然并卵。后面找了好久才找到一个靠谱的。完美的解决了我的问题。";
    String text2 =
            "dadasjdasdasofasfasfasfasfafasfasfasfasfasfsaffsafasfsafsafasfasfasfsa啊啊啊啊";
    String text3 =
            "1111111111111111111111111111111111111111111111111111111111111111111111啊啊啊啊";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextview = findViewById(R.id.textview);
        mTextview2 = findViewById(R.id.textview2);
    }

    public void click(View view) {
        mTextview.setText("");
        mTextview.setText(text3);
        mTextview2.setText("");
        mTextview2.setText(text3);
        mTextview2.setSplitText();
    }
}
