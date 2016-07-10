package com.paperbox

import grails.plugins.rest.client.RestBuilder
import groovy.json.JsonSlurper

import static org.springframework.http.HttpStatus.*

class AjaxMethodsService {

    def ausPostcode(q) {
        String url = 'https://digitalapi.auspost.com.au/postcode/search.json?q=' + q
        String apiKey = 'a48d31e9-8684-48e7-9425-a6328508c5b2'
        def rest = new RestBuilder()
        def resp = rest.get(url) {
            header 'AUTH-KEY', apiKey
        }
        return resp
    }

    def postcodeValidate(q) {
        def resp = ausPostcode(q).json
        def count = 0
        if (resp.localities) {
           resp.localities.locality.id.each {
               count++
           }
            return count == 1
        }
        else {
            return false
        }
    }
}
