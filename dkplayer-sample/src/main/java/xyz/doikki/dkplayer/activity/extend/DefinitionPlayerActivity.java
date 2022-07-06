package xyz.doikki.dkplayer.activity.extend;

import xyz.doikki.dkplayer.R;
import xyz.doikki.dkplayer.activity.BaseActivity;
import xyz.doikki.dkplayer.widget.component.DefinitionControlView;
import xyz.doikki.videocontroller.StandardVideoController;
import xyz.doikki.videocontroller.component.CompleteView;
import xyz.doikki.videocontroller.component.ErrorView;
import xyz.doikki.videocontroller.component.GestureView;
import xyz.doikki.videocontroller.component.PrepareView;
import xyz.doikki.videocontroller.component.TitleView;
import xyz.doikki.videoplayer.player.VideoView;

import java.util.LinkedHashMap;

/**
 * 清晰度切换
 * Created by Doikki on 2017/4/7.
 */

public class DefinitionPlayerActivity extends BaseActivity<VideoView> implements DefinitionControlView.OnRateSwitchListener {

    private StandardVideoController mController;
    private DefinitionControlView mDefinitionControlView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_layout_common;
    }

    @Override
    protected int getTitleResId() {
        return R.string.str_definition;
    }

    @Override
    protected void initView() {
        super.initView();
        mVideoView = findViewById(R.id.video_view);

        mController = new StandardVideoController(this);
        addControlComponents();

        LinkedHashMap<String, String> videos = new LinkedHashMap<>();
        videos.put("640p", "https://rr2---sn-i3b7knse.googlevideo.com/videoplayback?expire=1657002655&ei=P4bDYvu7H-eVvcAPr9iekA4&ip=104.208.79.160&id=o-AFpFuBRCL9xsWmqrrixzpiDMjDoFNEoit4DmmZcqpPvn&itag=18&source=youtube&requiressl=yes&mh=1Y&mm=31%2C26&mn=sn-i3b7knse%2Csn-3pm7dn7y&ms=au%2Conr&mv=m&mvi=2&pl=18&initcwndbps=716250&vprv=1&mime=video%2Fmp4&gir=yes&clen=65579833&ratebypass=yes&dur=720.236&lmt=1653870432773132&mt=1656980693&fvip=5&fexp=24001373%2C24007246&c=ANDROID&txp=4538434&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRQIgESizkmxN-WvCl5gF48RsV8FTrSnt7wHvSsRFjLWS4t8CIQDR92DGd35VsBtMbLucfrSYRT1c0vmeh_Gk0IsQJaB_jQ%3D%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRQIgEfaA9vJ_oJysN9FxtgxOJ_fXW0sXIDk4HXnh1C-jPQsCIQD5cbMgj8uJevWFnxN0iASlhCAIt6jjTTa4pVCZ2AFqeQ%3D%3D&cpn=PTgZxh367lmN39lc");
        videos.put("1280p", "https://rr2---sn-i3b7knse.googlevideo.com/videoplayback?expire=1657002655&ei=P4bDYvu7H-eVvcAPr9iekA4&ip=104.208.79.160&id=o-AFpFuBRCL9xsWmqrrixzpiDMjDoFNEoit4DmmZcqpPvn&itag=22&source=youtube&requiressl=yes&mh=1Y&mm=31%2C26&mn=sn-i3b7knse%2Csn-3pm7dn7y&ms=au%2Conr&mv=m&mvi=2&pl=18&initcwndbps=716250&vprv=1&mime=video%2Fmp4&cnr=14&ratebypass=yes&dur=720.236&lmt=1653879810678943&mt=1656980693&fvip=5&fexp=24001373%2C24007246&c=ANDROID&txp=4532434&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Ccnr%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRQIgdDgcjq5FRHoUxuKrGyoGYGB1ycaVZjsFUhlRUWYt7-cCIQCaLKsvQZErew2fdAITLgzD8ZMQ-3JdbHh0i06JjbGv5g%3D%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRQIgEfaA9vJ_oJysN9FxtgxOJ_fXW0sXIDk4HXnh1C-jPQsCIQD5cbMgj8uJevWFnxN0iASlhCAIt6jjTTa4pVCZ2AFqeQ%3D%3D&cpn=PTgZxh367lmN39lc");
        mDefinitionControlView.setData(videos);
        mVideoView.setVideoController(mController);
        mVideoView.setUrl(videos.get("640p"));//默认播放标清
        mVideoView.start();
    }

    private void addControlComponents() {
        CompleteView completeView = new CompleteView(this);
        ErrorView errorView = new ErrorView(this);
        PrepareView prepareView = new PrepareView(this);
        prepareView.setClickStart();
        TitleView titleView = new TitleView(this);
        mDefinitionControlView = new DefinitionControlView(this);
        mDefinitionControlView.setOnRateSwitchListener(this);
        GestureView gestureView = new GestureView(this);
        mController.addControlComponent(completeView, errorView, prepareView, titleView, mDefinitionControlView, gestureView);
    }

    @Override
    public void onRateChange(String url) {
        mVideoView.setUrl(url);
        mVideoView.replay(false);
    }
}
