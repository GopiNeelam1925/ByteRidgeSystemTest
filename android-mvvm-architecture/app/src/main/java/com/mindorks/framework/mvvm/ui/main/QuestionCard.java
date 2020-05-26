/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.mindorks.framework.mvvm.ui.main;

import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.mindorks.framework.mvvm.R;
import com.mindorks.framework.mvvm.data.model.db.Option;
import com.mindorks.framework.mvvm.data.model.others.QuestionCardData;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.util.List;

/**
 * Created by amitshekhar on 08/07/17.
 */

@NonReusable
@Layout(R.layout.card_layout)
public class QuestionCard {

    @View(R.id.btn_option_1)
    private Button mOption1Button;

    @View(R.id.btn_option_2)
    private Button mOption2Button;

    @View(R.id.btn_option_3)
    private Button mOption3Button;

    @View(R.id.btn_option_4)
    private Button mOption4Button;

    @View(R.id.iv_pic)
    private ANImageView mPicImageView;

    private QuestionCardData mQuestionCardData;
    private OptionClickListener optionClickListener;

    @View(R.id.tv_question_txt)
    private TextView mQuestionTextView;

    public QuestionCard(QuestionCardData questionCardData, OptionClickListener optionClickListener) {
        mQuestionCardData = questionCardData;
        this.optionClickListener = optionClickListener;
    }

    @Click(R.id.btn_option_1)
    public void onOption1Click() {
        showCorrectOptions(mOption1Button);
    }

    @Click(R.id.btn_option_2)
    public void onOption2Click() {
        showCorrectOptions(mOption2Button);
    }

    @Click(R.id.btn_option_3)
    public void onOption3Click() {
        showCorrectOptions(mOption3Button);
    }

    @Click(R.id.btn_option_4)
    public void onOption4Click() {
        showCorrectOptions(mOption4Button);
    }

    @Resolve
    private void onResolved() {
        mQuestionTextView.setText(mQuestionCardData.question.questionText);
        if (mQuestionCardData.mShowCorrectOptions) {
            showCorrectOptions(null);
        }

        for (int i = 0; i < mQuestionCardData.options.size(); i++) {
            Button button = null;
            switch (i) {
                case 0:
                    button = mOption1Button;
                    break;
                case 1:
                    button = mOption2Button;
                    break;
                case 2:
                    button = mOption3Button;
                    break;
                case 3:
                    button = mOption4Button;
                    button.setVisibility(android.view.View.VISIBLE);
                    break;
            }

            if (button != null) {
                button.setText(mQuestionCardData.options.get(i).optionText);
            }

            if (mQuestionCardData.question.imgUrl != null) {
                mPicImageView.setImageUrl(mQuestionCardData.question.imgUrl);
            }
        }
    }

    private void showCorrectOptions(Button clickFrom) {
        mQuestionCardData.mShowCorrectOptions = true;
        for (int i = 0; i < mQuestionCardData.options.size(); i++) {
            Option option = mQuestionCardData.options.get(i);
            Button button = null;
            switch (i) {
                case 0:
                    button = mOption1Button;

                    break;
                case 1:
                    button = mOption2Button;
                    break;
                case 2:
                    button = mOption3Button;
                    break;
                case 3:
                    button = mOption4Button;
                    break;
            }
            if (button != null) {
                if (option.isCorrect) {
                    button.setBackgroundColor(Color.GREEN);
                } else {
                    button.setBackgroundColor(Color.RED);
                }
            }
            if (clickFrom != null) {
                optionClickListener.onOptionClickListener(clickFrom,button,option);
            }
        }
    }

//    private void updateDbWithSelectedOption(Button clickFrom, Button button, int i, Option option) {
//        if (button == clickFrom) {
//            Exam localExam = this.exam;
//            if (option.isCorrect) {
//                localExam.correct = localExam.correct + 1;
//            } else {
//                localExam.wrong = localExam.wrong + 1;
//            }
//            AsyncTask.execute(new Runnable() {
//                @Override
//                public void run() {
//                    dataManager.updateCurrentExam(localExam);
//                }
//            });
//        }
//    }

    private boolean isMoreThanOptionCorrectForThisQuestion(List<Option> options) {
        int count = 0;
        for (Option option : options) {
            if (option.isCorrect) {
                count++;
            }
        }
        return count != 1;
    }
}
