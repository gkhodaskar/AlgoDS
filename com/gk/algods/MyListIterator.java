package com.gk.algods;
import java.util.Iterator;
import java.util.List;

public class MyListIterator <T> implements Iterator<T>{

    private List<T> list = null;
    private int index;

    MyListIterator(List<T> list){
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.list.size();
    }

    @Override
    public T next() {
        return this.list.get(this.index++);
    }
}
