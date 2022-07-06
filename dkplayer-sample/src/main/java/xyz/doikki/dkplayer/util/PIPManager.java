package xyz.doikki.dkplayer.util;

import android.view.View;

import xyz.doikki.dkplayer.app.MyApplication;
import xyz.doikki.dkplayer.widget.FloatView;
import xyz.doikki.dkplayer.widget.controller.FloatController;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.player.VideoViewManager;

/**
 * 悬浮播放
 * Created by Doikki on 2018/3/30.
 */

public class PIPManager {

    public interface VideoViewRestore{
        void restore();
    }

    private final VideoView mVideoView;
    private final FloatView mFloatView;
    private final FloatController mFloatController;
    private boolean mIsShowing;
    private int mPlayingPosition = -1;

    public VideoViewRestore getVideoViewRestore() {
        return mVideoViewRestore;
    }

    public void setVideoViewRestore(VideoViewRestore mVideoViewRestore) {
        this.mVideoViewRestore = mVideoViewRestore;
    }

    private VideoViewRestore mVideoViewRestore;


    private PIPManager() {
        mVideoView = new VideoView(MyApplication.getInstance());
        VideoViewManager.instance().add(mVideoView, Tag.PIP);
        mFloatController = new FloatController(MyApplication.getInstance());
        mFloatView = new FloatView(MyApplication.getInstance(), 0, 0);
    }

    private static final class InstanceHolder {
        static final PIPManager instance = new PIPManager();
    }

    public static PIPManager getInstance() {
        return InstanceHolder.instance;
    }

    public void startFloatWindow() {
        if (mIsShowing) return;
        Utils.removeViewFormParent(mVideoView);
        mVideoView.setVideoController(mFloatController);
        mFloatController.setPlayState(mVideoView.getCurrentPlayState());
        mFloatController.setPlayerState(mVideoView.getCurrentPlayerState());
        mFloatView.addView(mVideoView);
        mFloatView.addToWindow();
        mIsShowing = true;
    }

    public void stopFloatWindow() {
        if (!mIsShowing) return;
        mFloatView.removeFromWindow();
        Utils.removeViewFormParent(mVideoView);
        mIsShowing = false;
    }

    public void setPlayingPosition(int position) {
        this.mPlayingPosition = position;
    }

    public int getPlayingPosition() {
        return mPlayingPosition;
    }

    public void pause() {
        if (mIsShowing) return;
        mVideoView.pause();
    }

    public void resume() {
        if (mIsShowing) return;
        mVideoView.resume();
    }

    public void reset() {
        if (mIsShowing) return;
        Utils.removeViewFormParent(mVideoView);
        mVideoView.release();
        mVideoView.setVideoController(null);
        mPlayingPosition = -1;
    }

    public boolean onBackPress() {
        return !mIsShowing && mVideoView.onBackPressed();
    }

    public boolean isStartFloatWindow() {
        return mIsShowing;
    }

    /**
     * 显示悬浮窗
     */
    public void setFloatViewVisible() {
        if (mIsShowing) {
            mVideoView.resume();
            mFloatView.setVisibility(View.VISIBLE);
        }
    }


}
