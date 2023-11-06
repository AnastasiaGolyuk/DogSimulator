package test.createx.dogsimulator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    private val _homeItemClickedLD = MutableLiveData<Unit>()
    val homeItemClickedLD: LiveData<Unit> get() = _homeItemClickedLD

    private val _eventsItemClickedLD = MutableLiveData<Unit>()
    val eventsItemClickedLD: LiveData<Unit> get() = _eventsItemClickedLD

    private val _libraryItemClickedLD = MutableLiveData<Unit>()
    val libraryItemClickedLD: LiveData<Unit> get() = _libraryItemClickedLD



//    fun onBottomItemChanged(position: Int) {
//        when(values()[position]) {
//            BottomNavBarTabs.TAB_TRANSLATOR -> _homeItemClickedLD.execute()
//            TAB_EVENTS -> _eventsItemClickedLD.execute()
//            TAB_LIBRARY -> _libraryItemClickedLD.execute()
//        }
//    }
}