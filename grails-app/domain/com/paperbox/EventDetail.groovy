package com.paperbox

class EventDetail {

    String listingCode
    String bookingStatus
    String vendorBooked
    Number budgetAmount
    Number bookedAmount

    static hasOne = [acceptedQuoteDetail: QuoteDetail]

    static belongsTo = [event: Event]

    static constraints = {
        acceptedQuoteDetail nullable: true
    }

    static mapping = {
        id column: 'event_detail_id'
    }

}
