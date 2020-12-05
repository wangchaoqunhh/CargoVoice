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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lwb.cargovoice.R;
import com.lwb.cargovoice.adapter.FreightDetailsBottomDialogListAdapter;
import com.lwb.cargovoice.utils.ClickToolsUtil;

import java.util.List;

import static com.lwb.cargovoice.R2.id.design_bottom_sheet;

public class BottomFreightDetailsBottomDialog extends BottomSheetDialogFragment {
    /**
     *
     */
    private int topOffset = 0;
    private BottomSheetBehavior<FrameLayout> behavior;

    private TextView dialogTitle;
    private RecyclerView rv;

    private List list;
    private int type;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getContext() == null) {
            return super.onCreateDialog(savedInstanceState);
        } else {
            return new BottomSheetDialog(getContext(), R.style.TransparentBottomSheetStyle);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.cargovoice_freight_details_bottom_dialog_view, null);
        dialogTitle = mainView.findViewById(R.id.tv_dialog_title);
        rv = mainView.findViewById(R.id.rv);
        initAdapter(list, type);
        return mainView;
    }


    /**
     * @param type 1 运输信息 2 里程碑 3 货件/物品  4 订单  5 集装箱 6 参考号 7 备注
     */
    private void initAdapter(List list, int type) {
        String title = "";
        switch (type) {
            case 1:
                title = "运输信息";
                break;
            case 2:
                title = "里程碑";
                break;
            case 3:
                title = "物品";
                break;
            case 4:
                title = "订单";
                break;
            case 5:
                title = "集装箱";
                break;
            case 6:
                title = "参考号";
                break;
            case 7:
                title = "备注";
                break;
        }
        this.dialogTitle.setText(title);
        FreightDetailsBottomDialogListAdapter freightDetailsBottomDialogListAdapter
                = new FreightDetailsBottomDialogListAdapter(list, type);
        rv.setAdapter(freightDetailsBottomDialogListAdapter);
    }


    public BottomFreightDetailsBottomDialog setListAndType(List list, int type) {
        this.list = list;
        this.type = type;
        return this;
    }


    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        FrameLayout bottomSheet = dialog.getDelegate().findViewById(design_bottom_sheet);
        if (bottomSheet != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams();
            behavior = BottomSheetBehavior.from(bottomSheet);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
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

//    private static BottomFreightDetailsBottomDialog dialog;
//
//    public static BottomFreightDetailsBottomDialog getInstant() {
//        if (dialog == null) {
//            synchronized (BottomFreightDetailsBottomDialog.class) {
//                if (dialog == null) {
//                    dialog = new BottomFreightDetailsBottomDialog();
//                }
//            }
//        }
//        return dialog;
//    }
}
