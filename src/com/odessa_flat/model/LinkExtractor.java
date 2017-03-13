package com.odessa_flat.model;

import com.odessa_flat.filters.*;
import com.odessa_flat.interfaces.Event;
import com.odessa_flat.interfaces.HtmlExtractor;
import com.odessa_flat.interfaces.HtmlIterable;
import com.odessa_flat.interfaces.Notifier;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Sergii on 24.01.2017.
 */

public class LinkExtractor extends HtmlExtractor<Content, URL> {
    private HtmlIterable<URL> htmlIterable = new HtmlLinkIterator();
    private Notifier<Integer> linkFoundEventNotifier = new Notifier();

    public final Event<Integer> LinkFoundEvent = linkFoundEventNotifier.getEvent();

    public static class Builder {
        private BlockingQueue<Content> sourceQueue;
        private boolean enablePassThrough;
        private String disallowed;

        public Builder(BlockingQueue<Content> sourceQueue) {
            this.sourceQueue = sourceQueue;
        }

        public Builder enablePassThrough(boolean enable) {
            this.enablePassThrough = enable;
            return this;
        }

        public Builder setDisallowed(String disallowed) {
            this.disallowed = disallowed;
            return this;
        }

        public LinkExtractor build() {
            return new LinkExtractor(this);
        }
    }

    private LinkExtractor(Builder builder) {
        super(builder.sourceQueue);
        htmlIterable.setDisalowed(builder.disallowed);
        enableQueuePassTrough(builder.enablePassThrough);
    }

    @Override
    protected void extract() throws InterruptedException {
        try {
            Content content = sourceQueue.take();
            if (queuePassThrough != null) {
                queuePassThrough.put(content);
            }
            htmlIterable.setIn(content);
            int count = 0;
            for (URL url : htmlIterable) {
                queueOut.put(url);
                count++;
            }
            linkFoundEventNotifier.raiseEvent(this, count);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
