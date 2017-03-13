package com.odessa_flat.interfaces;

import com.odessa_flat.filters.BaseResolver;
import com.odessa_flat.model.Content;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sergii on 24.01.2017.
 */
public interface HtmlIterable<T> extends Iterable<T> {

    void setIn(Content in) throws MalformedURLException;

    default void setAllowed(String allowed) {
    }

    default void setDisalowed(String disallowed) {
    }
}
