package com.example.mviexample

import com.example.mviexample.redux.Action
import com.example.mviexample.redux.Middleware
import com.example.mviexample.redux.State
import com.example.mviexample.redux.Store
import junit.framework.Assert.assertTrue

/**
 * This is a implementation of [Middleware] that will log every action sent to it.
 *
 * In a unit test, we can apply this middleware to a [Store], and then use this middleware for asserting that an action was sent to that store.
 */
class ActionCaptureMiddleware<S : State, A : Action> : Middleware<S, A> {

    private val actionHistory: MutableList<Action> = mutableListOf()

    override suspend fun process(action: A, currentState: S, store: Store<S, A>) {
        actionHistory.add(action)
    }

    fun assertActionProcessed(expectedAction: Action) {
        assertTrue(actionHistory.contains(expectedAction))
    }
}
