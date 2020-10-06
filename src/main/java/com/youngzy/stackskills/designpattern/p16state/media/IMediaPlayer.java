package com.youngzy.stackskills.designpattern.p16state.media;

public interface IMediaPlayer {
    void setState(IMediaPlayerState state);
    IMediaPlayerState getState();
}
