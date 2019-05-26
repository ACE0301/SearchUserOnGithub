package com.ace.aleksandr.searchuserongithub.base

import io.reactivex.disposables.Disposable

fun Disposable?.disposeIfNotNull() {
    if (this != null && !isDisposed) {
        dispose()
    }
}