package me.simple.itemdecor;

public interface IFilter<T> {
    T filter(FilterFunc func);
}
