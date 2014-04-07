package com.aliyastuff.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class QuizActivity extends Activity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_CURRENT_INDEX ="com.aliyastuff.geoquiz.current_index";

    Button mTrueButton;
    Button mFalseButton;
    Button mNextButton;
    Button mCheatButton;

    TextView mQuestionText;

    int mCurrentIndex;

    boolean mCheated;

    private TrueFalse[] mQuestionsBank = new TrueFalse[]{
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_oceans, true)
    };

    private void initializeItemsFromSavedInstance(Bundle savedInstanceState){
        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX, 0);
            mCheated = savedInstanceState.getBoolean(CheatActivity.HAS_CHEATED, false);
        }
        else {
            mCurrentIndex = 0;
            mCheated = false;
        }

        mQuestionText.setText(mQuestionsBank[mCurrentIndex].getQuestion());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(..)");
        setContentView(R.layout.activity_quiz);
        try {
            //initialize variables
            mTrueButton = (Button) findViewById(R.id.true_button);
            mFalseButton = (Button) findViewById(R.id.false_button);
            mNextButton = (Button) findViewById(R.id.next_button);
            mCheatButton = (Button) findViewById(R.id.cheat_button);

            mQuestionText = (TextView) findViewById(R.id.question_text);

            initializeItemsFromSavedInstance(savedInstanceState);

            // initialize onClick listeners
            mTrueButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    checkAnswer(true);
                }
            });
            mFalseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(false);
                }
            });
            mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    updateQuestion();
                }
            });
            mCheatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                    intent.putExtra(CheatActivity.ANSWER_TRUE, mQuestionsBank[mCurrentIndex].isIsAnswerTrue());
                    startActivityForResult(intent, 0);
                }
            });

        }
        catch (Exception e) {
            Log.d(TAG, "onCreate(..) - Exception: ", e);
        }
    }
    private void updateQuestion() {
        mCheated = false;
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
        //Log.d(TAG, "updateQuestion() index = " + index + ", mQuestionsBank.length = " + mQuestionsBank.length);
        mQuestionText.setText(mQuestionsBank[mCurrentIndex].getQuestion());
    }
    private void checkAnswer(boolean isTruePressed){
        int toastMessageResult = 0;

        boolean isAnswerTrue = mQuestionsBank[mCurrentIndex].isIsAnswerTrue();

        if(mCheated)
            toastMessageResult = R.string.judgement_text;
        else if ((isAnswerTrue && isTruePressed) || (!isAnswerTrue && !isTruePressed))
            toastMessageResult = R.string.correct_text;
        else
            toastMessageResult = R.string.incorrect_text;

        Toast.makeText(QuizActivity.this, toastMessageResult, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_CURRENT_INDEX, mCurrentIndex);
        savedInstanceState.putBoolean(CheatActivity.HAS_CHEATED, mCheated);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(data != null)
        {
            mCheated = data.getBooleanExtra(CheatActivity.HAS_CHEATED, false);
        }
    }
}