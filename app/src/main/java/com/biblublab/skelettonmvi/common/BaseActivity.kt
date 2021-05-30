package com.biblublab.skelettonmvi.common

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<STATE, EFFECT, EVENT, ViewModel : BaseViewModel<STATE, EFFECT, EVENT>, B : ViewBinding>(private val bindingFactory  : (LayoutInflater) -> B) : AppCompatActivity(){

    abstract val viewModel : ViewModel
    protected lateinit var binding : B

    private val viewStateObserver = Observer<STATE> {
        Log.d("TAG", "observed viewState : $it")
        renderViewState(it)
    }

    private val viewEffectObserver = Observer<EFFECT> {
        Log.d("TAG", "observed viewEffect : $it")
        renderViewEffect(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        viewModel.viewStates().observe(this, viewStateObserver)
        viewModel.viewEffects().observe(this, viewEffectObserver)
    }

    abstract fun renderViewState(viewState: STATE)

    abstract fun renderViewEffect(viewEffect: EFFECT)
}