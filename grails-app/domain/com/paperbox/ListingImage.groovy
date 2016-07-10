package com.paperbox

class ListingImage {
    String imageTitle
    String imageSubtitle
    byte[] image

    static belongsTo = [listing: Listing]
    static constraints = {
        image maxSize: 3000000
        imageTitle nullable: true
        imageSubtitle nullable: true
    }
}
