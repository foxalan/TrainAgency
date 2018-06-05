package com.example.core.delegate.bottom;

/**
 * @author alan
 * 底部的Bean 使用字体图标
 */

public final class BottomTabBean {

    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
