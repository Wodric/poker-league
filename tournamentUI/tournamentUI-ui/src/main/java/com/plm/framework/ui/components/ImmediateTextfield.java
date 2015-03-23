package com.plm.framework.ui.components;

import com.vaadin.data.Property;
import com.vaadin.ui.TextField;

/**
 * Overwrite the textField to set immediate behavior by default
 * @author Lefèvre Alexandre "Wodric"
 *
 */
public class ImmediateTextfield extends TextField {
	
	/**
	 * Serialization UID
	 */
	private static final long serialVersionUID = -9214656686649195895L;
	
	/**
	 * Value to set to immediate settings
	 */
	private final static boolean IS_IMMEDIATE = true;
	
    /**
     * Constructs an empty <code>TextField</code> with no caption.
     */
    public ImmediateTextfield() {
        clear();
        setImmediate(IS_IMMEDIATE);
    }

    /**
     * Constructs an empty <code>TextField</code> with given caption.
     * 
     * @param caption
     *            the caption <code>String</code> for the editor.
     */
    public ImmediateTextfield(String caption) {
        this();
        setCaption(caption);
        setImmediate(IS_IMMEDIATE);
    }

    /**
     * Constructs a new <code>TextField</code> that's bound to the specified
     * <code>Property</code> and has no caption.
     * 
     * @param dataSource
     *            the Property to be edited with this editor.
     */
    @SuppressWarnings("rawtypes")
	public ImmediateTextfield(Property dataSource) {
        setPropertyDataSource(dataSource);
        setImmediate(IS_IMMEDIATE);
    }

    /**
     * Constructs a new <code>TextField</code> that's bound to the specified
     * <code>Property</code> and has the given caption <code>String</code>.
     * 
     * @param caption
     *            the caption <code>String</code> for the editor.
     * @param dataSource
     *            the Property to be edited with this editor.
     */
    @SuppressWarnings("rawtypes")
	public ImmediateTextfield(String caption, Property dataSource) {
        this(dataSource);
        setCaption(caption);
        setImmediate(IS_IMMEDIATE);
    }

    /**
     * Constructs a new <code>TextField</code> with the given caption and
     * initial text contents. The editor constructed this way will not be bound
     * to a Property unless
     * {@link com.vaadin.data.Property.Viewer#setPropertyDataSource(Property)}
     * is called to bind it.
     * 
     * @param caption
     *            the caption <code>String</code> for the editor.
     * @param value
     *            the initial text content of the editor.
     */
    public ImmediateTextfield(String caption, String value) {
        setValue(value);
        setCaption(caption);
        setImmediate(IS_IMMEDIATE);
    }

}
