package me.zipstream.todo.util;

import android.content.Context;
import android.content.res.TypedArray;

import me.zipstream.todo.R;

public class Utils {

    public static int getToolbarHeight(Context context) {
        final TypedArray styleAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styleAttributes.getDimension(0, 0);
        styleAttributes.recycle();

        return toolbarHeight;
    }
}
