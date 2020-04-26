package com.github.roroche.eoandroidui

import android.os.Bundle

/**
 * Interface describing extra parameters for navigation.
 */
interface Extra {
    /**
     * Convert extra parameters to [Bundle].
     */
    fun toBundle(): Bundle
}
