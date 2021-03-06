package com.mancng.shoppinglist.dialog;


import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import com.mancng.shoppinglist.infrastructure.ShoppingListApplication;
import com.mancng.shoppinglist.infrastructure.Utils;
import com.squareup.otto.Bus;

public class BaseDialog extends DialogFragment {

    protected ShoppingListApplication application;
    protected Bus bus;
    protected String userEmail, userName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (ShoppingListApplication) getActivity().getApplication();
        bus = application.getBus();
        bus.register(this);
        //This allow us to get the userEmail and userName for all dialog fragments.
        userEmail = getActivity().getSharedPreferences(Utils.MY_PREFERENCE, Context.MODE_PRIVATE).getString(Utils.EMAIL,"");
        userName = getActivity().getSharedPreferences(Utils.MY_PREFERENCE, Context.MODE_PRIVATE).getString(Utils.USERNAME,"");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}