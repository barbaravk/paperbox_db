package com.paperbox

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class OrganiserController {

    def organiserService
    def ajaxMethodsService
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Organiser.list(params), model:[organiserCount: Organiser.count()]
    }

    def show(Organiser organiser) {
        respond organiser
    }

    def create() {
        def organiser = organiserService.create()
        respond organiser
    }

    @Transactional
    def save(Organiser organiser) {
        if (organiser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        def user = User.findByUsername(springSecurityService.principal.username)
        if (user) {
            organiser.user = user
        }

        organiser.status = 'A'
        organiser.validate()

        if (organiser.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond organiser.errors, view:'create'
            return
        }

        organiser.save flush:true

        request.withFormat {
            form multipartForm {
                if (params.save_and_continue) {
                    redirect controller: 'listing'
                }
                if (params.save_and_exit) {
                    redirect uri: '/'
                }
                else redirect organiser
            }
            '*' { respond organiser, [status: CREATED] }
        }
    }

    def edit(Organiser organiser) {
        respond organiser
    }

    @Transactional
    def update(Organiser organiser) {
        if (organiser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (organiser.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond organiser.errors, view:'edit'
            return
        }

        organiser.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'organiser.label', default: 'Organiser'), organiser.id])
                redirect organiser
            }
            '*'{ respond organiser, [status: OK] }
        }
    }

    @Transactional
    def delete(Organiser organiser) {

        if (organiser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        organiser.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'organiser.label', default: 'Organiser'), organiser.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def postcode(params) {
        def resp = ajaxMethodsService.ausPostcode(params.q)
        render resp.json
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'organiser.label', default: 'Organiser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
