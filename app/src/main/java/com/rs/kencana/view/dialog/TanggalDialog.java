package com.rs.kencana.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.rs.kencana.R;
import com.rs.kencana.event.TanggalEvent;
import com.rs.kencana.util.MyLogger;
import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

public class TanggalDialog extends DialogFragment implements Dialog.OnClickListener {

    private DatePicker datePicker;
    private int posisi;


    public static final String DIALOG_TAG = "com.rs.kencana.tanggaldialog";
    private static final String EXTRA_POSISI = "com.rs.kencana.tannggaldialog.extra.posisi";

    public static DialogFragment newInstance(int posisi) {
        Bundle arg = new Bundle();
        arg.putInt(EXTRA_POSISI, posisi);
        TanggalDialog dialogFragment = new TanggalDialog();
        dialogFragment.setArguments(arg);
        return dialogFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        posisi = getArguments().getInt(EXTRA_POSISI);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_tanggal, null);
        datePicker = view.findViewById(R.id.dialog_tanggal_datepicker);
        builder.setView(view);
        builder.setPositiveButton("OK", this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == Dialog.BUTTON_POSITIVE) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
            EventBus.getDefault().post(new TanggalEvent(posisi, calendar.getTime()));
        }
    }
}
