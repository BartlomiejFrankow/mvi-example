package com.example.mviexample.redux

interface Reducer<S : State, A : Action> {

    /**
     * Given a [currentState] and some [action] that the user took, produce new [State]
     *
     * This will give us clear and predictable state management, that ensures each state is associated with some specific user Intent or Action
     */
    fun reduce(currentState: S, action: A): S
}
