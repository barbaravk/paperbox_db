package com.paperbox

class Vendor {

    String businessName
    String addressLine1
    String addressLine2
    String cityStatePostcode
    String contactFirstName
    String contactLastName
    String phoneNumber
    String altEmail
    Character status
    String website
    String abn
    String about

    static belongsTo = [user: User]

    static constraints = {
        addressLine2 nullable: true
        abn nullable: true
        website nullable: true
        altEmail nullable: true
    }

    static mapping = {
        id column: 'vendor_id'
    }
}
