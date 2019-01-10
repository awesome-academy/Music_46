package com.tungpt.vn.musicmarvelous.constants;

import android.support.annotation.IntDef;

@IntDef({
        LoopMode.NO_LOOP,
        LoopMode.LOOP_ONE,
        LoopMode.LOOP_LIST}
)
public @interface LoopMode {
    int NO_LOOP = -1;
    int LOOP_ONE = 0;
    int LOOP_LIST = 1;
}
