package com.example.mviexample

import android.util.Log
import com.example.mviexample.redux.Action
import com.example.mviexample.redux.Middleware
import com.example.mviexample.redux.State
import com.example.mviexample.redux.Store

/**
 * This [Middleware] is responsible for logging every [Action] that is processed to the Logcat, so that we can see the flow of our data for debugging purposes.
 */
class LoggingMiddleware<S : State, A : Action> : Middleware<S, A> {

    override fun process(action: A, currentState: S, store: Store<S, A>) {
        Log.v("LoggingMiddleware", "Processing action: $$action, current state: $currentState")
    }
}