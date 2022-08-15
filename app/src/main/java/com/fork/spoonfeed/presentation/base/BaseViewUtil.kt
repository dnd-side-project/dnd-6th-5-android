package com.fork.spoonfeed.presentation.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.fork.spoonfeed.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import timber.log.Timber


sealed class BaseViewUtil {
    abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes val layout: Int) : Fragment() {
        private var _binding: T? = null
        protected val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = DataBindingUtil.inflate(inflater, layout, container, false)
            return binding.root
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

        abstract fun initView()

        protected inner class LifeCycleEventLogger(private val className: String) : LifecycleObserver {
            fun registerLogger(lifecycle: Lifecycle) {
                lifecycle.addObserver(this)
            }

            fun log() {
                Timber.d("${className}LifeCycleEvent", "${lifecycle.currentState}")
            }
        }
    }

    abstract class BaseAppCompatActivity<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) :
        AppCompatActivity() {
        protected lateinit var binding: T

        @SuppressLint("SourceLockedOrientationActivity")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            binding = DataBindingUtil.setContentView(this, layoutRes)
        }

        abstract fun initView()

        protected inner class LifeCycleEventLogger(private val className: String) : LifecycleObserver {
            fun registerLogger(lifecycle: Lifecycle) {
                lifecycle.addObserver(this)
            }

            fun log() {
                Timber.d("${className}LifeCycleEvent", "${lifecycle.currentState}")
            }
        }
    }

    abstract class BaseCategoryBottomDialogFragment<T : ViewDataBinding>(@LayoutRes val layout: Int) :
        BottomSheetDialogFragment() {
        private var _binding: T? = null
        protected val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = DataBindingUtil.inflate(inflater, layout, container, false)
            return binding.root
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return BottomSheetDialog(requireContext(), R.style.BottomDialog)
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

        abstract fun initView()

        companion object {
            const val ALL = "전체"
            const val DWELLING = "주거"
            const val FINANCE = "금융"
        }
    }
}
