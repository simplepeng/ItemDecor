package me.simple.itemdecor;

public interface IFilter<T> {
    T filter(FilterFun func);

    T filter(int... excludes);
}
