package com.youngzy.stackskills.dp.p16state.media;

public interface IMediaPlayer {
    void setState(IMediaPlayerState state);
    IMediaPlayerState getState();
}
