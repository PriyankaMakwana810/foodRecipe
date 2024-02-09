package com.tridya.foodrecipeblog.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tridya.foodrecipeblog.api.repo.NotificationRepository
import com.tridya.foodrecipeblog.database.tables.Notifications
import com.tridya.foodrecipeblog.utils.Constants
import com.tridya.foodrecipeblog.utils.PrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val repository: NotificationRepository,
    @Named(Constants.SHARED_COMMON) val sharedPreferences: PrefUtils,
) : ViewModel() {

    private var _allNotifications =
        MutableStateFlow<List<Notifications>>(emptyList())
    val allNotifications: StateFlow<List<Notifications>> = _allNotifications

    private var _readNotifications =
        MutableStateFlow<List<Notifications>>(emptyList())
    val readNotifications: StateFlow<List<Notifications>> = _readNotifications

    private var _unreadNotifications =
        MutableStateFlow<List<Notifications>>(emptyList())
    val unreadNotifications: StateFlow<List<Notifications>> = _unreadNotifications


    init {
        getAllNotifications()
        getReadNotifications()
        getUnreadNotifications()
    }

    fun updateNotificationState(isRead: Boolean, id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateNotificationState(isRead, id)
        }
    }

    fun getAllNotifications() {
        try {
            viewModelScope.launch {
                repository.getAllNotifications.collect {
                    _allNotifications.value = it
                }
            }
        } catch (e: Exception) {
            _allNotifications.value = emptyList()
        }
    }

    fun getReadNotifications() {
        try {
            viewModelScope.launch {
                repository.getReadNotifications.collect {
                    _readNotifications.value = it
                }
            }
        } catch (e: Exception) {
            _readNotifications.value = emptyList()
        }
    }

    fun getUnreadNotifications() {
        try {
            viewModelScope.launch {
                repository.getUnreadNotifications.collect {
                    _unreadNotifications.value = it
                }
            }
        } catch (e: Exception) {
            _unreadNotifications.value = emptyList()
        }
    }

}