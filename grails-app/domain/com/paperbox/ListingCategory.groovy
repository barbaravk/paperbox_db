package com.paperbox

class ListingCategory {

    String categoryName

    ListingCategory(String categoryName) {
        this.categoryName = categoryName
    }

    static constraints = {
    }
    static mapping = {
        id column: 'listing_category_code'
    }
}
