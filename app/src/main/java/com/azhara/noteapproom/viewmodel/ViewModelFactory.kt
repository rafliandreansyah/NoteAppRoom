package com.azhara.noteapproom.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azhara.noteapproom.ui.insert.NoteAddUpdateViewModel
import com.azhara.noteapproom.ui.main.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory private constructor(private val mApplication: Application): ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory{
            if(INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    if(INSTANCE == null){
                        INSTANCE = ViewModelFactory(application)
                    }
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(mApplication) as T
        }else if(modelClass.isAssignableFrom(NoteAddUpdateViewModel::class.java)){
            return NoteAddUpdateViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

}