package com.paperbox

class Message {

    Long fromId
    Long toId
//    String messageType
    String text
    Boolean last
    Boolean readed = false
    Date dateCreated
    String subject
    Boolean reply = false
    Boolean lastOnThread = false
    Integer numberOfMessagesOnThread = 1
    Boolean fromDeletedOnThread = false
    Boolean toDeletedOnThread = false
    String fromName = ''
    String toName = ''

    static constraints = {
        subject nullable: true, blank: true
        reply nullable: true
        lastOnThread nullable: true
        numberOfMessagesOnThread nullable: true
        fromDeletedOnThread nullable: true
        toDeletedOnThread nullable: true
        fromName nullable: true, blank: true
        toName nullable: true, blank: true
    }

    static mapping = {
        text type: "text"
        subject type: "text"
    }

    boolean isDeletedForUser(Long idUser){
        return ((this.fromId == idUser && this.fromDeletedOnThread) || (this.toId == idUser && this.toDeletedOnThread))
    }
}
