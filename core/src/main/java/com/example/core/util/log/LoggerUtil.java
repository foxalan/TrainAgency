package com.example.core.util.log;

import android.util.Log;

/**
 * @author alan
 *         Date  2018/6/5.
 *         Function : 获取打印Log的位置
 *                          使用方法:TAG为当前类的名字
 *         Issue :
 */

public class LoggerUtil {

    @SuppressWarnings("unused")
    public static String TAG = "LogUtils";
    private static final boolean DEBUG = true;
    private static final int D = 745;
    private static final int E = 421;
    private static final int V = 674;
    private static final String CUT_OFF = "--------";
    private static final String CUT_OFF_END = "-------";
    @SuppressWarnings("unused")
    private static final String SPACE_9 = "";


    public static void d(String tag, String... values) {
        printf(D, tag, values);
    }

    public static void e(String tag, String... values) {
        printf(E, tag, values);
    }

    public static void v(String tag, String... values) {
        printf(V, tag, values);
    }

    private static void printf(int mark, String tag, String... values) {
        if (!DEBUG) {
            return;
        }

        //需要打印的内容
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            stringBuffer.append(values[i]);
            if (i == values.length - 1) {
                break;
            }
            stringBuffer.append(", ");
        }

        // 打印
        switch (mark) {
            case D:

                printfLine(D, tag);
                Log.d(tag, stringBuffer.toString());
                Log.d(tag, CUT_OFF_END);

                break;
            case E:
                printfLine(E, tag);

                Log.e(tag, stringBuffer.toString());
                Log.e(tag, CUT_OFF_END);

                break;
            case V:
                printfLine(V, tag);
                Log.v(tag,  stringBuffer.toString());
                Log.v(tag, CUT_OFF_END);

                break;
            default:
                break;
        }


    }

    private static String getPosition(String tag) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement element = getTargetStack(tag);

        if (null == element) {
            return null;
        }

        sb.append("(")
                .append(element.getFileName())
                .append(":")
                .append(element.getLineNumber())
                .append(")");
        Log.e("api_host",sb.toString());
        return sb.toString();
    }

    private static void printfLine(int mark, String tag) {
        String startLine = CUT_OFF + getPosition(tag) + CUT_OFF;

        switch (mark) {
            case D:
                Log.d(tag, startLine);
                break;
            case E:
                Log.e(tag, startLine);
                break;
            case V:
                Log.v(tag, startLine);
                break;
            default:
                break;
        }


    }

    /**
     * 获取最后调用我们log的StackTraceElement
     *
     * @param tag 目标类的SimpleName
     * @return
     */

    private static StackTraceElement getTargetStack(String tag) {
//        Log.e("api_host","getTargetStack"+Thread.currentThread().getStackTrace().length);
//        Log.e("api_host",tag);
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {

            if (element.getClassName().contains(tag)) {
                //返回调用位置的 element
                Log.e("api_host","true");
                return element;
            }
        }
        return null;
    }


}
