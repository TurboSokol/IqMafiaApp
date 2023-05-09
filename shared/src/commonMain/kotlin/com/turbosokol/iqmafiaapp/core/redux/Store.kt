package com.turbosokol.iqmafiaapp.core.redux

import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.app.RootReducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

interface GeneralState
interface Action
interface Effect
object None : Action
object Empty : Effect

interface Store<S : GeneralState, A : Action, E : Effect> {
    fun observeState(): StateFlow<S>
    fun dispatch(action: A)
    fun getState(): S
    fun observeSideEffect(): Flow<E>
}

class ReduxStore(
    private val reducer: RootReducer,
    defaultValue: AppState,
    private val middlewares: List<Middleware<AppState>>
): Store<AppState, Action, Effect>, CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val state = MutableStateFlow(defaultValue)
    private val sideEffect = MutableSharedFlow<Effect>()

    override fun observeState(): StateFlow<AppState> = state.asStateFlow()

    override fun dispatch(action: Action) {
        val oldState = state.value
        val newState = reducer.reduce(oldState, action)

        middlewares.forEach { middleware ->
            launch {
                middleware.execute(newState, action, sideEffect).collect { middlewareAction ->
                    dispatch(middlewareAction)
                }
            }
        }

        if (newState != oldState) state.value = newState
    }

    override fun getState(): AppState = state.value

    override fun observeSideEffect(): Flow<Effect> = sideEffect
}