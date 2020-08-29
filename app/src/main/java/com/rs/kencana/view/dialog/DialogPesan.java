package com.rs.kencana.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.rs.kencana.R;

public class DialogPesan extends DialogFragment {

    private String judulText;
    private String pesanText;

    public static final String PESAN_DIALOG_TAG = "com.rs.kencana.pesandialog";

    public static DialogFragment newInstance(String judulText, String pesanText) {
        DialogPesan dialogFragment = new DialogPesan();
        dialogFragment.judulText = judulText;
        dialogFragment.pesanText = pesanText;
        return dialogFragment;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(judulText);
        builder.setMessage(pesanText);
        builder.setIcon(R.drawable.logo);
        builder.setPositiveButton("Ok Siip", null);
        return builder.create();
    }
}
