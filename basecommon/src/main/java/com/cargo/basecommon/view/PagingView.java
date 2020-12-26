package com.cargo.basecommon.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cargo.basecommon.R;
import com.cargo.basecommon.base.IView;
import com.cargo.basecommon.base.RxJavaObserver;
import com.cargo.basecommon.utils.ListUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

public class PagingView<T> extends LinearLayout {
    private Context mContext;

    private int oldPageNumber = 0;
    private int pageNumber = 1;
    private int pageSize = 10;

    public RefreshLayout mRefreshLayout;
    public RecyclerView recyclerview;

    public LinearLayout mEmpty;
    private ImageView mIvEmy;
    private TextView mTvEmy;
    /**
     * 是否是 刷新 or 加载
     * 1刷新
     * 2加载 更多
     */
    private int mRefreshOrLoadMore;
    private BaseQuickAdapter<T, BaseViewHolder> mAdapter;


    /**
     * 是否启用下拉刷新
     */
    private boolean enableRefresh = true;
    /***
     *  是否启用上拉加载
     */
    private boolean enableLoadMore = true;

    public PagingView(Context context) {
        super(context);
        init(context);
    }

    public PagingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initTypeArray(context, attrs);
        init(context);
    }

    public PagingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeArray(context, attrs);
        init(context);
    }

    public PagingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initTypeArray(context, attrs);
        init(context);
    }

    private void initTypeArray(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PagingView);

        pageSize = array.getInt(R.styleable.PagingView_pv_pageSize, 10);
    }

    private void init(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.paging_view, null);
        mRefreshLayout = view.findViewById(R.id.smartLayout);
        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mEmpty = view.findViewById(R.id.ll_emy);
        mIvEmy = view.findViewById(R.id.iv_emy);
        mTvEmy = view.findViewById(R.id.tv_emy);

        mRefreshLayout.setEnableScrollContentWhenLoaded(true);
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(true);
        mRefreshLayout.setDisableContentWhenRefresh(true);
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            if (onRequest != null) {
                pageNumber = 1;
                onRequest.onRequest();
            }
            mRefreshOrLoadMore = 1;
        });

        //加载更多
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (onRequest != null) {
                //保存上一次的页码数
                this.oldPageNumber = pageNumber;
                //如果 列表总数>= 页码数*每页个数相等 我需要 把页码数加1
                List<T> data = mAdapter.getData();
                if (data.size() >= pageNumber * pageSize) {
                    pageNumber++;
                }
                onRequest.onRequest();
            }
            mRefreshOrLoadMore = 2;
        });
        addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public void onError() {
        if (mRefreshOrLoadMore == 1) {
            mRefreshLayout.finishRefresh(false);
        } else if (mRefreshOrLoadMore == 2) {
            mRefreshLayout.finishLoadMore(false);
        }
        mRefreshOrLoadMore = 0;
        recyclerview.setVisibility(View.GONE);
        mEmpty.setVisibility(View.VISIBLE);
        mIvEmy.setImageDrawable(mContext.getDrawable(R.drawable.svg_ic_undraw_aircraft_fbvl));
        mTvEmy.setText(R.string.network_connection_failed_please_reload);
        mRefreshLayout.setEnableLoadMore(false);
    }

    public void onSuccess(List<T> list) {
        List<T> allData = mAdapter.getData();
        if (mRefreshOrLoadMore == 1) {
            allData.clear();
            mRefreshLayout.finishRefresh(true);
        } else if (mRefreshOrLoadMore == 2) {
            mRefreshLayout.finishLoadMore(true);
            //如果这次请求的页码数 和 上一次相等，那么最后这次刷新就是最后的数据
            if (oldPageNumber == pageNumber) {
                int allDataSize = allData.size();
                for (int i = 0; i < allDataSize % pageSize; i++) {
                    allData.remove(allData.size() - 1);
                }
            }
        }
        mRefreshOrLoadMore = 0;

        if (!ListUtils.isEmpty(list)) {
            allData.addAll(list);
        }
        if (!ListUtils.isEmpty(allData)) {
            recyclerview.setVisibility(View.VISIBLE);
            mEmpty.setVisibility(View.GONE);

            if (enableLoadMore) {
                if (list.size() < pageSize && footView != null) {
                    //显示 脚布局
                    mAdapter.setFooterView(footView);
                    mRefreshLayout.setEnableLoadMore(false);
                } else {
                    mAdapter.removeAllFooterView();
                    mRefreshLayout.setEnableLoadMore(true);
                }
            }

            mAdapter.notifyDataSetChanged();
        } else {
            recyclerview.setVisibility(View.GONE);
            mEmpty.setVisibility(View.VISIBLE);

            mIvEmy.setImageDrawable(mContext.getDrawable(R.drawable.svg_ic_undraw_no_data_re_kwbl));
            mTvEmy.setText(R.string.no_content_yet);

            mRefreshLayout.setEnableLoadMore(false);
        }
    }

    private View footView;

    /**
     * 新的请求 和下拉刷新一样
     */
    public void newRequest() {
        if (onRequest != null) {
            pageNumber = 1;
            onRequest.onRequest();
        }
        mRefreshOrLoadMore = 1;
    }

    /**
     * 和上拉加载 逻辑一样
     */
    public void doRefresh() {
        if (onRequest != null) {
            //保存上一次的页码数
            this.oldPageNumber = pageNumber;
            //如果 列表总数>= 页码数*每页个数相等 我需要 把页码数加1
            List<T> data = mAdapter.getData();
            if (data.size() >= pageNumber * pageSize) {
                pageNumber++;
            }
            onRequest.onRequest();
        }
        mRefreshOrLoadMore = 2;
    }

    public PagingView setAdapter(BaseQuickAdapter<T, BaseViewHolder> adapter) {
        mAdapter = adapter;
        recyclerview.setAdapter(adapter);
        return this;
    }

    public PagingView setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerview.setLayoutManager(layoutManager);
        return this;
    }

    public void startRequest() {
        if (onRequest != null) {
            onRequest.onRequest();
        }
    }

    public PagingView setFootView(View footView) {
        this.footView = footView;
        return this;
    }


    /**
     * 是否启用下拉刷新
     */
    public PagingView setEnableRefresh(boolean enableRefresh) {
        this.enableRefresh = enableRefresh;
        if (mRefreshLayout != null) {
            mRefreshLayout.setEnableRefresh(enableRefresh);
        }
        return this;
    }

    /**
     * 是否启用上拉加载
     */
    public PagingView setEnableLoadMore(boolean enableLoadMore) {
        this.enableLoadMore = enableLoadMore;
        if (mRefreshLayout != null) {
            mRefreshLayout.setEnableLoadMore(enableLoadMore);
        }
        return this;
    }

    /**
     * 没写完
     */
    @Deprecated()
    public <V extends IView, T> void getObserver(IView mView) {
        new RxJavaObserver<T>() {
            @Override
            public void onNext(T response) {

            }
        };
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PagingView setOnRequest(OnRequest onRequest) {
        this.onRequest = onRequest;
        return this;
    }

    private OnRequest onRequest;

    public interface OnRequest {
        public void onRequest();
    }

}
