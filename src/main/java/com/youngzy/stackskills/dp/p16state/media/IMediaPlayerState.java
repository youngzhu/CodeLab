package com.youngzy.stackskills.dp.p16state.media;

public interface IMediaPlayerState {
    // 每一个状态都应该知道如何过渡到另一个状态
    void play(IMediaPlayer player);
    void pause(IMediaPlayer player);
    void stop(IMediaPlayer player);
    void seek(IMediaPlayer player);
    void rewind(IMediaPlayer player);
    void forward(IMediaPlayer player);
    double getVolume(IMediaPlayer player);
}
