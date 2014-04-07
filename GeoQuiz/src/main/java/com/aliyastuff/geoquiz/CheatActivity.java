package com.aliyastuff.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

    public static final String HAS_CHEATED = "com.aliyastuff.geoquiz.has_cheated";
    public static final String ANSWER_TRUE = "com.aliyastuff.geoquiz.answer_true";

    Button mShowAnswerButton;

    TextView mAnswerText;

    boolean mIsAnswerTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        setAnswerShown(false);

        mIsAnswerTrue = getIntent().getBooleanExtra(ANSWER_TRUE, false);

        mShowAnswerButton = (Button)findViewById(R.id.show_answer_button);
        mAnswerText = (TextView)findViewById(R.id.answer_text);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show answer
                updateAnswerText();
            }
        });
    }
    private void setAnswerShown(boolean isAnswerShown)
    {
        Intent data = new Intent();
        data.putExtra(HAS_CHEATED, isAnswerShown);
        setResult(RESULT_OK, data);
    }
    private void updateAnswerText()
    {
        if(mIsAnswerTrue)
            mAnswerText.setText(R.string.true_text);
        else
            mAnswerText.setText(R.string.false_text);
        setAnswerShown(true);

    }
}
