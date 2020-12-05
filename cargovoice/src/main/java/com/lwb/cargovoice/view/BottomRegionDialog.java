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
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lwb.cargovoice.R;
import com.lwb.cargovoice.adapter.AddHarvestInfoAdapter;
import com.lwb.cargovoice.module.mvp.entity.response.AddHarvestInfoResponse;
import com.lwb.cargovoice.utils.ClickToolsUtil;

import java.util.List;

import static com.lwb.cargovoice.R2.id.design_bottom_sheet;

public class BottomRegionDialog extends BottomSheetDialogFragment {
    /**
     *
     */
    private int topOffset = 0;
    private BottomSheetBehavior<FrameLayout> behavior;
    private List<AddHarvestInfoResponse> list;
    private BackSelectData mBackSelectData;

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
        View mainView = inflater.inflate(R.layout.cargovoice_bottom_region_dialog, null);
        LinearLayout llAddLocation = mainView.findViewById(R.id.ll_add_location);
        RecyclerView recyclerView = mainView.findViewById(R.id.recyclerview);

        AddHarvestInfoAdapter addHarvestInfoAdapter = new AddHarvestInfoAdapter(list);
        recyclerView.setAdapter(addHarvestInfoAdapter);

        addHarvestInfoAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (mBackSelectData != null) {
                mBackSelectData.onBackSelectData(list.get(position));
                dismiss();
            }
        });

        llAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBackSelectData != null) {
                    mBackSelectData.onAddLocation();
                    dismiss();
                }
            }
        });

        return mainView;
    }

    public BottomRegionDialog setList(List<AddHarvestInfoResponse> list) {
        this.list = list;
        return this;
    }

    public BottomRegionDialog setBackSelectData(BackSelectData mBackSelectData) {
        this.mBackSelectData = mBackSelectData;
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
                wm.getDefaultDisplay().getSize(point);
                height = point.y - topOffset;
            }
        }
        return height;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    private static BottomRegionDialog dialog;

    public static BottomRegionDialog getInstant() {
        if (dialog == null) {
            synchronized (BottomRegionDialog.class) {
                if (dialog == null) {
                    dialog = new BottomRegionDialog();
                }
            }
        }
        return dialog;
    }

    public interface BackSelectData {
        void onBackSelectData(AddHarvestInfoResponse backBean);

        void onAddLocation();
    }

}
