package com.zdvdev.focusjumedittexts.fjet;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.List;

/**
 * A simple class which manages focus' jumps between list-linked EditTexts.
 *
 * @author aballano
 *         Date: 14/05/14
 */
public class EditTextFocusJumperController implements View.OnFocusChangeListener, TextWatcher {

	private List<LengthedEditText> mFields;
	int mCurrentSelectedFieldPosition;

	public EditTextFocusJumperController(List<LengthedEditText> fields) {
		mFields = fields;
	}

	void requestNextFocus() {
		if (++mCurrentSelectedFieldPosition < mFields.size()) {
			mFields.get(mCurrentSelectedFieldPosition).requestFocus();
		}
	}

	@Override public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			mCurrentSelectedFieldPosition = (Integer) v.getTag();
		}
	}

	@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

	@Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

	@Override public void afterTextChanged(Editable s) {
		if (s.length() == mFields.get(mCurrentSelectedFieldPosition).getMaxLength()) {
			requestNextFocus();
		}
	}

	public void init() {
		for (int i = 0; i < mFields.size(); i++) {
			EditText et = mFields.get(i);
			et.setTag(i);
			et.setOnFocusChangeListener(this);
			et.addTextChangedListener(this);
		}
	}

	public void destroy() {
		mFields = null;
	}
}
