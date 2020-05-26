package com.mindorks.framework.mvvm.ui.main;

import android.widget.Button;

import com.mindorks.framework.mvvm.data.model.db.Option;

public interface OptionClickListener {
    void onOptionClickListener(Button clickFrom, Button button, Option option);
}
