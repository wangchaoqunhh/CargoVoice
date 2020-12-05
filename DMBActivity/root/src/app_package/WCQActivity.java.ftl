package ${packageName}.module.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;

    import com.cargo.basecommon.base.BaseActivity;
    import ${packageName}.R;
    import ${packageName}.module.mvp.contract.${contractClass};
    import ${packageName}.module.mvp.entity.response.${responseClass};
    import ${packageName}.module.mvp.presenter.${presenterClass};

    import org.greenrobot.eventbus.EventBus;

    public class ${commonName}Activity extends BaseActivity<${commonName}Contract.View, ${commonName}Presenter> implements ${commonName}Contract.View {

    @Override
    protected void init() {

    EventBus.getDefault().register(this);

    }



    @Override
    protected void initPresenter() {
    mPresenter = new ${commonName}Presenter(mContext);
    }

    @Override
    protected int setLayout() {
    return R.layout.${layoutName};
    }


    @Override
    protected void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
    }


    @Override
    public void onSuccess(${responseClass} response) {

    }

    @Override
    public void onError(String result) {

    }

    public static void launchActivity(Context context) {
    Intent intent = new Intent(context, ${commonName}Activity.class);
    context.startActivity(intent);
    }
    }