package ch.liip.viewmodelsavedstatehelpers.demo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ch.liip.viewmodelsavedstatehelpers.delegate
import ch.liip.viewmodelsavedstatehelpers.livedata

class MainViewModel(handle: SavedStateHandle) : ViewModel() {
    // Simple string that is saved in the SavedState
    var manualText by handle.delegate<String?>()

    // MutableLiveData that is saved in the SavedState
    val liveDataText by handle.livedata<String?>()

    // Could be used with a default value
    // val liveDataText by handle.livedata<String?>(initialValue = "Hello")
}
