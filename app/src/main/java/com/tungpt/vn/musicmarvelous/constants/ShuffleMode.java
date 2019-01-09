package com.example.themo.musicmarvelous.constants;

import android.support.annotation.IntDef;

@IntDef({ShuffleMode.ON,
        ShuffleMode.OFF}
)
public @interface ShuffleMode {
    int OFF = 0;
    int ON = 1;
}
