package cn.efarm360.com.dabaomvp.weigt;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import cn.efarm360.com.dabaomvp.R;

public class SegmentedGroup extends RadioGroup {

    private int mMarginDp;
    private Resources resources;

    private LayoutSelector mLayoutSelector;
    /**
     * SegmentedGroup圆角角度
     */
    private Float mCornerRadius;

    /**
     * 被选中的RadioButton文本的颜色，默认白色
     */
    private int mCheckedTextColor = Color.WHITE;
    /**
     * SegmentedGroup边线的默认宽度
     */
    private final int radio_button_stroke_border = R.dimen.dp_2;
    /**
     * SegmentedGroup默认的圆角角度
     */
    private final int radio_button_corner_radius = R.dimen.dp_4;
    /**
     * 被选中RadioButton的默认背景色
     */
    private final int radio_button_selected_color = R.color.white;
    /**
     * 未被选中的文本的颜色
     */
    private int mTintColor;
    /**
     * SegmentedGroup边线颜色
     */
    private int mStrokeColor;
    /**
     * RadioButton被选中的背景色
     */
    private int mCheckColor;

    public SegmentedGroup(Context context) {
        super(context);
        init();
        mLayoutSelector = new LayoutSelector(mCornerRadius);
    }

    /* Reads the attributes from the layout */
    public SegmentedGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttrs(attrs);
        mLayoutSelector = new LayoutSelector(mCornerRadius);
    }

    private void init() {
        resources = getResources();
        mTintColor = resources.getColor(radio_button_selected_color);
        mStrokeColor = mTintColor;
        mCheckColor = mTintColor;
        mMarginDp = (int) getResources().getDimension(radio_button_stroke_border);
        mCornerRadius = getResources().getDimension(radio_button_corner_radius);
    }

    public void setmMarginDp(int dp) {
        mMarginDp = dp;
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.SegmentedGroup, 0, 0);
        try {
            mMarginDp = (int) typedArray.getDimension(
                    R.styleable.SegmentedGroup_sc_border_width,
                    getResources().getDimension(radio_button_stroke_border));

            mCornerRadius = typedArray.getDimension(
                    R.styleable.SegmentedGroup_sc_corner_radius,
                    getResources().getDimension(radio_button_corner_radius));

            mTintColor = typedArray.getColor(
                    R.styleable.SegmentedGroup_sc_tint_color,
                    getResources().getColor(radio_button_selected_color));
            mCheckedTextColor = typedArray.getColor(
                    R.styleable.SegmentedGroup_sc_checked_text_color,
                    getResources().getColor(android.R.color.white));

        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //Use holo light for default
        updateBackground();
    }

    public void setStrokeColor(int mStrokeColor) {
        this.mStrokeColor = mStrokeColor;
    }

    public void setTintColor(int tintColor) {
        mTintColor = tintColor;
        updateBackground();
    }

    public void setTintColor(int tintColor, int checkedTextColor) {
        mTintColor = tintColor;
        mCheckedTextColor = checkedTextColor;
        updateBackground();
    }

    public void setCheckedTextColor(int mCheckedTextColor) {
        this.mCheckedTextColor = mCheckedTextColor;
    }

    public void setCheckColor(int mCheckColor) {
        this.mCheckColor = mCheckColor;
    }

    public void updateBackground() {
        int count = super.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            updateBackground(child);
            // If this is the last view, don't set LayoutParams
            if (i == count - 1) break;

            LayoutParams initParams = (LayoutParams) child.getLayoutParams();
            LayoutParams params = new LayoutParams(initParams.width, initParams.height, initParams.weight);
            // Check orientation for proper margins
            if (getOrientation() == LinearLayout.HORIZONTAL) {
                params.setMargins(0, 0, -mMarginDp, 0);
            } else {
                params.setMargins(0, 0, 0, -mMarginDp);
            }
            child.setLayoutParams(params);
        }
    }

    private void updateBackground(View view) {
        int checked = mLayoutSelector.getSelected();
        int unchecked = mLayoutSelector.getUnselected();
        //Set text color
        ColorStateList colorStateList = new ColorStateList(new int[][]{
                {android.R.attr.state_pressed},
                {-android.R.attr.state_pressed, -android.R.attr.state_checked},
                {-android.R.attr.state_pressed, android.R.attr.state_checked}},
                new int[]{Color.GRAY, mTintColor, mCheckedTextColor});
        ((Button) view).setTextColor(colorStateList);

        //Redraw with tint color
        Drawable checkedDrawable = resources.getDrawable(checked).mutate();
        Drawable uncheckedDrawable = resources.getDrawable(unchecked).mutate();
        ((GradientDrawable) checkedDrawable).setColor(mCheckColor);

        ((GradientDrawable) checkedDrawable).setStroke(mMarginDp, mStrokeColor);
        ((GradientDrawable) uncheckedDrawable).setStroke(mMarginDp, mStrokeColor);

        //Set proper radius
        ((GradientDrawable) checkedDrawable).setCornerRadii(mLayoutSelector.getChildRadii(view));
        ((GradientDrawable) uncheckedDrawable).setCornerRadii(mLayoutSelector.getChildRadii(view));

        //Create drawable
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-android.R.attr.state_checked}, uncheckedDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, checkedDrawable);

        //Set button background
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(stateListDrawable);
        } else {
            view.setBackgroundDrawable(stateListDrawable);
        }
    }

    /*
     * This class is used to provide the proper layout based on the view.
     * Also provides the proper radius for corners.
     * The layout is the same for each selected left/top middle or right/bottom button.
     * float tables for setting the radius via Gradient.setCornerRadii are used instead
     * of multiple xml drawables.
     */
    private class LayoutSelector {

        private int children;
        private int child;
        private final int SELECTED_LAYOUT = R.drawable.radio_checked;
        private final int UNSELECTED_LAYOUT = R.drawable.radio_unchecked;

        private float r;    //this is the radios read by attributes or xml dimens
        private final float r1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
                , 0.1f, getResources().getDisplayMetrics());    //0.1 dp to px
        private final float[] rLeft;    // left radio button
        private final float[] rRight;   // right radio button
        private final float[] rMiddle;  // middle radio button
        private final float[] rDefault; // default radio button
        private final float[] rTop;     // top radio button
        private final float[] rBot;     // bot radio button
        private float[] radii;          // result radii float table

        public LayoutSelector(float cornerRadius) {
            children = -1; // Init this to force setChildRadii() to enter for the first time.
            child = -1; // Init this to force setChildRadii() to enter for the first time
            r = cornerRadius;
            rLeft = new float[]{r, r, r1, r1, r1, r1, r, r};
            rRight = new float[]{r1, r1, r, r, r, r, r1, r1};
            rMiddle = new float[]{r1, r1, r1, r1, r1, r1, r1, r1};
            rDefault = new float[]{r, r, r, r, r, r, r, r};
            rTop = new float[]{r, r, r, r, r1, r1, r1, r1};
            rBot = new float[]{r1, r1, r1, r1, r, r, r, r};
        }

        private int getChildren() {
            return SegmentedGroup.this.getChildCount();
        }

        private int getChildIndex(View view) {
            return SegmentedGroup.this.indexOfChild(view);
        }

        private void setChildRadii(int newChildren, int newChild) {

            // If same values are passed, just return. No need to update anything
            if (children == newChildren && child == newChild)
                return;

            // Set the new values
            children = newChildren;
            child = newChild;

            // if there is only one child provide the default radio button
            if (children == 1) {
                radii = rDefault;
            } else if (child == 0) { //left or top
                radii = (getOrientation() == LinearLayout.HORIZONTAL) ? rLeft : rTop;
            } else if (child == children - 1) {  //right or bottom
                radii = (getOrientation() == LinearLayout.HORIZONTAL) ? rRight : rBot;
            } else {  //middle
                radii = rMiddle;
            }
        }

        /* Returns the selected layout id based on view */
        public int getSelected() {
            return SELECTED_LAYOUT;
        }

        /* Returns the unselected layout id based on view */
        public int getUnselected() {
            return UNSELECTED_LAYOUT;
        }

        /* Returns the radii float table based on view for Gradient.setRadii()*/
        public float[] getChildRadii(View view) {
            int newChildren = getChildren();
            int newChild = getChildIndex(view);
            setChildRadii(newChildren, newChild);
            return radii;
        }
    }
}