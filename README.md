EditTexts-with-Focus-Jump
=========================

Two simple classes to implement the typical Focus-Jump for EditText fields in a form.


## What is this?

Did you ever had a form layout in your Android app? Are you jealous from that nice iOS fields that jumps the focus to the next field when user types until it's max length? Well, suffer no more!


## How to use

This is pretty straightforward:

1. **First**: you need to replace your form's **EditText**s with this custom **LengthedEditText**s.


2. **Second**: inside your **Activity**/**Fragment**, initialize an array containing your form's fields. If you use [ButterKnife](http://jakewharton.github.io/butterknife/) this is as simple and nice as:

```java
@InjectViews({R.id.view1, R.id.view2, R.id.view3, R.id.view4, R.id.view5}) 

List<LengthedEditText> fields;
```

3. **Finally**: in your layout initialization method (onCreateView, for example) create a new instance of **EditTextFocusJumperController** which basically is your fields jump controller. Finally call **destroy** in your **onDestroy** callback.

```java
mFieldsController = new EditTextFocusJumperController(fields);
mFieldsController.init();
```

```java
@Override
public void onDestroyView() {
	super.onDestroyView();
	ButterKnife.reset(this);
	mFieldsController.destroy();
}
```


Aaaand you're done!
