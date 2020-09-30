package com.youngzy.stackskills.dp.p16state.media;

public class MediaPlayer implements IMediaPlayer {
    IMediaPlayerState currentState;

    public void play() {
        // 播放
        // play code...

        // 设置状态
        currentState.play(this);
    }

    @Override
    public void setState(IMediaPlayerState state) {

    }

    @Override
    public IMediaPlayerState getState() {
        return null;
    }
}
