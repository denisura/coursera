package com.github.denisura.coursera.ui.catalog.pagination;

import rx.Observable;

public interface PagingListener<T> {
    Observable<T> onNextPage(int offset);
}