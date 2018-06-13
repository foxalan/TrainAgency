package com.example.ec.main.home.message.list;

import android.support.annotation.IdRes;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.core.delegate.LatteDelegate;


/**
 * @author alan
 * 消息数据
 */

public class MessageBean implements MultiItemEntity {

    private int mItemType = 0;
    @IdRes
    private int  mImageUrl;
    private int mCount;
    private String mText = null;
    private MessageType mValue = null;
    private int mId = 0;
    private LatteDelegate mDelegate = null;
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;


    public MessageBean(int mCount,int mItemType, int  mImageUrl, String mText, MessageType mValue, int mId, LatteDelegate mDelegate, CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener) {
        this.mCount = mCount;
        this.mItemType = mItemType;
        this.mImageUrl = mImageUrl;
        this.mText = mText;
        this.mValue = mValue;
        this.mId = mId;
        this.mDelegate = mDelegate;
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }

    public int getImageUrl() {
        return mImageUrl;
    }


    public int getCount() {
        return mCount;
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
    }

    public String getText() {
        if (mText == null) {
            return "";
        }

        return mText;
    }

    public MessageType getValue() {
        if (mValue == null) {
            return MessageType.READ;
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

        private int mCount;
        private int id = 0;
        private int itemType = 0;
        private int  imageUrl;
        private String text = null;
        private MessageType value = null;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = null;
        private LatteDelegate delegate = null;

        public Builder setCount(int count){
            this.mCount = count;
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

        public Builder setImageUrl(@IdRes int  imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(MessageType value) {
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

        public MessageBean build() {
            return new MessageBean(mCount,itemType, imageUrl, text, value, id, delegate, onCheckedChangeListener);
        }
    }
}
