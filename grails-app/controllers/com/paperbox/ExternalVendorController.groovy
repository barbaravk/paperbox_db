package com.paperbox

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ExternalVendorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ExternalVendor.list(params), model:[externalVendorCount: ExternalVendor.count()]
    }

    def show(ExternalVendor externalVendor) {
        respond externalVendor
    }

    def create() {
        respond new ExternalVendor(params)
    }

    @Transactional
    def save(ExternalVendor externalVendor) {
        if (externalVendor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (externalVendor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond externalVendor.errors, view:'create'
            return
        }

        externalVendor.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'externalVendor.label', default: 'ExternalVendor'), externalVendor.id])
                redirect externalVendor
            }
            '*' { respond externalVendor, [status: CREATED] }
        }
    }

    def edit(ExternalVendor externalVendor) {
        respond externalVendor
    }

    @Transactional
    def update(ExternalVendor externalVendor) {
        if (externalVendor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (externalVendor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond externalVendor.errors, view:'edit'
            return
        }

        externalVendor.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'externalVendor.label', default: 'ExternalVendor'), externalVendor.id])
                redirect externalVendor
            }
            '*'{ respond externalVendor, [status: OK] }
        }
    }

    @Transactional
    def delete(ExternalVendor externalVendor) {

        if (externalVendor == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        externalVendor.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'externalVendor.label', default: 'ExternalVendor'), externalVendor.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'externalVendor.label', default: 'ExternalVendor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
