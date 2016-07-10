package com.paperbox

class FacebookUser {

    Long uid
    String accessToken
    Date accessTokenExpires

    static belongsTo = [user: User] //connected to main Spring Security domain

    static constraints = {
        uid unique: true
    }

    static void removeAll(User u, boolean flush = false) {
        if (u == null) return

        FacebookUser.where { user == u }.deleteAll()

        if (flush) { FacebookUser.withSession { it.flush() } }
    }
}
