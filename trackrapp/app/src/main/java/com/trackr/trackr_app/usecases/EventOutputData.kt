package usecases

import entities.Anniversary

/**
 * A dataclass representing the Data that Presenter will retrieve from EventManager
 */
class EventOutputData(event: Event?) {
    private var eventType: EventTypes? = null
    var firstName: String? = null
    var lastName: String? = null
    var personDescription: String? = null
    var personTags: Set<String>? = null
    private var date: LocalDate? = null
    private var remindDeadline: LocalDate? = null

    enum class EventTypes {
        ANNIVERSARY, BIRTHDAY
    }

    fun getEventType(): String {
        return eventType.toString()
    }

    fun getDate(): LocalDate? {
        return date
    }

    fun getRemindDeadline(): LocalDate? {
        return remindDeadline
    }

    init {
        if (event != null) {
            eventType = if (event is Anniversary) EventTypes.ANNIVERSARY else EventTypes.BIRTHDAY
            firstName = event.getPerson().getFirstName()
            lastName = event.getPerson().getLastName()
            personDescription = event.getPerson().getDescription()
            personTags = event.getPerson().getTags()
            date = event.getDate()
            remindDeadline = event.getReminderDeadline()
        }
    }
}