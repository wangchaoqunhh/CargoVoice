package com.cargo.basecommon.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment<V extends IView,P extends IPresenter> extends Fragment {

    protected P mPresenter;
    protected Context mContext;
    protected View v;
    private boolean isFirst = true;
    private boolean isPrepared;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    protected abstract void init(View v);

    protected abstract void initPresenter();

    protected abstract int setLayout();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayout(), container, false);
        ButterKnife.bind(this,view);
        initPresenter();
        if (mPresenter != null) {
            mPresenter.onCreate();
            mPresenter.attachView((V) this);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoad();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getUserVisibleHint()) {
            setUserVisibleHint(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isFirst = true;
        isPrepared = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        resetState();
    }

    private void lazyLoad() {
        if (!isFirst || !isPrepared) {
            return;
        }
        isFirst = false;
        initData();
    }

    private void resetState() {
        isFirst = true;
        isPrepared = false;
    }

    protected abstract void initData();



}
