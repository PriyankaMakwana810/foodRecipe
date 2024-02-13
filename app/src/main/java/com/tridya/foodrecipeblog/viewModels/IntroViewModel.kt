package com.tridya.foodrecipeblog.viewModels

import androidx.lifecycle.ViewModel
import com.tridya.foodrecipeblog.api.repo.CommonRepository
import com.tridya.foodrecipeblog.utils.Constants
import com.tridya.foodrecipeblog.utils.PrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val repository: CommonRepository,
    @Named(Constants.SHARED_COMMON) val sharedPreferences: PrefUtils,
) : ViewModel()