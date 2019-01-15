package com.tungpt.vn.musicmarvelous.constants;

import android.support.annotation.IntDef;

@IntDef({
        TabEntity.TAB_HOME,
        TabEntity.TAB_LIBRARY,
        TabEntity.TAB_DOWNLOAD,}
)

public @interface TabEntity {
    int TAB_HOME = 0;
    int TAB_LIBRARY = 1;
    int TAB_DOWNLOAD = 2;
}
