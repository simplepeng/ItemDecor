package me.simple.itemdecor;

import androidx.annotation.Nullable;

public interface Linker {
    @Nullable
    AbsItemDecor bind(int position);
}
