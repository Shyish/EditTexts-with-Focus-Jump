package com.zdvdev.focusjumedittexts.fjet;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.EditText;

import java.lang.reflect.Field;

/**
 * Custom EditText which let you obtain the xml-defined maxLength attribute.
 *
 * @author aballano
 *         Date: 14/05/14
 */
@SuppressWarnings("UnusedDeclaration")
public class LengthedEditText extends EditText {

	private int maxLength = -1;

	public LengthedEditText(Context context) {
		super(context);
		init();
	}

	public LengthedEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LengthedEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public int init() {
		if (maxLength == -1) {
			InputFilter[] inputFilters = getFilters();

			try {
				if (inputFilters.length > 0 && inputFilters[0] instanceof InputFilter.LengthFilter) {
					Field f = inputFilters[0].getClass().getDeclaredField("mMax");

					f.setAccessible(true);
					maxLength = (Integer) f.get(inputFilters[0]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return maxLength;
	}

	public int getMaxLength() {
		return maxLength;
	}
}
