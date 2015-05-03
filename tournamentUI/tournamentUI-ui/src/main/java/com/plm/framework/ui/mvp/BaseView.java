package com.plm.framework.ui.mvp;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button.ClickListener;

/**
 * All view will implement this interface. It permit to manage all standard listener automatically
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public interface BaseView extends View, ClickListener, ValueChangeListener{

}
