package com.example.core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.core.app.Latte;

/**
 *
 * @author alan
 * 获取屏幕的宽和高
 */

public final class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
