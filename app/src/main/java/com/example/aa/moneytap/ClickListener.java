package com.example.aa.moneytap;

import android.view.View;

/**
 * Created by aa on 23-06-2018.
 */

interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
