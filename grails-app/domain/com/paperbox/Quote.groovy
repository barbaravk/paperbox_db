package com.paperbox

class Quote {

    List quoteDetail

    static belongsTo = [vendor: Vendor]
    static hasMany = [quoteDetail: QuoteDetail]
    static constraints = {
    }

    static mapping = {
        id column: 'quote_id'
    }
}
