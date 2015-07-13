/*
 * Copyright 2015 The Mashub Project
 *
 * The Mashub Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.beatsbucket.mashub.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class ListDecorator<T> implements List<T> {
    protected final List<T> decoratedList;

    protected ListDecorator(List<T> decoratedList) {
        this.decoratedList = decoratedList;
    }

    @Override
    public T get(int index) {
        return decoratedList.get(index);
    }

    @Override
    public int size() {
        return decoratedList.size();
    }

    @Override
    public boolean isEmpty() {
        return decoratedList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return decoratedList.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return decoratedList.iterator();
    }

    @Override
    public boolean add(T t) {
        return decoratedList.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return decoratedList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return decoratedList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return decoratedList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return decoratedList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return decoratedList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return decoratedList.retainAll(c);
    }

    @Override
    public void clear() {
        decoratedList.clear();
    }

    @Override
    public T set(int index, T element) {
        return decoratedList.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        decoratedList.add(index, element);
    }

    @Override
    public T remove(int index) {
        return decoratedList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return decoratedList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return decoratedList.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return decoratedList.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return decoratedList.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return decoratedList.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return decoratedList.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return decoratedList.toArray(a);
    }
}
