package me.simple.itemdecor;

import java.util.HashSet;

class Utils {
    public static void checkFilter(FilterFun fun, HashSet<Integer> excludes) {
        if (fun != null && excludes != null) {
            throw new IllegalArgumentException("'filter' method just can be use one");
        }
    }
}
