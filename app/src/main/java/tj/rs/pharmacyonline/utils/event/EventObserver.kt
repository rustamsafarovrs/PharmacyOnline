package tj.rs.pharmacyonline.utils.event

import androidx.lifecycle.Observer

/**
 * @author Rustam Safarov (RS)
 * created at 13.06.2020
 */
class EventObserver(private val handlerBlock: (Event) -> Unit) : Observer<Event> {
    private val executedEvents: HashSet<String> = hashSetOf()

    fun clearExecutedEvents() = executedEvents.clear()

    override fun onChanged(it: Event?) {
        when (it?.type) {
            Type.EXECUTE_WITHOUT_LIMITS,
            Type.WAIT_OBSERVER_IF_NEEDED -> {
                if (!it.isHandled) {
                    it.isHandled = true
                    it.apply(handlerBlock)
                }
            }
            Type.EXECUTE_ONCE,
            Type.WAIT_OBSERVER_IF_NEEDED_AND_EXECUTE_ONCE -> {
                if (it.javaClass.simpleName !in executedEvents) {
                    if (!it.isHandled) {
                        it.isHandled = true
                        executedEvents.add(it.javaClass.simpleName)
                        it.apply(handlerBlock)
                    }
                }
            }
        }
    }
}
