package net.bigmachini.challange.journalapp.db.controllers;

import android.content.Context;

public class BaseController {
    protected Context mContext;

    BaseController(Context context) {
        mContext = context.getApplicationContext();
    }
}
