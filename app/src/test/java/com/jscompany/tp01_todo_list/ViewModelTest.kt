package com.jscompany.tp01_todo_list

import android.app.Application
import androidx.lifecycle.LiveData
import com.jscompany.tp01_todo_list.di.appTestModule
import com.jscompany.tp01_todo_list.livedata.LiveDataTestObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@OptIn(ExperimentalCoroutinesApi::class)
internal class viewModelTest:KoinTest {

    @get:Rule
    val mokittoRule:MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var context : Application

    private val disparcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        startKoin {
            androidContext(context)
            modules(appTestModule)
        }
        Dispatchers.setMain(disparcher)
    }

    @After
    fun tearDown() {
        startKoin {  }
        Dispatchers.resetMain() // 메인 디스페쳐를 초기화 해줘야 메모리 누수가 발생하지 않음
    }

    //라이브데이터 비슷하게 동작하는 유틸리티 등록
    protected fun <T> LiveData<T>.test(): LiveDataTestObserver<T> {
        val testObserver = LiveDataTestObserver<T>()
        observeForever(testObserver)
        return testObserver
    }

}