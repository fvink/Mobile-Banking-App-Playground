package com.fvink.mobilebanking.util

import timber.log.Timber

inline fun <reified T> safeExecute(
    operation: () -> T
): Result<T> {
    return try {
        Result.success(operation())
    } catch (e: Throwable) {
        Timber.e(e)
        Result.failure(e)
    }
}