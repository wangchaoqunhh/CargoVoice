package com.lwb.cargovoice.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lwb.cargovoice.R;
import com.lwb.cargovoice.utils.ClickToolsUtil;

public class AddNumDialog extends DialogFragment {

    private Back mBack;
    private EditText etNum;

    private boolean isDecimals;

    public static AddNumDialog newInstance() {
        AddNumDialog f = new AddNumDialog();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.cargovoice_add_num_dialog_view, null);
        etNum = view.findViewById(R.id.et_num);
        TextView cancel = view.findViewById(R.id.tv_cancel);
        TextView ok = view.findViewById(R.id.tv_ok);

        cancel.setOnClickListener(v -> dismiss());

        ok.setOnClickListener(v -> {
            if (mBack != null) {
                mBack.onBack(etNum.getText().toString());
            }
            dismiss();
        });

        if (isDecimals) {
            //小数
            etNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            //设置只能输入两位小数
            etNum.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if (s.toString().contains(".")) {
                        if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                            s = s.toString().subSequence(0,
                                    s.toString().indexOf(".") + 3);
                            etNum.setText(s);
                            etNum.setSelection(s.length());
                        }
                    }
                    if (s.toString().trim().substring(0).equals(".")) {
                        s = "0" + s;
                        etNum.setText(s);
                        etNum.setSelection(2);
                    }

                    if (s.toString().startsWith("0")
                            && s.toString().trim().length() > 1) {
                        if (!s.toString().substring(1, 2).equals(".")) {
                            etNum.setText(s.subSequence(0, 1));
                            etNum.setSelection(1);
                            return;
                        }
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }

            });
        } else {
            //整数
            etNum.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        if (!TextUtils.isEmpty(etNum.getText())) {
            etNum.setSelection(etNum.length());
        }
        return view;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (ClickToolsUtil.INSTANCE.isFastDoubleClick()) return;
        super.show(manager, tag);
    }

    public void setDecimals(boolean decimals) {
        isDecimals = decimals;
    }

    public AddNumDialog setBack(Back back) {
        mBack = back;
        return this;
    }

    public interface Back {
        void onBack(String num);
    }
}
