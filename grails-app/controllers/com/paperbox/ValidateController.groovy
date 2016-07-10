package com.paperbox

import static org.springframework.http.HttpStatus.*

class ValidateController {

    def AjaxMethodsService

    def postcodeValidate(params) {
        if (ajaxMethodsService.postcodeValidate(params.cityStatePostcode)) {
            render status: OK
        }
        else render status: NOT_ACCEPTABLE
    }
}
