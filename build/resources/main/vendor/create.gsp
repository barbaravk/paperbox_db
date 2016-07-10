<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'vendor.label', default: 'Vendor')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div id="create-vendor" class="content" role="main">

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.vendor}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.vendor}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <div class="container">
        <h3>Tell us about your business</h3>

        <div class="row">
            <div class="col-md-3">
                <div class="card-user">
                    <c:profilePic/>
                </div>
            </div>
            <div class="col-md-9">
                <form action="${postUrl ?: '/vendor/save'}" method="POST" id="createVendorForm"
                      data-toggle="validator" role="form">
                    <fieldset>
                        <f:field bean="vendor" property="businessName" type="text" label="Business name"
                                 placeholder="business name"
                                 class="col-md-12 col-sm-12"
                                 inputClass="border-input" wrapper="/"
                                 data-error="Name of your business is required"/>
                        <f:field bean="vendor" property="website" type="url" label="Company Webstite"
                                 placeholder="URL"
                                 class="col-md-7 col-sm-12"
                                 inputClass="border-input" wrapper="/"/>
                        <f:field bean="vendor" property="abn" type="text" label="ABN"
                                 placeholder="ABN"
                                 class="col-md-5 col-sm-12"
                                 inputClass="border-input" wrapper="/"/>
                        <f:field bean="vendor" property="contactFirstName" type="text" label="First Name"
                                 placeholder="first name"
                                 class="col-md-6 col-sm-12"
                                 inputClass="border-input" wrapper="/"
                                 data-error="Contact first name is required"/>
                        <f:field bean="vendor" property="contactLastName" type="text" label="Last Name"
                                 placeholder="last name"
                                 class="col-md-6 col-sm-12"
                                 inputClass="border-input" wrapper="/"
                                 data-error="Contact last name is required"/>
                        <f:field bean="vendor" property="phoneNumber" type="text" label="Phone Number"
                                 placeholder="phone number"
                                 class="col-md-5 col-sm-12"
                                 inputClass="border-input" wrapper="/"
                                 data-error="Contact phone number is required"/>
                        <f:field bean="vendor" property="altEmail" type="text" label="Alternative Email"
                                 placeholder="email"
                                 class="col-md-7 col-sm-12"
                                 inputClass="border-input" wrapper="/"/>
                        <f:field bean="vendor" property="addressLine1" type="text" label="Street Address"
                                 placeholder="line 1"
                                 class="col-md-12 col-sm-12"
                                 inputClass="border-input" wrapper="/"
                                 data-error="Valid street address is required"/>
                        <f:field bean="vendor" property="addressLine2" type="text" label=" "
                                 placeholder="line 2"
                                 class="col-md-12 col-sm-12"
                                 inputClass="border-input" wrapper="/"/>
                        <f:field bean="vendor" property="cityStatePostcode" type="text" label=" "
                                 placeholder="City, State and Postcode"
                                 class="col-md-12 col-sm-6" inputClass="locationsearch border-input"
                                 data-remote="/validate/postcodeValidate"
                                 data-error="Valid location is required"
                                 data-remote-error="Please select valid location"
                                 wrapper="/"/>
                        <f:field bean="vendor" property="about" label="About"
                                 placeholder="This is a textarea limited to 150 characters."
                                 class="col-md-12 col-sm-6" inputClass="textarea-limited border-input"
                                 element="textarea" rows="8" data-limit="150"
                                 wrapper="/textarea"/>
                    </fieldset>
                    <div class="row buttons-row">
                        <div class="col-md-4">
                            <input name="save_and_exit" class="btn btn-info btn-block"
                                   value="${message(code: 'default.button.create.save_and_exit',
                                           default: 'Exit')}"/>
                        </div>
                        <div class="col-md-4">
                            <input type="submit" name="save_and_exit" class="btn btn-block"
                                   value="${message(code: 'default.button.create.save_and_exit',
                                           default: 'Save and exit')}"/>
                        </div>
                        <div class="col-md-4">
                            <input type="submit" name="save_and_continue" class="btn btn-fill btn-block"
                                   value="${message(code: 'default.button.create.save_and_continue',
                                           default: 'Save and continue')}"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<asset:javascript src="application.js"/>
</body>
</html>
