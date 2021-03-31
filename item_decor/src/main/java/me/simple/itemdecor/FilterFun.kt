package me.simple.itemdecor

interface FilterFun {
    fun exclude(position: Int): Boolean
}