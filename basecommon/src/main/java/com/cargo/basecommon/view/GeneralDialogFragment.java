package com.cargo.basecommon.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.cargo.basecommon.R;

import java.util.Objects;


/**
 * 提示弹窗
 *
 * @author Sundf
 */
public class GeneralDialogFragment extends DialogFragment{

    private static final String KEY_IS_INPUT = "isInput";
    private static final String NORMAL_TITLE = "title";
    private static final String NORMAL_TEXT = "text";
    private String mTitle;
    private EditText mEditTitle;
    private OnCancelClickListener cancelClickListener;
    private OnOkClickListener okClickListener;
    private String inputText = "";
    private boolean isInput;
    private String mText;


    public static GeneralDialogFragment newInstance(boolean isInput,String title ,String text) {
        GeneralDialogFragment mDialogFragment = new GeneralDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_IS_INPUT, isInput);
        bundle.putString(NORMAL_TITLE,title);
        bundle.putString(NORMAL_TEXT,text);
        mDialogFragment.setArguments(bundle);
        return mDialogFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isInput = getArguments().getBoolean(KEY_IS_INPUT);
            mTitle = getArguments().getString(NORMAL_TITLE);
            mText = getArguments().getString(NORMAL_TEXT);

        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        return setDialogLayout(inflater, container);
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams windowParams = window.getAttributes();
            windowParams.dimAmount = 0.6f;
            window.setAttributes(windowParams);
        }
    }

    /**
     * 设置dialog布局
     *
     * @param inflater  过滤器
     * @param container 总布局
     * @return view
     */
    private View setDialogLayout(LayoutInflater inflater, ViewGroup container) {
        View view =null;
        if (isInput){
            view = inflater.inflate(R.layout.dialog_edit, container, false);
            mEditTitle = view.findViewById(R.id.et_dialog_input_text);
            mEditTitle.setText(inputText);
            mEditTitle.setSelection(inputText.length());//将光标移至文字末尾
        }else{
            view = inflater.inflate(R.layout.dialog_normal, container, false);
            TextView tvTitle = view.findViewById(R.id.tv_title);
            TextView tvText = view.findViewById(R.id.tv_text);
            tvText.setText(mText);
            tvTitle.setText(mTitle);
        }
        TextView tvOk = view.findViewById(R.id.tv_dialog_ok);
        TextView tvCancel = view.findViewById(R.id.tv_dialog_cancel);
        tvCancel.setOnClickListener(view1 -> cancelClickListener.clickCancel());
        tvOk.setOnClickListener(view12 -> {
            if (isInput){
                okClickListener.clickOk(mEditTitle.getText().toString());
            }else{
                okClickListener.clickOk("");
            }
        });
        return view;
    }





    @Override
    public void dismiss() {
        if (getDialog() != null) {
            View view = getDialog().getCurrentFocus();
            if (view instanceof TextView) {
                InputMethodManager mInputMethodManager = (InputMethodManager) Objects.requireNonNull(getContext()).getSystemService(Context.INPUT_METHOD_SERVICE);
                if (mInputMethodManager != null) {
                    mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                }
            }
        }
        super.dismiss();
    }


    public void setOnCancelClickListener(OnCancelClickListener cancelClickListener){
        this.cancelClickListener = cancelClickListener;
    }

    public void setEditTitle(String text) {
        this.inputText = text;
    }

    public interface OnCancelClickListener {
        /**
         * 点击cancel按钮回调方法
         */
        void clickCancel();
    }

    public void setOnOkClickListener(OnOkClickListener okClickListener){
        this.okClickListener = okClickListener;
    }
    public interface OnOkClickListener {
        /**
         * 点击ok按钮回调方法
         */
        void clickOk(String input);
    }

    public EditText getEditTitle() {
        return mEditTitle;
    }


}
