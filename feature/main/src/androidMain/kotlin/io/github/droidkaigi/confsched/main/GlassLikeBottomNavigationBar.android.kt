package io.github.droidkaigi.confsched.main

import android.os.Build

actual fun isBlurSupported(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
}
