package com.paperbox

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.GrantedAuthority

class MyUserDetails extends GrailsUser {

    final String firstName
    final String lastName
    final byte[] picture

    MyUserDetails(
            String username,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<GrantedAuthority> authorities,

            long id,
            String firstName,
            String lastName) {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities, id)

        this.firstName = firstName
        this.lastName = lastName
        this.picture = picture
    }
}
