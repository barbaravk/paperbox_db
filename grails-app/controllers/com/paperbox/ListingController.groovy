package com.paperbox

import grails.converters.JSON
import org.grails.buffer.StreamCharBuffer

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ListingController {

    def springSecurityService
    def assetResourceLocator

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", filter: "POST"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Listing.list(params), model:[listingCount: Listing.count()]
    }

    def show(Listing listing) {
        respond listing
    }

    def create() {
        respond new Listing()
    }

    @Transactional
    def save(Listing listing) {
        if (listing == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        def vendor = Vendor.findByUser(User.findByUsername(springSecurityService.principal.username))
        if (vendor) {
            listing.vendor = vendor
        }

        listing.status = 'A'
        listing.validate()

        if (listing.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond listing.errors, view:'create'
            return
        }

        listing.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'listing.label', default: 'Listing'), listing.id])
                redirect listing
            }
            '*' { respond listing, [status: CREATED] }
        }
    }

    @Transactional
    def saveListingAjax(Listing listing) {
        if (listing == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        def slurper = new groovy.json.JsonSlurper()
        def vendor = Vendor.findByUser(User.findByUsername(springSecurityService.principal.username))
        if (vendor) {
            listing.vendor = vendor
        }
        if (params.tags) {
            def tags = params.tags.split(',')
            tags.each {
                listing.addToTag new Tag(tagString: it, tagType: 'listing')
            }
        }
        if (params.items) {
            def items = slurper.parseText(params.items)
            items.each {
                listing.addToItem new Item(itemName: it.value.itemName, itemDescription: it.value.itemDescription,
                        itemPrice: it.value.itemPrice)
            }
        }
        if (params.images) {
            def images = slurper.parseText(params.images)
            images.each {
                def imageDataName= 'imageData_' + it.key.tokenize('_')[1]
                byte[] image = params[imageDataName].inputStream.bytes
                listing.addToImage new ListingImage(imageTitle: it.value.title, imageSubtitle: it.value.subtitle,
                        image: image)
            }
        }

        listing.status = 'A'
        listing.validate()

        if (listing.hasErrors()) {
            transactionStatus.setRollbackOnly()
            render([success: false, error: listing.errors])
        }

        listing.save flush:true

        def URL = '/listing/show/' + listing.id
        render([success: true, URL: URL] as JSON)
    }

    def edit(Listing listing) {
        respond listing
    }

    @Transactional
    def update(Listing listing) {
        if (listing == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (listing.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond listing.errors, view:'edit'
            return
        }

        listing.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'listing.label', default: 'Listing'), listing.id])
                redirect listing
            }
            '*'{ respond listing, [status: OK] }
        }
    }

    @Transactional
    def delete(Listing listing) {

        if (listing == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        listing.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'listing.label', default: 'Listing'), listing.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def filter() {
        def slurper = new groovy.json.JsonSlurper()
        def filterParams = slurper.parseText(params.filterItems)

        def listingCategoryIds = []
        def eventDate
        def list
        filterParams.each {
            if (it.value.valueType == 'listingCategory') {
                listingCategoryIds.push(it.value.value)
            }
            else if (it.value.valueType == 'eventDate') {
                eventDate = Date.parse('dd/MM/yyyy', it.value.value)
            }
        }

        def calendarEntries = CalendarEntry.list()
        def bookedVendors = []
        calendarEntries.each {
            if (it.periodStart <= eventDate && eventDate <= it.periodEnd) {
                bookedVendors.push(it.vendor)
            }
        }

        System.out.println(bookedVendors)

        if (!listingCategoryIds.isEmpty()) {
            def categoryList = ListingCategory.findAllByIdInList(listingCategoryIds)
            list = Listing.findAllByListingCategoryInList(categoryList)
        } else {
            list = Listing.list()
        }
        list.removeAll {
            bookedVendors.contains(it.vendor)
        }

        String tempString
        StringBuilder outString = new StringBuilder()

        list.each {
            tempString = g.render(template: "/includes/listingCard", model: [listing: it, listingName: it
                    .listingName, listingTagline: it.listingTagline, user: it.vendor.user])
            outString.append(tempString)
        }

        render ([success: true, htmlOutput: outString.toString()] as JSON)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'listing.label', default: 'Listing'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def displayProfilePicture (User user){
        def resource = assetResourceLocator.findAssetForURI('placeholder.jpg')
        byte[] placeholder = resource.inputStream.bytes
        if (user && user.picture) {
            response.outputStream << user.picture
        }
        else {
            response.contentType = 'image/jpg'
            response.outputStream << placeholder
        }
        response.outputStream.flush()
    }

    def displayBackgroundImage (Listing listing){
        def resource = assetResourceLocator.findAssetForURI('card-placeholder.jpg')
        byte[] placeholder = resource.inputStream.bytes
        if (listing && !listing.image.empty) {
            response.outputStream << listing.image.first().image
        }
        else {
            response.contentType = 'image/jpg'
            response.outputStream << placeholder
        }
        response.outputStream.flush()
    }
}
