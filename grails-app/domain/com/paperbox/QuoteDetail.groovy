package com.paperbox

class QuoteDetail {

    EventDetail eventDetail

    static belongsTo = [quote: Quote]

    static constraints = {
        eventDetail nullable: true
    }

    static mapping = {
        id column: 'quote_detail_id'
    }

    static mappedBy = [eventDetail: 'event_detail_id']
}
