package com.paperbox

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

class FileUploadController {

    def springSecurityService
    def assetResourceLocator

    @Transactional
    def saveProfilePicture(params) {
        def user = User.findByUsername(springSecurityService.principal.username)
        def file = params.file
         if (user == null || file == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        byte[] picture = file.inputStream.bytes

        if (user) {
            user.picture = picture
            user.save(flush: true)
        }
        else return
        render status: OK
    }

    @Transactional
    def removeProfilePicture(){
        def user = User.findByUsername(springSecurityService.principal.username)

        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        user.picture = null
        user.save(flush: true)

        render status: OK
    }

    def displayProfilePicture (){
        def resource = assetResourceLocator.findAssetForURI('placeholder.jpg')
        byte[] placeholder = resource.inputStream.bytes
        def user = User.findByUsername(springSecurityService.principal.username)

        if (user && user.picture) {
            response.outputStream << user.picture
        }
        else {
            response.contentType = 'image/jpg'
            response.outputStream << placeholder
        }
        response.outputStream.flush()
    }

    protected void notFound() {
        render status: NOT_FOUND
    }
}
