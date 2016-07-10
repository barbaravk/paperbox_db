package com.paperbox

class QuoteRequest {

    static hasOne = [vendor: ExternalVendor]
    static hasMany = [requestDetaul: QuoteRequestDetail]

    static constraints = {
    }
}
