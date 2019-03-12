package id.sheendev.qrbarcodescanner.app.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import id.sheendev.qrbarcodescanner.R;


/**
 * Created by Rizki Maulana on 11/30/17.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public class SingleMenuLayout extends LinearLayout {

    private TextView mTxtTitle, mTxtSubtitle;
    private String mStrTitle, mStrSubtitle;
    private ImageView mImgIcon;
    private int       icon;
    private boolean   showIcon;

    public SingleMenuLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View           view     = inflater.inflate(R.layout.row_single_menu, this);

        mTxtTitle =  view.findViewById(R.id.m_txt_title);
        mTxtSubtitle =  view.findViewById(R.id.m_txt_subtitle);
        mImgIcon =  view.findViewById(R.id.m_icon);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SingleMenuLayout,
                0, 0);

        try {
            mStrTitle = a.getString(R.styleable.SingleMenuLayout_sm_title);
            mStrSubtitle = a.getString(R.styleable.SingleMenuLayout_sm_subtitle);
            showIcon = a.getBoolean(R.styleable.SingleMenuLayout_sm_showIcon, false);
            Drawable drawable = a.getDrawable(R.styleable.SingleMenuLayout_sm_setIcon);

            mTxtTitle.setText(mStrTitle);
            mTxtSubtitle.setText(mStrSubtitle);
            if (showIcon) {
                mImgIcon.setVisibility(View.VISIBLE);
                if (drawable != null)
                    mImgIcon.setImageDrawable(drawable);
            } else {
                mImgIcon.setVisibility(View.GONE);
            }
        } finally {
            a.recycle();
        }
    }

    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }

    public void setSubtitle(String subtitle) {
        mTxtSubtitle.setText(subtitle);
    }
}
