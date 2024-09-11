package dev.marco.demo.backend;

public class ChannelChangedEvent {

    private final int newChannelId;
    private final Object source;

    public ChannelChangedEvent(Object source, int newChannelId) {
        this.source = source;
        this.newChannelId = newChannelId;
    }

    public int getNewChannelId() {
        return newChannelId;
    }

    public Object getSource() {
        return source;
    }
}
