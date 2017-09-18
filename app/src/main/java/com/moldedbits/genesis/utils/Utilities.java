package com.moldedbits.genesis.utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class Utilities {

    public static final String CATEGORY_KEY = "categoryKey";
    public static final String CATEGORY_NAME = "categoryName";
    public static final String PASSAGE_INDEX = "passageIndex";
    public static final String FB_PASSAGE_DETAILS = "passageDetails";

    public static float convertDpToPx(int pixel, Resources res) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixel,
                res.getDisplayMetrics());
    }
}
