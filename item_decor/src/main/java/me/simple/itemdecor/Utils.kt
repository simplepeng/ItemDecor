package me.simple.itemdecor

import java.util.*

internal object Utils {
    fun checkFilter(`fun`: FilterFun?, excludes: HashSet<Int>?) {
        require(!(`fun` != null && excludes != null)) { "'filter' method just can be use one" }
    }
}