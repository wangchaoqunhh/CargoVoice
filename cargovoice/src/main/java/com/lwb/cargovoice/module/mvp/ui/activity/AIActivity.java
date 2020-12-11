package com.lwb.cargovoice.module.mvp.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cargo.basecommon.base.BaseActivity;
import com.cargo.basecommon.base.BaseFragmentActivity;
import com.cargo.basecommon.utils.AirToast;
import com.cargo.basecommon.utils.InputMethodUtil;
import com.cargo.basecommon.utils.KeyboardChangeListener;
import com.cargo.basecommon.utils.ListUtils;
import com.cargo.basecommon.utils.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.lwb.cargovoice.R;
import com.lwb.cargovoice.R2;
import com.lwb.cargovoice.adapter.AIButtonAdapter;
import com.lwb.cargovoice.module.mvp.contract.AIContract;
import com.lwb.cargovoice.module.mvp.entity.request.AIRequest;
import com.lwb.cargovoice.module.mvp.entity.response.AIResponse;
import com.lwb.cargovoice.module.mvp.presenter.AIPresenter;
import com.lwb.cargovoice.module.mvp.ui.fragment.DataDialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = "/cargovoice/AIActivity")
public class AIActivity extends BaseFragmentActivity<AIContract.View, AIPresenter> implements AIContract.View {

    @BindView(R2.id.wv)
    WebView webView;
    @BindView(R2.id.tv_result)
    TextView tvResult;
    @BindView(R2.id.tv_speak)
    TextView tvSpeak;
    @BindView(R2.id.rl_control)
    RelativeLayout rlControl;
    @BindView(R2.id.cv_record)
    CardView cvRecord;
    @BindView(R2.id.iv_record)
    ImageView ivRecord;
    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.iv_keyboard)
    ImageView ivKeyboard;
    @BindView(R2.id.iv_gif)
    ImageView ivGif;
    @BindView(R2.id.iv_send)
    ImageView ivSend;
    @BindView(R2.id.iv_volume)
    ImageView ivVolume;
    @BindView(R2.id.rv_button)
    RecyclerView rvButton;
    @BindView(R2.id.rl_record_bottom)
    RelativeLayout rlRecord;
    @BindView(R2.id.rl_edit)
    RelativeLayout rlEdit;
    @BindView(R2.id.editText)
    EditText editText;

    private SpeechRecognizer mIat;
    private StringBuffer buffer = new StringBuffer();
    private SpeechSynthesizer mTts;
    /**
     * 用户发送的文字
     */
    private String sendMessageString;

    /**
     * 是否有声音 true有  false无
     */
    private boolean isVolume;

    /**
     * 发送请求的状态默认1  1:开始   2:正常状态  3:关闭
     */
    public int requestType = 1;

    Handler han = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                dialogFragment.showData();
            } else if (msg.what == 2) {
                rvButton.setVisibility(View.GONE);
                AIRequest request = new AIRequest();
                request.setSender((String) SPUtils.get(mContext, "token", ""));
                request.setMessage(sendMessageString);
                mPresenter.aiHook(request);
            }
        }
    };
    private DataDialogFragment dialogFragment;


    @Override
    protected void init() {
        initWebIcon();
        initListener();
        isVolume = (boolean) SPUtils.get(mContext, "isVolume", true);
        if (isVolume) {
            //能说话
            ivVolume.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_volume));
        } else {
            //不能说话
            ivVolume.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_volume_3));
        }
        initAI();
        AIRequest request = new AIRequest();
        request.setSender((String) SPUtils.get(mContext, "token", ""));
        request.setMessage("/restart");
        mPresenter.aiHook(request);
        rvButton.setVisibility(View.GONE);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            //切换状态为关闭
            requestType = 3;
            AIRequest request = new AIRequest();
            request.setSender((String) SPUtils.get(mContext, "token", ""));
            request.setMessage("/restart");
            mPresenter.aiHook(request);
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 初始化点击事件
     */
    private void initListener() {
        //开始语音识别
        cvRecord.setOnClickListener(v -> {
            buffer = new StringBuffer();
            //开始识别，并设置监听器
            mIat.startListening(mRecognizerListener);
        });
        ivBack.setOnClickListener(v -> {
            //切换状态为关闭
            requestType = 3;
            AIRequest request = new AIRequest();
            request.setSender((String) SPUtils.get(mContext, "token", ""));
            request.setMessage("/restart");
            mPresenter.aiHook(request);
        });

        //点击键盘,隐藏其他布局,展示键盘布局
        ivKeyboard.setOnClickListener(v -> {
            rlEdit.setVisibility(View.VISIBLE);
            rlRecord.setVisibility(View.GONE);
            InputMethodUtil.show(editText);
        });
        //键盘关闭监听
        KeyboardChangeListener.create(this).setKeyboardListener((isShow, keyboardHeight) -> {
            if (!isShow) {
                rlEdit.setVisibility(View.GONE);
                rlRecord.setVisibility(View.VISIBLE);
            }
        });
        //失去焦点,关闭键盘
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                InputMethodUtil.hide(this);
            }
        });
        ivSend.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(editText.getText().toString())) {
                sendMessageString = editText.getText().toString();
                //展示用户说的话
                showSpeak();
                //发送用户说的话到后台
                sendMessage(2);
                rvButton.setVisibility(View.GONE);
                editText.setText("");
            }
        });
        ivVolume.setOnClickListener(v -> {
            if (isVolume) {
                //变为不能说话
                ivVolume.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_volume_3));
                AirToast.showToast("语音播报已关闭");
            } else {
                //变为能说话
                ivVolume.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_volume));
                AirToast.showToast("语音播报已开启");
            }
            isVolume = !isVolume;
            //记录是否语音播报最新状态
            SPUtils.put(mContext, "isVolume", isVolume);
        });
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                sendMessageString = editText.getText().toString();
                //展示用户说的话
                showSpeak();
                //发送用户说的话到后台
                sendMessage(2);
                rvButton.setVisibility(View.GONE);
                editText.setText("");
                return true;
            }
            return false;
        });
    }

    private void showSpeak() {
        rvButton.setVisibility(View.GONE);
        tvSpeak.setVisibility(View.VISIBLE);
        tvSpeak.setText('"' + sendMessageString + '"');
    }

    /**
     * 初始化web图
     */
    private void initWebIcon() {
        webView.loadUrl("file:///android_asset/dist/index.html");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setBackgroundColor(0);
        webView.getBackground().setAlpha(0);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void initPresenter() {
        mPresenter = new AIPresenter(mContext);
    }

    @Override
    protected int setLayout() {
        return R.layout.cargovoice_activity_ai;
    }

    /**
     * 调起录音
     */
    private void initAI() {
        //使用SpeechRecognizer对象，可根据回调消息自定义界面；
        mIat = SpeechRecognizer.createRecognizer(this, i -> Log.d("李文博", "SpeechRecognizer init() code = " + i));
        //初始化语音合成
        mTts = SpeechSynthesizer.createSynthesizer(this, i -> Log.d("李文博", "SpeechRecognizer init() code = " + i));
        initListenerParameter();
        initSpeakParameter("50");
    }


    /**
     * 释放资源
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != mIat) {
            // 退出时释放连接
            mIat.cancel();
            mIat.destroy();
        }
    }


    @Override
    public void onFail(String s) {
        Log.e("李文博 onFail", s);
        AirToast.showToast(s);
    }


    @Override
    public void onSuccess(List<AIResponse> aiResponses) {
        if (requestType == 1) {
            //当前状态为开始
            AIRequest request = new AIRequest();
            request.setSender((String) SPUtils.get(mContext, "token", ""));
            request.setMessage("你好");
            mPresenter.aiHook(request);
            //切换状态
            requestType = 2;
        } else if (requestType == 2) {
            tvSpeak.setVisibility(View.GONE);
            rvButton.setVisibility(View.GONE);
            if (!ListUtils.isEmpty(aiResponses)) {
                List<AIResponse.ButtonItem> allButtons = new ArrayList<>();
                for (int i = 0; i < aiResponses.size(); i++) {
                    if (!TextUtils.isEmpty(aiResponses.get(i).getText())) {
                        buffer = new StringBuffer();
                        buffer.append(aiResponses.get(i).getText());
                        tvResult.setText(buffer);
                        //有声音
                        if (isVolume) {
                            int code = mTts.startSpeaking(aiResponses.get(i).getText(), mSpeakListener);
                            if (code != ErrorCode.SUCCESS) {
                                Log.e("李文博","语音合成失败,错误码: " + code + ",请点击网址https://www.xfyun.cn/document/error-code查询解决方案");
                            }
                        }
                        //有按钮
                        if (!ListUtils.isEmpty(aiResponses.get(i).getButtons())) {
                            rvButton.setVisibility(View.VISIBLE);
                            allButtons.addAll(aiResponses.get(i).getButtons());
                            AIButtonAdapter adapter = new AIButtonAdapter(allButtons);
                            rvButton.setLayoutManager(new LinearLayoutManager(mContext));
                            rvButton.setAdapter(adapter);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    sendMessageString = allButtons.get(position).getPayload();
                                    sendMessage(2);
                                }
                            });
                        } else {
                            rvButton.setVisibility(View.GONE);
                        }

                    }
                    if (!ListUtils.isEmpty(aiResponses.get(i).getCustom())) {
                        String fe_flag = aiResponses.get(i).getCustom().get(0).getFE_flag();
                        if ("3C".equals(fe_flag)) {
                            //弹出确认弹窗
                            dialogFragment = DataDialogFragment.newInstance(aiResponses.get(i).getCustom().get(0));
                            dialogFragment.show(getSupportFragmentManager(), "dialog");
                            dialogFragment.setOnDataFinishListener(new DataDialogFragment.OnDataFinishListener() {
                                @Override
                                public void onDataFinish() {
                                    sendMessage(1);
                                }
                            });
                        } else if ("true".equals(fe_flag)) {
                            //询价
                            EnquiryAddGoalActivity.launchActivity(mContext, 1, aiResponses.get(i).getCustom().get(0));
                        }
                    }
                }
            }

        }else if (requestType == 3){
            //状态为关闭
            finish();
        }

    }

    @Override
    public void onError(String result) {
        Log.e("李文博 onError", result);
        AirToast.showToast(result);
    }

    /**
     * 录音回调
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {
        @Override
        public void onVolumeChanged(int i, byte[] bytes) {

        }

        @Override
        public void onBeginOfSpeech() {
            webView.evaluateJavascript("javascript:changeMoon()", s -> {
            });
            ivRecord.setVisibility(View.GONE);
            rlControl.setVisibility(View.GONE);
            //显示Gif图
            cvRecord.setVisibility(View.GONE);
            ivGif.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(R.drawable.voice).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    if (drawable instanceof GifDrawable) {
                        GifDrawable gifDrawable = (GifDrawable) drawable;
                        gifDrawable.setLoopCount(20);
                        ivGif.setImageDrawable(drawable);
                        gifDrawable.start();
                    }
                }
            });
            Log.e("李文博", "开始说话+变形");
        }

        @Override
        public void onEndOfSpeech() {
            webView.evaluateJavascript("javascript:resetMoon()", s -> {
            });
            ivRecord.setVisibility(View.VISIBLE);
            rlControl.setVisibility(View.VISIBLE);
            //隐藏Gif图
            cvRecord.setVisibility(View.VISIBLE);
            ivGif.setVisibility(View.GONE);
            Log.e("李文博", "结束说话+收形");
        }

        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            buffer.append(recognizerResult.getResultString());
            sendMessageString = buffer.toString();
            if (b) {
                //展示用户说的话
                showSpeak();
                //发送用户说的话到后台
                sendMessage(2);
                rvButton.setVisibility(View.GONE);
            }
        }

        @Override
        public void onError(SpeechError speechError) {
            AirToast.showToast(speechError.getPlainDescription(true));
            Log.e("李文博", speechError.getPlainDescription(true));
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };

    private void sendMessage(int code) {
        Message message = Message.obtain();
        message.what = code;
        han.sendMessageDelayed(message, 500);
    }

    /**
     * 语音合成
     */
    private SynthesizerListener mSpeakListener = new SynthesizerListener() {
        @Override
        public void onSpeakBegin() {
            Log.e("李文博", "开始播放");
        }

        @Override
        public void onBufferProgress(int i, int i1, int i2, String s) {
            //合成进度
        }

        @Override
        public void onSpeakPaused() {
            Log.e("李文博", "暂停播放");
        }

        @Override
        public void onSpeakResumed() {
            Log.e("李文博", "继续播放");
        }

        @Override
        public void onSpeakProgress(int i, int i1, int i2) {
            // 播放进度
        }

        @Override
        public void onCompleted(SpeechError speechError) {
            Log.e("李文博", "播放完成");
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };

    /**
     * 初始化语音听写参数
     */
    private void initListenerParameter() {
        if (null == mIat) {
            // 创建单例失败，与 21001 错误为同样原因，参考 http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=9688
            Log.e("李文博", "创建对象失败，请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化");
            return;
        }
        //设置语法ID和 SUBJECT 为空，以免因之前有语法调用而设置了此参数；或直接清空所有参数，具体可参考 DEMO 的示例。
        mIat.setParameter(SpeechConstant.CLOUD_GRAMMAR, null);
        mIat.setParameter(SpeechConstant.SUBJECT, null);
        //设置返回结果格式，目前支持json,xml以及plain 三种格式，其中plain为纯听写文本内容
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "plain");
        //此处engineType为“cloud”
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, "cloud");
        //设置语音输入语言，zh_cn为简体中文
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        //设置结果返回语言
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin");
        // 设置语音前端点:静音超时时间，单位ms，即用户多长时间不说话则当做超时处理
        //取值范围{1000～10000}
        mIat.setParameter(SpeechConstant.VAD_BOS, "4000");
        //设置语音后端点:后端点静音检测时间，单位ms，即用户停止说话多长时间内即认为不再输入，
        //自动停止录音，范围{0~10000}
        mIat.setParameter(SpeechConstant.VAD_EOS, "1000");
        //设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, "0");
    }

    /**
     * 初始化语音合成参数
     *
     * @param volume 声音大小,正常50,静音0
     */
    private void initSpeakParameter(String volume) {
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        // 根据合成引擎设置相应参数
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        //支持实时音频返回，仅在synthesizeToUri条件下支持
        mTts.setParameter(SpeechConstant.TTS_DATA_NOTIFY, "1");
        //	mTts.setParameter(SpeechConstant.TTS_BUFFER_TIME,"1");

        // 设置在线合成发音人
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, "50");
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, "50");
        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "false");

//        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
//        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "pcm");
//        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.pcm");
    }

}
