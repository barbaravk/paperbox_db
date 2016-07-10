package com.paperbox

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class QuoteRequestController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond QuoteRequest.list(params), model:[quoteRequestCount: QuoteRequest.count()]
    }

    def show(QuoteRequest quoteRequest) {
        respond quoteRequest
    }

    def create() {
        respond new QuoteRequest(params)
    }

    @Transactional
    def save(QuoteRequest quoteRequest) {
        if (quoteRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (quoteRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond quoteRequest.errors, view:'create'
            return
        }

        quoteRequest.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'quoteRequest.label', default: 'QuoteRequest'), quoteRequest.id])
                redirect quoteRequest
            }
            '*' { respond quoteRequest, [status: CREATED] }
        }
    }

    def edit(QuoteRequest quoteRequest) {
        respond quoteRequest
    }

    @Transactional
    def update(QuoteRequest quoteRequest) {
        if (quoteRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (quoteRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond quoteRequest.errors, view:'edit'
            return
        }

        quoteRequest.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'quoteRequest.label', default: 'QuoteRequest'), quoteRequest.id])
                redirect quoteRequest
            }
            '*'{ respond quoteRequest, [status: OK] }
        }
    }

    @Transactional
    def delete(QuoteRequest quoteRequest) {

        if (quoteRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        quoteRequest.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'quoteRequest.label', default: 'QuoteRequest'), quoteRequest.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'quoteRequest.label', default: 'QuoteRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
