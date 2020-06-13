package tj.rs.pharmacyonline.utils.event

import androidx.lifecycle.MutableLiveData

/**
 * @author Rustam Safarov (RS)
 * created at 13.06.2020
 */
class Emitter : MutableLiveData<Event>() {
    private val waitingEvents: ArrayList<Event> = ArrayList()
    private var isActive = false


    override fun onInactive() {
        isActive = false
    }

    override fun onActive() {
        isActive = true
        val postingEvent = ArrayList<Event>()
        waitingEvents.forEach {
            if (hasActiveObservers()) {
                this.value = it
                postingEvent.add(it)
            }
        }.also { waitingEvents.removeAll(postingEvent) }
    }

    private fun newEvent(event: Event, type: Type) {
        event.type = type
        this.value = when (type) {
            Type.EXECUTE_WITHOUT_LIMITS,
            Type.EXECUTE_ONCE -> if (hasActiveObservers()) event else null

            Type.WAIT_OBSERVER_IF_NEEDED,
            Type.WAIT_OBSERVER_IF_NEEDED_AND_EXECUTE_ONCE -> {
                if (hasActiveObservers() && isActive) event
                else {
                    waitingEvents.add(event)
                    null
                }
            }
        }
    }

    /** Clear All Waiting Events */
    fun clearWaitingEvents() = waitingEvents.clear()

    /** Default: Emit Event for Execution */
    fun emitAndExecute(event: Event) = newEvent(event, Type.EXECUTE_WITHOUT_LIMITS)

    /** Emit Event for Execution Once */
    fun emitAndExecuteOnce(event: Event) = newEvent(event, Type.EXECUTE_ONCE)

    /** Wait Observer Available and Emit Event for Execution */
    fun waitAndExecute(event: Event) = newEvent(event, Type.WAIT_OBSERVER_IF_NEEDED)

    /** Wait Observer Available and Emit Event for Execution Once */
    fun waitAndExecuteOnce(event: Event) =
        newEvent(event, Type.WAIT_OBSERVER_IF_NEEDED_AND_EXECUTE_ONCE)
}