package usecases

import com.trackr.trackr_app.entities.Event
import java.time.LocalDate

/**
 * A dataclass representing the Data that Presenter will retrieve from EventManager
 */
class EventOutputData(event: Event?) {
    private var eventType: EventTypes? = null
    var firstName: String? = null
    var lastName: String? = null
    var personDescription: String? = null
    var personTags: Set<String?>? = null
    var date: LocalDate? = null
    var remindDeadline: LocalDate? = null

    enum class EventTypes {
        ANNIVERSARY, BIRTHDAY
    }

    fun getEventType(): String {
        return eventType.toString()
    }

    init {
        if (event != null) {
            eventType = if (event is Anniversary) EventTypes.ANNIVERSARY else EventTypes.BIRTHDAY
            firstName = event.getPerson().firstName
            lastName = event.getPerson().lastName
            personDescription = event.getPerson().description
            personTags = event.getPerson().getTags()
            date = event.date
            remindDeadline = event.reminderDeadline
        }
    }
}