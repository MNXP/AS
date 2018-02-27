package com.xp.mymvp.Tool.View;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xp.mymvc.R;

/**
 * 可复用标题栏View
 */
public class TitleLayout extends RelativeLayout {


    private Context context;
    /**
     * 标题栏返回按钮
     */
    private ImageButton backButton;

    /**
     * 标题栏标题文字
     */
    private TextView titleText;
    private TextView rightTextTv;
    private View viewBar;
    /**
     * 右上角图片
     */
    private ImageButton imageButton;


    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(final Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.title_bar, this, true);
        String title = null;
        String titleRight = null;
        this.context = context;
        boolean showBackButton = true;
        boolean showRightText = false;
        boolean showRightBtn = false;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleLayout);
        if (typedArray != null) {
            title = typedArray.getString(R.styleable.TitleLayout_title_layout_title);
            titleRight = typedArray.getString(R.styleable.TitleLayout_title_layout_right_title);

            showBackButton = typedArray.getBoolean(R.styleable.TitleLayout_title_layout_show_back, true);
            showRightText = typedArray.getBoolean(R.styleable.TitleLayout_title_layout_show_right, false);
            showRightBtn = typedArray.getBoolean(R.styleable.TitleLayout_title_layout_show_right_btn, false);

        }

        if (view != null) {
            backButton = (ImageButton) view.findViewById(R.id.btn_back);
            imageButton = (ImageButton) view.findViewById(R.id.iv_btn);
            titleText = (TextView) view.findViewById(R.id.tv_title);
            rightTextTv = (TextView) view.findViewById(R.id.tv_right);
            viewBar = view.findViewById(R.id.view_bar);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                viewBar.setVisibility(VISIBLE);
            } else {
                viewBar.setVisibility(GONE);
            }
            if (title != null) {
                setTitle(title);
            }
            if (titleRight != null) {
                setRightText(titleRight);
            }
            setBackButtonVisibility(showBackButton, showRightText, showRightBtn);


            //为标题栏后退按钮绑定默认监听事件
            //监听事件默认调用当前Activity 的onBackPressed 方法
            setBackOnClickListener(null);
//            backButton.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (context instanceof Activity) {
//
//                        Activity activity = (Activity) context;
//                        hideSoftInput();
//                        activity.setResult(201);
//                        activity.onBackPressed();
//                    }
//                }
//            });
        }
        if (typedArray != null) {
            typedArray.recycle();
        }
    }

    public void hideSoftInput() {
        // **隐藏软键盘**/
        View v = ((Activity) context).getWindow().peekDecorView();
        if (v != null) {
            InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * 设置标题栏标题文字
     *
     * @param title 标题文字
     */
    public void setTitle(CharSequence title) {

        if (title != null) {
            titleText.setText(title);
        }
    }

    /**
     * 设置右侧文字
     *
     * @param rightText
     */
    public void setRightText(String rightText) {
        if (rightTextTv != null) {
            rightTextTv.setText(rightText);
        }
    }

    /**
     * 设置右侧图片
     *
     * @param imageId
     */
    public void setRightImage(int imageId) {
        if (imageButton != null) {
            imageButton.setImageResource(imageId);
        }
    }

    /**
     * 设置右侧图片是否显示
     *
     * @param b true 显示 false 隐藏
     */
    public void setRightImageVisible(boolean b) {
        imageButton.setVisibility(b ? VISIBLE : GONE);
    }

    /**
     * 设置是否显示标题栏后退按钮，默认为显示
     *
     * @param backVisible 是否显示标题栏后退按钮
     */
    public void setBackButtonVisibility(boolean backVisible, boolean rightVisible, boolean rightImage) {
        if (backVisible) {
            backButton.setVisibility(VISIBLE);
        } else {
            backButton.setVisibility(GONE);
        }

        if (rightVisible) {
            rightTextTv.setVisibility(VISIBLE);
        } else {
            rightTextTv.setVisibility(GONE);
        }
        if (rightImage) {
            imageButton.setVisibility(VISIBLE);
        } else {
            imageButton.setVisibility(GONE);
        }

    }

    public void setRightOnClickListener(OnClickListener onRightClickListener) {
        if (onRightClickListener != null) {
            rightTextTv.setOnClickListener(onRightClickListener);
        }
    }

    public void setRightImageOnClickListener(OnClickListener onRightImageClickListener) {
        if (onRightImageClickListener != null) {
            imageButton.setOnClickListener(onRightImageClickListener);
        }
    }

    public void setBackOnClickListener(OnClickListener onClickListener) {
        if (onClickListener != null) {
            backButton.setOnClickListener(onClickListener);
        } else {
            backButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (context instanceof Activity) {
                        Activity activity = (Activity) context;
                        hideSoftInput();
                        activity.setResult(201);
                        activity.onBackPressed();
                    }
                }
            });

        }
    }


    public void showView(int resId, int visibility) {
        View view = findViewById(resId);
        view.setVisibility(visibility);
    }


}
