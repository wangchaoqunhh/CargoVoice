package ${packageName}.module.mvp.model;

import android.content.Context;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.http.RetrofitHelper;

import ${packageName}.module.mvp.contract.${contractClass};

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

    public class ${commonName}Model implements ${commonName}Contract.Model {

    private Context mContext;

    public ${commonName}Model(Context context) {
    this.mContext = context;
    }




    // @Override
    // public Observable<BaseResponse<> xxx(String name) {
//     return RetrofitHelper.getInstance(mContext)
//             .getRetrofit()
//             .create(CarChooseApi.class)
//             .getCarByName(name)//调用相应的方法
//             .subscribeOn(Schedulers.io())//请求时切换线程到io线程
//             .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到主线程(ui线程)
// }
}
