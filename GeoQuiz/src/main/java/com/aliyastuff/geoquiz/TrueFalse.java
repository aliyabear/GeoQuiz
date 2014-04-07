package com.aliyastuff.geoquiz;

/**
 * Created by aliya on 4/7/2014.
 */
public class TrueFalse {

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean isIsAnswerTrue() {
        return mIsAnswerTrue;
    }

    public void setIsAnswerTrue(boolean mIsAnswerTrue) {
        this.mIsAnswerTrue = mIsAnswerTrue;
    }

    int mQuestion;
    boolean mIsAnswerTrue;

    public TrueFalse(int question, boolean isAnswerTrue)
    {
        setQuestion(question);
        setIsAnswerTrue(isAnswerTrue);
    }
}
