package ${packageName}.module.mvp.presenter;    import android.content.Context;    import android.content.Intent;    import com.cargo.basecommon.base.IPresenter;    import com.cargo.basecommon.base.IView;    import ${packageName}.module.mvp.contract.${contractClass};    import io.reactivex.disposables.CompositeDisposable;    public class ${commonName}Presenter implements IPresenter {    private Context mContext;    private CompositeDisposable mCompositeDisposable;    private ${commonName}Contract.View mView;    public ${commonName}Presenter(Context context) {    this.mContext = context;    }    @Override    public void onCreate() {    //用来存放RxJava的订阅关系,请求结束时需要清理掉这个订阅,否则会产生内存泄漏           mCompositeDisposable = new CompositeDisposable();    }    @Override    public void onStart() {    }    @Override    public void onStop() {    mCompositeDisposable.dispose();    }    @Override    public void onPause() {    }    @Override    public void attachView(IView view) {    mView = (${commonName}Contract.View) view;    }    @Override    public void attachIncomingIntent(Intent intent) {    }    }