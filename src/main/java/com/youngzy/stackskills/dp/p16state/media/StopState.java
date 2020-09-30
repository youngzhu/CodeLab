package com.youngzy.stackskills.dp.p16state.media;

public class StopState implements IMediaPlayerState {
    @Override
    public void play(IMediaPlayer player) {
        IMediaPlayerState nextState = new PlayState();
        player.setState(nextState);
    }

    @Override
    public void pause(IMediaPlayer player) {

    }

    @Override
    public void stop(IMediaPlayer player) {

    }

    @Override
    public void seek(IMediaPlayer player) {

    }

    @Override
    public void rewind(IMediaPlayer player) {

    }

    @Override
    public void forward(IMediaPlayer player) {

    }

    @Override
    public double getVolume(IMediaPlayer player) {
        return 0;
    }
}
