package com.lwb.cargovoice.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.cargo.basecommon.base.BaseApplication;
import com.cargo.basecommon.bean.EnquiryAddGoalRequest;
import com.cargo.basecommon.bean.GsonBean;
import com.cargo.basecommon.utils.LocalJsonResolutionUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lwb.cargovoice.R;
import com.lwb.cargovoice.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectDictionaryWindow extends PopupWindow {

    private Context mContext;
    private EnquiryAddGoalRequest mRequest;
    private String fileName;
    private List<GsonBean> jsonListBean;

    public SelectDictionaryWindow(Context context, String fileName, OnItemBack onItemBack) {
        super(context);
        this.mContext = context;
        this.mRequest = ((BaseApplication) ((Activity) mContext).getApplication()).getRequest();
        this.fileName = fileName;
        this.mOnItemBack = onItemBack;
        init();
    }

    //获取屏幕高
    public int getHeightPixels() {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int heightPixels = metrics.heightPixels;
        int widthPixels = metrics.widthPixels;
        return heightPixels;
    }


    private void init() {
        View mainView = LayoutInflater.from(mContext).inflate(R.layout.cargovoice_select_dictionary_view, null);
        setHeight(getHeightPixels() * 7 / 10);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(mainView);
        RecyclerView recyclerView = mainView.findViewById(R.id.recyclerview);
        jsonListBean = LocalJsonResolutionUtils.getJsonListBean(mContext, fileName);

        if (fileName.equals("containerType（箱型）.json")) {
            List<GsonBean> jsonList = new ArrayList<>();
            for (GsonBean gsonBean : jsonListBean) {
                if (mRequest != null && gsonBean.getCategoryCode().equals(mRequest.getBusinessInfo().getTransportModeCode())) {
                    jsonList.add(gsonBean);
                }
            }
            jsonListBean = jsonList;
        }

        Adapter adapter = new Adapter(jsonListBean, fileName);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((adapter1, view, position) -> {
            GsonBean gsonBean = jsonListBean.get(position);
            if (mOnItemBack != null) {
                mOnItemBack.onItemBack(gsonBean);
                dismiss();
            }
        });

        setFocusable(true);
        setOutsideTouchable(false);
        setBackgroundDrawable(mContext.getDrawable(R.drawable.cargovoice_shape_r8dp_c128));
        setElevation(ScreenUtil.dp2px(mContext, 8f));
    }

    public void show() {
        showAtLocation(((Activity) mContext).getWindow().getDecorView(),
                Gravity.RIGHT | Gravity.CENTER_VERTICAL,
                ScreenUtil.dp2px(mContext, 20F), 0);

//        if (!isShowing()) {
//            int[] location = new int[2];
//            view.getLocationOnScreen(location);
//            int y = location[1];
//            showAsDropDown(view, Gravity.RIGHT, Gravity.CENTER);
//        }
    }

    public static class Adapter extends BaseQuickAdapter<GsonBean, BaseViewHolder> {
        private String fileName;

        public Adapter(@Nullable List<GsonBean> data, String fileName) {
            super(R.layout.cargovoice_item_select_dictionary_view, data);
            this.fileName = fileName;
        }

        @Override
        protected void convert(BaseViewHolder helper, GsonBean item) {
            if ("incoTerm（贸易条款）.json".equals(fileName)) {
                helper.setText(R.id.tv_item, item.getCode());
            } else {
                helper.setText(R.id.tv_item, item.getNameCn());
            }
        }
    }

    private OnItemBack mOnItemBack;

    public interface OnItemBack {
        void onItemBack(GsonBean gsonBean);
    }
}
