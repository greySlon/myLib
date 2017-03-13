package com.odessa_flat.interfaces;

import javafx.collections.WeakListChangeListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by Sergii on 14.02.2017.
 */
public class Notifier<T> {
    private WeakHashMap<Listener<T>, Boolean> map = new WeakHashMap<>();


    public void raiseEvent(Object sender, T arg) {
        for (Listener<T> listener : map.keySet()) {
            listener.eventHandler(sender, arg);
        }
    }

    public Event<T> getEvent() {
        return new Event<T>() {
            @Override
            public void addEventListner(Listener<T> listner) {
                if (listner != null)
                    map.put(listner, null);
            }

            @Override
            public void removeListner(Listener<T> listner) {
                if (listner != null)
                    map.remove(listner);
            }
        };
    }
}
