package com.paperbox

import grails.transaction.Transactional

class VendorService {

    def springSecurityService

    def create() {
        def user = User.findByUsername(springSecurityService.principal.username)
        def vendor = new Vendor()
        if (user) {
            vendor.user = user
            vendor.contactFirstName = user.firstName
            vendor.contactLastName = user.lastName
        }
        return vendor
    }
}
