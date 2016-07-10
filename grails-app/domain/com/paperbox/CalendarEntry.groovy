package com.paperbox

class CalendarEntry {

    Date periodStart
    Date periodEnd
    String periodStatus
    String calendarEntryType
    String calendarEntryName

    static belongsTo = [vendor: Vendor]

    static constraints = {
        calendarEntryType nullable: true
        calendarEntryName nullable: true
    }
}
