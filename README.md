# ConstraintRadioGroup
A RadioGroup widget that can be used with ConstraintLayout

## Getting started
Copy the `blRadioGroup.java` file to your source set and change the package name.

## Usage
To group `RadioButton` widgets in a `ConstraintLayout`, you can add a `blRadioGroup` tag to the layout file and use `app:constraint_referenced_ids` to specify RadioButton ids that will be managed by this radio group widget. 

Adding multiple `blRadioGroup` widgets to a `ConstraintLayout` is supported.

An example:

```xml
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

In your java source file, you can use the following `blRadioGroup` APIs:
```java
ClearCheck(), Check(int), GetCheckedRadioButtonId(), SetOnCheckedChangeListener(OnCheckedChangeListener)
```

For example,
```java
blRadioGroup rg = findViewById(R.id.group);
// set btn2 RadioButton as checked, btn1 will auto be unchecked
rg.Check(R.id.btn2);

// get current checked button id
int nCheckedBtnId = rg.GetCheckedRadioButtonId();

// monitor the change of checked button
rg.SetOnCheckedChangeListener(new blRadioGroup.OnCheckedChangeListener() {
  @Override public void 
  OnCheckedChanged(blRadioGroup rg, @IdRes int nCheckedId)
  {
  }
});
```

## License
    Copyright (C) 2019 Sam Lu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
