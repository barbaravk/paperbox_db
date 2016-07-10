package com.paperbox

class MessagesTagLib {
    static defaultEncodeAs = [taglib: 'html']
    static namespace = 'c'
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def lines = { attrs, body ->
        out << attrs['string'].encodeAsHTML().replace('\n', '<br/>\n')
    }

}
