package com.paperbox

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured('permitAll')
class UserController {

    def springSecurityService

    def assetResourceLocator

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userCount: User.count()]
    }

    def show(User user) {
        respond user
    }

    def create() {
        respond new User(params)
    }

    @Transactional
    def save(User user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'create'
            return
        }

        if (user.save(flush: true)) {
            addRoles user
            springSecurityService.reauthenticate user.username
            redirect action: 'show', id: user.id
        }

        if (user.userType == 'vendor') {
            redirect controller: "vendor", action: "create", params: [user: user.id]
        }

        else if (user.userType == 'organiser') {
            redirect controller: "organiser", action: "create", params: [user: user.id]
        }
    }

    def edit(User user) {
        respond user
    }

    @Transactional
    def update(User user, Long id, Long version) {
        if (!user) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'edit'
            return
        }

        if (version != null && user.version > version) {
            user.errors.rejectValue 'version', 'default.optimistic.locking.failure',
                    'Another user has updated this TestUser while you were editing'
            render view: 'edit', model: buildUserModel(user)
            return
        }

        def oldPassword = user.password
        user.properties = params

        if (user.save(flush: true)) {
            UserRole.removeAll user
            addRoles user
            redirect action: 'show', id: user.id
        }
        else {
            render view: 'edit', model: buildUserModel(user)
        }
    }

    @Transactional
    def delete(User user, Long id) {
        if (user) {
            def authPrincipal = springSecurityService.principal
            // avoid self-delete if the logged-in user is an admin
            if (!(authPrincipal instanceof String) && authPrincipal.username == user.username) {
                flash.message = 'You can not delete yourself, please login as another admin and try again'
            }
            else {
                try {
                    UserRole.removeAll user
                    FacebookUser.removeAll user
                    user.delete flush: true
                    flash.message = "User $id deleted."
                    redirect action: 'index', method:"GET"
                }
                catch (DataIntegrityViolationException e) {
                    flash.message = "User $id could not be deleted"
                    redirect action: 'show', id: id
                }
            }
        }
        else {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def accountLocked() {
        render 'accountLocked'
    }

    def accountDisabled() {
        render 'accountDisabled'
    }

    def accountExpired() {
        render 'accountExpired'
    }

    def passwordExpired() {
        render 'passwordExpired'
    }

    private void addRoles(user) {
        for (String key in params.keySet()) {
            if (key.contains('ROLE') && 'on' == params.get(key)) {
                UserRole.create user, Role.findByAuthority(key), true
            }
        }
    }

    private Map buildUserModel(user) {

        List roles = Role.list().sort { it.authority }
        Set userRoleNames = user.authorities*.authority
        LinkedHashMap<Role, Boolean> roleMap = [:]
        for (role in roles) {
            roleMap[(role)] = userRoleNames.contains(role.authority)
        }

        [user: user, roleMap: roleMap]
    }
}
