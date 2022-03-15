package com.fork.spoonfeed.presentation.util

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class LinearLayoutManagerWrapper : LinearLayoutManager {
    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout) {}

    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }
}