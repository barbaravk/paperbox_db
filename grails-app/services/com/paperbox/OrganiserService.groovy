package com.paperbox

import grails.transaction.Transactional
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.impl.FacebookTemplate

@Transactional
class OrganiserService {

    def springSecurityService

    def create() {
        def user = User.findByUsername(springSecurityService.principal.username)
        def organiser = new Organiser()
        if (user) {
            organiser.user = user
            organiser.firstName = user.firstName
            organiser.lastName = user.lastName

            def fbUser = FacebookUser.findByUser(user)

            if (fbUser) {
                Facebook facebook = new FacebookTemplate(fbUser.accessToken)
                org.springframework.social.facebook.api.User fbProfile = facebook.userOperations().userProfile
                String gender = fbProfile.gender
                organiser.gender = gender
            }
        }
        return organiser
    }

}
