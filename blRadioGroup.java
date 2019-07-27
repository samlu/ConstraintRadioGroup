package com.a0soft.gphone.base.widget.constraint;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import java.util.ArrayList;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Usage example:
 * <pre>{@code
 *   <android.support.constraint.ConstraintLayout
 *     ...
 *     <com.a0soft.gphone.base.widget.constraint.blRadioGroup
 *         android:layout_width="wrap_content"
 *         android:layout_height="wrap_content"
 *         app:constraint_referenced_ids="radioButton1, radioButton2" />
 *   </android.support.constraint.ConstraintLayout>
 * }</pre>
 */
@SuppressWarnings("unused")
public final class blRadioGroup extends ConstraintHelper {
    /**
     * <p>Interface definition for a callback to be invoked when the checked
     * radio button changed in this group.</p>
     */
    public interface OnCheckedChangeListener {
        /**
         * <p>Called when the checked radio button has changed. When the
         * selection is cleared, nCheckedId is -1.</p>
         *
         * @param rg the group in which the checked radio button has changed
         * @param nCheckedId the unique identifier of the newly checked radio button
         */
        void OnCheckedChanged(blRadioGroup rg, @IdRes int nCheckedId);
    }

    private @Nullable OnCheckedChangeListener m_listenerOnCheckedChange;

    private final @NonNull ArrayList<RadioButton> m_vRadioButtons = new ArrayList<>();
    private boolean m_bSkipCheckingViewsRecursively = false;
    private int m_nCurrentSelectedViewId = -1;
    private int m_nSelectedViewIdBeforePreLayout = -1;

    private final @NonNull CompoundButton.OnCheckedChangeListener m_listenerRadioButton =
            new CompoundButton.OnCheckedChangeListener() {
        @Override public void
        onCheckedChanged(CompoundButton vButton, boolean bIsChecked)
        {
            if (m_bSkipCheckingViewsRecursively)
                return;

            if (m_nCurrentSelectedViewId != -1) {
                // uncheck the checked button
                m_bSkipCheckingViewsRecursively = true;
                for (RadioButton v: m_vRadioButtons) {
                    if (v.getId() == m_nCurrentSelectedViewId) {
                        v.setChecked(false);
                        break;
                    }
                }
                m_bSkipCheckingViewsRecursively = false;
            }

            _SetCurrentSelectedViewId(vButton.getId());
        }
    };

    //////////////////////////////////////////////
    public
    blRadioGroup(Context ctx)
    {
        super(ctx);
    }

    public
    blRadioGroup(Context ctx, AttributeSet attrs)
    {
        super(ctx, attrs);
    }

    public
    blRadioGroup(Context ctx, AttributeSet attrs, int defStyleAttr)
    {
        super(ctx, attrs, defStyleAttr);
    }

    @Override protected void
    init(AttributeSet attrs)
    {
        super.init(attrs);

        mUseViewMeasure = false;
    }

    @Override public void
    updatePreLayout(ConstraintLayout container)
    {
        super.updatePreLayout(container);

        for (int i = 0; i < mCount; i++) {
            int nId = mIds[i];
            View v = container.getViewById(nId);
            if (v instanceof RadioButton) {
                m_vRadioButtons.add((RadioButton)v);
                ((RadioButton) v).setOnCheckedChangeListener(m_listenerRadioButton);
            }
        }

        if (m_nSelectedViewIdBeforePreLayout != -1)
            Check(m_nSelectedViewIdBeforePreLayout);
    }

    @Override public void
    updatePostLayout(ConstraintLayout container)
    {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = 0;
        params.height = 0;

        super.updatePostLayout(container);
    }

    public void
    ClearCheck()
    {
        if (m_nCurrentSelectedViewId != -1) {
            // uncheck the checked button
            m_bSkipCheckingViewsRecursively = true;
            for (RadioButton v: m_vRadioButtons) {
                if (v.getId() == m_nCurrentSelectedViewId) {
                    v.setChecked(false);
                    break;
                }
            }
            m_bSkipCheckingViewsRecursively = false;

            _SetCurrentSelectedViewId(-1);
        }
    }

    /**
     * @return selected RadioButton id, -1 if no selection
     */
    public @IdRes int
    GetCheckedRadioButtonId()
    {
        return m_nCurrentSelectedViewId;
    }

    /**
     * @param nRadioButtonId set it as current selected
     */
    public void
    Check(@IdRes int nRadioButtonId)
    {
        final boolean bIsBeforePreLayout = m_vRadioButtons.isEmpty();
        m_nSelectedViewIdBeforePreLayout = (bIsBeforePreLayout ? nRadioButtonId : -1);

        boolean bFound = false;
        m_bSkipCheckingViewsRecursively = true;
        for (RadioButton v: m_vRadioButtons) {
            if (v.getId() == nRadioButtonId) {
                v.setChecked(true);
                bFound = true;
            }
            else
                v.setChecked(false);
        }
        m_bSkipCheckingViewsRecursively = false;

        if (!bIsBeforePreLayout)
            _SetCurrentSelectedViewId(bFound ? nRadioButtonId : -1);
    }

    /**
     * <p>Register a callback to be invoked when the checked radio button
     * changes in this group.</p>
     *
     * @param listener the callback to call on checked state change
     */
    public void
    SetOnCheckedChangeListener(@Nullable OnCheckedChangeListener listener)
    {
        m_listenerOnCheckedChange = listener;
    }

    private void
    _SetCurrentSelectedViewId(@IdRes int nRadioBtnId)
    {
        if (m_nCurrentSelectedViewId != nRadioBtnId) {
            m_nCurrentSelectedViewId = nRadioBtnId;

            if (m_listenerOnCheckedChange != null)
                m_listenerOnCheckedChange.OnCheckedChanged(this, nRadioBtnId);
        }
    }
}
