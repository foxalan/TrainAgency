package com.example.ec.main.personal.list;

import android.os.Bundle;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.core.delegate.LatteDelegate;


/**
 * @author alan
 *
 */

public class ListBean implements MultiItemEntity {

    private int mItemType = 0;
    private int mImageUrl;
    private String mUrl;

    public int getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(int mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public String getmValue() {
        return mValue;
    }

    public void setmValue(String mValue) {
        this.mValue = mValue;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    private String mText = null;
    private String mValue = null;
    private boolean isCheck = false;
    private int mId = 0;
    private LatteDelegate mDelegate = null;
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public ListBean(String mUrl, int mItemType, int mImageUrl, String mText, String mValue, int mId, LatteDelegate mDelegate, CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener, boolean isCheck) {
        this.mUrl = mUrl;
        this.mItemType = mItemType;
        this.mImageUrl = mImageUrl;
        this.mText = mText;
        this.mValue = mValue;
        this.mId = mId;
        this.mDelegate = mDelegate;
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;

        this.isCheck = isCheck;
    }

    public int  getImageUrl() {
        return mImageUrl;
    }

    public String getText() {
        if (mText == null) {
            return "";
        }
        return mText;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getValue() {
        if (mValue == null) {
            return "";
        }
        return mValue;

    }

    public int getId() {
        return mId;
    }

    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    public CompoundButton.OnCheckedChangeListener getmOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public static final class Builder {
        private String url;
        private int id = 0;
        private int itemType = 0;
        private int  imageUrl;
        private boolean isCheck = false;
        private String text = null;
        private String value = null;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = null;
        private LatteDelegate delegate = null;

        public Builder setUrl(String url){
            this.url = url;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder isChecked(boolean isCheck){
            this.isCheck = isCheck;
            return this;
        }

        public Builder setImageUrl(int  imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public Builder setDelegate(LatteDelegate delegate) {
            this.delegate = delegate;
            return this;
        }

        public ListBean build() {
            return new ListBean(url,itemType, imageUrl, text, value, id, delegate, onCheckedChangeListener,isCheck);
        }
    }
}
