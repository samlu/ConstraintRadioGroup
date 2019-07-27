# ConstraintRadioGroup
A RadioGroup that you can use with ConstraintLayout

## Getting started
Copy the blRadioGroup.java to your source set and change the package name.

## How to use
Add blRadioGroup in your layout file to group RadioButtons. For example:

```java
<android.support.constraint.ConstraintLayout
  ...
  <RadioButton
      android:id="@+id/btn1"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"/>
  <RadioButton
      android:id="@+id/btn2"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"/>
  <com.a0soft.gphone.base.widget.constraint.blRadioGroup
      android:id="@+id/group"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      app:constraint_referenced_ids="btn1, btn2" />
</android.support.constraint.ConstraintLayout>
```  

In your java source file, you can use the following blRadioGroup APIs:
```java
ClearCheck(), Check(int), GetCheckedRadioButtonId(), SetOnCheckedChangeListener(OnCheckedChangeListener)
```

For example,
```java
blRadioGroup rg = findViewById(R.id.group);
// set btn2 RadioButton as checked
rg.Check(R.id.btn2);

// get current checked button id
int nCheckedBtnId = rg.GetCheckedRadioButtonId();

// monitor the checked button changes
rg.SetOnCheckedChangeListener(new OnCheckedChangeListener() {
  @Override public void 
  OnCheckedChanged(blRadioGroup rg, @IdRes int nCheckedId)
  {
  }
});
```
