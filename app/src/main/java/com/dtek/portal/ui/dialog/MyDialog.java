package com.dtek.portal.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment {

    private String mString;

    public MyDialog(String string) {
        mString = string;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getActivity() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(mString)
                    .setPositiveButton(android.R.string.ok, (dialog, id) -> { });
            return builder.create();
        }
        return null;
    }
}

