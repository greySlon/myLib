package com.odessa_flat.interfaces;

/**
 * Created by Sergii on 15.02.2017.
 */
public interface Event<T> {
    void addEventListner(Listener<T> listner);
    void removeListner(Listener<T> listner);
}
