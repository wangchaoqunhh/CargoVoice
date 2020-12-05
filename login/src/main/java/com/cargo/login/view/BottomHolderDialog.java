package com.cargo.login.view;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.cargo.login.R;
import com.cargo.login.adapter.HolderListAdapter;
import com.cargo.login.module.mvp.entity.request.HolderBean;
import com.cargo.login.utils.ClickToolsUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import static com.cargo.login.R2.id.design_bottom_sheet;


public class BottomHolderDialog extends BottomSheetDialogFragment {
    /**
     *
     */
    private int topOffset = 0;
    private BottomSheetBehavior<FrameLayout> behavior;
    private static List<HolderBean> mHolderList;
    private HolderListAdapter adapter;
    private OnHolderClickListener mListener;

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
        View mainView = inflater.inflate(R.layout.login_bottom_holder_dialog, null);
        RecyclerView rvHolder = mainView.findViewById(R.id.rv_holder);
        adapter = new HolderListAdapter(R.layout.login_item_bottom_holder,mHolderList);
        rvHolder.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHolder.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mListener != null){
                    mListener.onHolderClick(mHolderList.get(position),position);
                }
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

    private static BottomHolderDialog bottomHolderDialog;

    public static BottomHolderDialog getInstant(List<HolderBean> holderList) {
        mHolderList = holderList;
        if (bottomHolderDialog == null) {
            synchronized (BottomHolderDialog.class) {
                if (bottomHolderDialog == null) {
                    bottomHolderDialog = new BottomHolderDialog();
                }
            }
        }
        return bottomHolderDialog;
    }

    public interface OnHolderClickListener{
        void onHolderClick(HolderBean holderBean,int position);
    }
    public void setOnHolderClickListener(OnHolderClickListener listener){
        mListener = listener;
    };


}
