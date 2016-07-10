package com.paperbox

class Event {

    Date eventDate
    String eventType
    int eventPostcode
    String eventName
    String status
    String eventDescription
    List eventDetail

    static belongsTo = [organiser: Organiser]
    static hasMany = [eventDetail: EventDetail]
    static constraints = {
    }

    static mapping = {
        id column: 'event_id'
    }
}
