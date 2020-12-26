package com.cargo.login.module.mvp.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cargo.basecommon.utils.Dp2pxUtils;
import com.cargo.basecommon.utils.languageUtils.LanguageType;
import com.cargo.login.R;
import com.cargo.login.module.mvp.ui.activity.LoginActivity;

import java.util.Locale;

import static com.cargo.basecommon.utils.languageUtils.LanguageUtil.changeLanguage;

public class LanguageDialogFragment extends DialogFragment {

    private OnLanguageChangeListener mListener;

    public static LanguageDialogFragment newInstance() {
        LanguageDialogFragment f = new LanguageDialogFragment();
        return f;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_language, null);
        RelativeLayout rlChina = view.findViewById(R.id.rl_china);
        RelativeLayout rlEnglish = view.findViewById(R.id.rl_english);
        rlChina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage(getActivity(), LanguageType.CHINESE.getLanguage());
                getActivity().recreate();
              /*  if (getActivity() instanceof LoginActivity){
                    ((LoginActivity)getActivity()).refesh();
                }*/
                mListener.onLanguageChange(LanguageType.CHINESE.getLanguage());
                dismiss();
            }
        });
        rlEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage(getActivity(), LanguageType.ENGLISH.getLanguage());
                getActivity().recreate();
                /*if (getActivity() instanceof LoginActivity){
                    ((LoginActivity)getActivity()).refesh();
                }*/
                mListener.onLanguageChange(LanguageType.ENGLISH.getLanguage());
                dismiss();
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(Dp2pxUtils.dp2px(getActivity(), 312), Dp2pxUtils.dp2px(getContext(), 113));
    }

    public interface OnLanguageChangeListener {
        void onLanguageChange(String simplifiedChinese);
    }

    public void setOnLanguageChangeListener(OnLanguageChangeListener listener) {
        mListener = listener;
    };

}
