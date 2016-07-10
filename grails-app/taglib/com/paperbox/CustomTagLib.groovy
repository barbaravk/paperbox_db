package com.paperbox

import org.grails.validation.DomainClassPropertyComparator
import org.grails.web.util.*

class CustomTagLib {

    def springSecurityService

    static returnObjectForTags = ['ifFieldError']

    static namespace = 'c'

    def ifFieldError = { attrs, body ->
        def bean = attrs.bean
        def field = attrs.field
        def placeholder = attrs.placeholder

        if (bean && field) {
            if (bean.errors.getFieldError(field)) {
                return g.message(error: bean.errors?.getFieldError(field), encodeAs: "HTML")
            } else
                return placeholder.toString().encodeAsHTML()
        }
        return ''
    }

    def profilePic = { attrs ->
        def user = User.findByUsername(springSecurityService.principal.username)
        if(user && user.picture) {
            out << '<div class="fileinput fileinput-exists" data-provides="fileinput">'
        }
        else {
            out << '<div class="fileinput fileinput-new" data-provides="fileinput">'
        }
        out << render(template: '/includes/profilePicture')
        out << '</div>'
    }
    def listingCard = { attrs, body ->
        def listing = Listing.findById(attrs.remove('id'))
        def vendor = listing.vendor
        def user = vendor.user
            out << render(template: "/includes/listingCard",
                    model: [listing: listing, listingName: listing.listingName, listingTagline: listing.listingTagline, user: user])
    }
}
