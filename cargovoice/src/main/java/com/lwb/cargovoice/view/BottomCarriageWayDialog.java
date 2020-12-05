package com.lwb.cargovoice.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.cargo.basecommon.base.BaseApplication;
import com.cargo.basecommon.bean.EnquiryAddGoalRequest;
import com.lwb.cargovoice.R;
import com.lwb.cargovoice.utils.ClickToolsUtil;

import static com.lwb.cargovoice.R2.id.design_bottom_sheet;

public class BottomCarriageWayDialog extends BottomSheetDialogFragment {
    /**
     *
     */
    private int topOffset = 0;
    private BottomSheetBehavior<FrameLayout> behavior;

    private static BottomCarriageWayDialog dialog;
    private Back mBack;

    //1海 2空
    private int transportMode;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getContext() == null) {
            return super.onCreateDialog(savedInstanceState);
        } else {
            return new BottomSheetDialog(getContext(), R.style.TransparentBottomSheetStyleDialog);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EnquiryAddGoalRequest request = ((BaseApplication) (getActivity().getApplication())).getRequest();
        if (request.getBusinessInfo().getTransportModeCode() == "SEA") {
            transportMode = 1;
        } else if (request.getBusinessInfo().getTransportModeCode() == "AIR") {
            transportMode = 2;
        }
        View mainView = inflater.inflate(R.layout.cargovoice_bottom_carriage_way_dialog, null);
        //空运
        LinearLayout ll_airborne = mainView.findViewById(R.id.ll_airborne);
        //海运
        LinearLayout ll_aquage = mainView.findViewById(R.id.ll_aquage);

        //初始化时 看应该选中那个
        if (transportMode == 1) {
            ll_aquage.setSelected(true);
            ll_airborne.setSelected(false);
        } else if (transportMode == 2) {
            ll_aquage.setSelected(false);
            ll_airborne.setSelected(true);
        }

        ll_airborne.setOnClickListener(v -> {
            if (mBack != null) {
                ll_airborne.setSelected(true);
                ll_aquage.setSelected(false);
                transportMode = 2;
                mBack.onBack("2");
                dismiss();
            }
        });

        ll_aquage.setOnClickListener(v -> {
            if (mBack != null) {
                ll_airborne.setSelected(false);
                ll_aquage.setSelected(true);
                transportMode = 1;
                mBack.onBack("1");
                dismiss();
            }
        });
        return mainView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        FrameLayout bottomSheet = dialog.getDelegate().findViewById(design_bottom_sheet);
        if (bottomSheet != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams();
            layoutParams.leftMargin = dp2px(getContext(), 8F);
            layoutParams.rightMargin = dp2px(getContext(), 8f);
        }
    }

    private int dp2px(Context context, Float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return Integer.parseInt(String.valueOf(dipValue * scale + 0.5F));
    }

    /**
     * @return height
     */
    private int getHeight() {
        int height = 1920;
        if (getContext() != null) {
            WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            Point point = new Point();
            if (wm != null) {
                wm.getDefaultDisplay().getSize(point);
                height = point.y - topOffset;
            }
        }
        return height;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (ClickToolsUtil.INSTANCE.isFastDoubleClick()) return;
        super.show(manager, tag);
    }

    public BottomCarriageWayDialog setBack(Back mBack) {
        this.mBack = mBack;
        return this;
    }

    @SuppressLint("ValidFragment")
    private BottomCarriageWayDialog() {

    }

    public static BottomCarriageWayDialog getInstant() {
        if (dialog == null) {
            synchronized (BottomCarriageWayDialog.class) {
                if (dialog == null) {
                    dialog = new BottomCarriageWayDialog();
                }
            }
        }
        return dialog;
    }

    public interface Back {
        // 1 海 2空
        void onBack(String haiOrKong);
    }
}
