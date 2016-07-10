package com.paperbox

class Item {

    String itemName
    String itemDescription
    Double itemPrice

    static mapping = {
        id column: 'item_id'
    }
    static constraints = {
        itemPrice nullable: true
    }
}
