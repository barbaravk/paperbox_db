package com.paperbox


import com.the6hours.grails.springsecurity.facebook.FacebookAuthToken
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.ImageType
import org.springframework.social.facebook.api.impl.FacebookTemplate

import grails.transaction.Transactional

@Transactional
class FacebookAuthService {

    def create(FacebookAuthToken token) {
        log.info("Create domain for facebook user $token.uid")

        //Use Spring Social Facebook to load details for current user from Facebook API
        Facebook facebook = new FacebookTemplate(token.accessToken.accessToken)
        org.springframework.social.facebook.api.User fbProfile = facebook.userOperations().userProfile
        String username = fbProfile.id
        String firstName = fbProfile.firstName
        String lastName = fbProfile.lastName
        String email = fbProfile.email
        byte[] picture = facebook.userOperations().getUserProfileImage(ImageType.LARGE)
        User person

        if (User.findByEmail(email)) {
            person = User.findByEmail(email)
        }
        else { person = new User(
                username: username,
                email: email,
                password: token.accessToken.accessToken, //not really necessary
                firstName: firstName,
                lastName: lastName,
                picture: picture,
                enabled: true,
                accountExpired:  false,
                accountLocked: false,
                passwordExpired: false,
        )}

        person.save()

        FacebookUser fbUser = new FacebookUser(
                uid: token.uid,
                accessToken: token.accessToken.accessToken,
                accessTokenExpires: token.accessToken.expireAt,
                user: person
        )

        try {
            fbUser.save()
            UserRole.create(fbUser.user, Role.findByAuthority('ROLE_USER'))
            UserRole.create(fbUser.user, Role.findByAuthority('ROLE_FACEBOOK'))
        }
        catch (Exception e)
        {
            println(e)
        }

        return fbUser
    }
}
