package com.paperbox

class Listing {

    ListingCategory listingCategory
    String listingName
    String listingTagline
    String listingDescription
    Character status

    static belongsTo = [vendor: Vendor]
    static hasMany = [image: ListingImage, tag: Tag, item: Item]

    static constraints = {
        image nullable: true
        tag nullable: true
        item nullable: true
        listingTagline nullable: true
    }

    static mapping = {
        id column: 'listing_id'
        listingCategory column: 'listing_category_code'
    }
}
