package com.example.ui.animator;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ui.R;

import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author alan
 *         Date  2018/7/11.
 *         Function : Fragment的转场动画
 *         Issue :
 */

public class LatteAnimator extends FragmentAnimator implements Parcelable {


    public LatteAnimator() {
        enter = R.anim.push_bottom_in;
        exit = R.anim.push_bottom_out;
        popEnter = R.anim.push_bottom_in;
        popExit = R.anim.push_bottom_out;
    }

    protected LatteAnimator(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LatteAnimator> CREATOR = new Creator<LatteAnimator>() {
        @Override
        public LatteAnimator createFromParcel(Parcel in) {
            return new LatteAnimator(in);
        }

        @Override
        public LatteAnimator[] newArray(int size) {
            return new LatteAnimator[size];
        }
    };
}
