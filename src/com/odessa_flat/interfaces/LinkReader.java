package com.odessa_flat.interfaces;

import com.odessa_flat.interfaces.Event;
import com.odessa_flat.interfaces.Notifier;
import com.odessa_flat.model.Link;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Sergii on 25.02.2017.
 */
public abstract class LinkReader {
    protected Notifier<String> uniqueEventNotifier = new Notifier<>();
    protected Notifier<Integer> linkFoundEventNotifier = new Notifier<>();

    public final Event<Integer> LinkFoundEvent = linkFoundEventNotifier.getEvent();

    public final Event<String> UniqueEvent = uniqueEventNotifier.getEvent();

    public abstract BlockingQueue<Link> getLinkQueue();
}
