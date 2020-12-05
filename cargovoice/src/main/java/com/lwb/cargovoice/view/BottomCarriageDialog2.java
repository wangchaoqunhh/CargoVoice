package com.lwb.cargovoice.view;

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

import com.lwb.cargovoice.R;
import com.lwb.cargovoice.utils.ClickToolsUtil;

import static com.lwb.cargovoice.R2.id.design_bottom_sheet;

public class BottomCarriageDialog2 extends BottomSheetDialogFragment {
    /**
     *
     */
    private int topOffset = 0;
    private BottomSheetBehavior<FrameLayout> behavior;

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
        View mainView = inflater.inflate(R.layout.cargovoice_bottom_carriage_dialog, null);

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
            layoutParams.leftMargin = dp2px(getContext(), 8f);
            layoutParams.rightMargin = dp2px(getContext(), 8f);
//            behavior = BottomSheetBehavior.from(bottomSheet)
//            behavior?.setState(BottomSheetBehavior.STATE_EXPANDED)
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
                // ʹ��Point�Ѿ���ȥ��״̬���߶�
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

    private static BottomCarriageDialog2 bottomCarriageDialog2;

    public static BottomCarriageDialog2 getInstant() {
        if (bottomCarriageDialog2 == null) {
            synchronized (BottomCarriageDialog2.class) {
                if (bottomCarriageDialog2 == null) {
                    bottomCarriageDialog2 = new BottomCarriageDialog2();
                }
            }
        }
        return bottomCarriageDialog2;
    }
}
