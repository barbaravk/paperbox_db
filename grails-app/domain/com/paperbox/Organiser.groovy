package com.paperbox

class Organiser {

    String firstName
    String lastName
    String cityStatePostcode
    String gender
    Date dateOfBirth
    String about
    String status

    static belongsTo = [user: User]

    static hasMany = [event: Event]

    static constraints = {
        about nullable: true, maxSize: 150
        dateOfBirth nullable: true

    }

    static mapping = {
        id column: 'org_id'
    }
}
