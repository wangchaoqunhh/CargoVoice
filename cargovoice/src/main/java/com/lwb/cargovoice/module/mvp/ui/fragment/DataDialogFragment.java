package com.lwb.cargovoice.module.mvp.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cargo.basecommon.utils.Dp2pxUtils;
import com.lwb.cargovoice.R;
import com.lwb.cargovoice.adapter.DateAdapter;
import com.lwb.cargovoice.module.mvp.entity.response.AIResponse;
import com.lwb.cargovoice.module.mvp.entity.response.MyDataBean;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DataDialogFragment extends DialogFragment {

    private ArrayList<String> myList = new ArrayList<>();
    private ArrayList<MyDataBean> showList = new ArrayList<>();
    private RecyclerView rvData;
    private DateAdapter adapter;
    private OnDataFinishListener mListener;
    int i = 0;

    boolean isAdd = true;
    /**
     * 添加字段时间间隔
     */
    int addTime = 400;
    /**
     * 改变颜色时间间隔
     */
    int changeColorTime = 400;

    Handler han = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x001) {
                adapter.notifyDataSetChanged();
                showData();
            }
        }
    };

    public static DataDialogFragment newInstance(AIResponse.Order order) {
        DataDialogFragment f = new DataDialogFragment();

        Bundle args = new Bundle();
        args.putParcelable("order", order);
        f.setArguments(args);
        return f;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.cargovoice_date_dialog, null);
        AIResponse.Order order = getArguments().getParcelable("order");
        //获取指定类所有的变量的对象数组
        Field[] fields = order.getClass().getDeclaredFields();
        int length = fields.length;
        for (int i = 0; i < length; i++) {
            Field field = fields[i];
            String key = field.getName();//key表示这个变量的字符串
            if ("FE_flag".equals(key)) {
                continue;
            }
            try {
                String type = field.getGenericType().getTypeName();//获取到了key的类型
                String getMethodString = "get" + key.substring(0, 1).toUpperCase() + key.substring(1, key.length());//必须注意 substring 是左开右闭的
                if ("java.lang.String".equals(type)) {
                    String value = (String) order.getClass().getMethod(getMethodString).invoke(order);
                    if (!TextUtils.isEmpty(value)) {
                        myList.add(value);
                    }

                }
            } catch (Exception e) {
//                AirToast.showToast("出错啦" + e);
            }
        }
        rvData = view.findViewById(R.id.rv_date);
        adapter = new DateAdapter(showList);
        rvData.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvData.setAdapter(adapter);
        mListener.onDataFinish();
        return view;
    }

    public void showData() {
        if (isAdd) {
            //本次应当为添加
            if (myList.size() > 0 && i < myList.size()) {
                MyDataBean bean = new MyDataBean();
                bean.setData(myList.get(i));
                bean.setWhrite(false);
                showList.add(0, bean);
            }
            Message message = Message.obtain();
            message.what = 0x001;
            han.sendMessageDelayed(message, addTime);
            isAdd = false;
        } else {
            if (myList.size() > 0 && i < myList.size()) {
                //本次为变更颜色
                showList.get(0).setWhrite(true);
                Message message = Message.obtain();
                message.what = 0x001;
                han.sendMessageDelayed(message, changeColorTime);
                i++;
                isAdd = true;
            } else {
                dismiss();
            }
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(Dp2pxUtils.dp2px(getActivity(), 336), Dp2pxUtils.dp2px(getContext(), 544));
    }


    public interface OnDataFinishListener {
        void onDataFinish();
    }

    public void setOnDataFinishListener(OnDataFinishListener listener) {
        mListener = listener;
    }

    ;

}
