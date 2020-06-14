package com.material.components.fragment;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;

import com.material.components.R;

public class DialogPaymentFragment extends DialogFragment {

    public CallbackResult callbackResult;

    public void setOnCallbackResult(final CallbackResult callbackResult) {
        this.callbackResult = callbackResult;
    }

    private int request_code = 0;
    private View root_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root_view = inflater.inflate(R.layout.dialog_payment, container, false);
        ((ImageButton) root_view.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        (root_view.findViewById(R.id.lyt_add_card)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPaymentClick();
            }
        });
        (root_view.findViewById(R.id.lyt_request)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPaymentClick();
            }
        });
        (root_view.findViewById(R.id.lyt_link_account)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPaymentClick();
            }
        });
        return root_view;
    }

    private void onPaymentClick() {
        if (callbackResult != null) {
            callbackResult.sendResult(request_code);
        }
        dismiss();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void setRequestCode(int request_code) {
        this.request_code = request_code;
    }

    public interface CallbackResult {
        void sendResult(int requestCode);
    }

}