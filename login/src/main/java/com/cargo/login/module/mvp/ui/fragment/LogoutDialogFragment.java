package com.cargo.login.module.mvp.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cargo.basecommon.utils.AirToast;
import com.cargo.basecommon.utils.Dp2pxUtils;
import com.cargo.basecommon.utils.SPUtils;
import com.cargo.login.R;
import com.cargo.login.module.mvp.ui.activity.LoginActivity;

import java.lang.reflect.Field;

public class LogoutDialogFragment extends DialogFragment {

    public static LogoutDialogFragment newInstance() {
        LogoutDialogFragment f = new LogoutDialogFragment();
        return f;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_logout, null);
        view.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.put(getActivity(),"token","");
                dismiss();
            }
        });
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(Dp2pxUtils.dp2px(getActivity(), 312), Dp2pxUtils.dp2px(getContext(), 144));
    }


}
