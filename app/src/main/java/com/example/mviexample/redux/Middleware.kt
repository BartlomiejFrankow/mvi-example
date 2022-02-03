package com.example.mviexample.redux

/**
 * A [Middleware] is any class that deals with side effects of actions. This could be logging triggering network calls and other examples.
 */
interface Middleware<S : State, A : Action> {

    /**
     * This will process the given [action] and [currentState] and determinate if we need to perform any side effects, or trigger a new action.
     *
     * @param[store] This is a reference to the [Store] that dispatched this action. We should only call this with a new action, and not trigger
     * the same action again or risk ending up in a loop.
     */
    fun process(
        action: A,
        currentState: S,
        store: Store<S, A>
    )
}