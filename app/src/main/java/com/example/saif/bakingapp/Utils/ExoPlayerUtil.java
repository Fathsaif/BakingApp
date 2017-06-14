package com.example.saif.bakingapp.Utils;

import java.util.Formatter;
import java.util.Locale;

/**
 * Created by Saif on 06/06/2017.
 */

public class ExoPlayerUtil {

    private String stringForTime(int timeMs,Formatter mFormatter,StringBuilder mFormatBuilder) {
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        int totalSeconds =  timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours   = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

}
