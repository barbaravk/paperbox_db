<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'organiser.label', default: 'Organiser')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div id="create-organiser" class="content" role="main">

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.organiser}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.organiser}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <div class="container">
        <h3>Tell us about yourself</h3>
        <div class="row">
            <div class="col-md-3">
                <div class="card-user">
                        <c:profilePic/>
                </div>
            </div>
            <div class="col-md-9">
                    <form action="${postUrl ?: '/organiser/save'}" method="POST" id="createOrganiserForm"
                          data-toggle="validator" role="form">
                            <fieldset>
                            <f:field bean="organiser" property="firstName" type="text" label="First name"
                                     placeholder="First name"
                                     class="col-md-12 col-sm-12"
                                     inputClass="border-input" wrapper="/"
                                     data-error="First name is requied"/>
                            <f:field bean="organiser" property="lastName" type="text" label="Last name"
                                     placeholder="Last name"
                                     class="col-md-12 col-sm-12"
                                     inputClass="border-input" wrapper="/"
                                     data-error="Last name is required"/>
                            <f:field bean="organiser" property="gender" type="text" label="Gender"
                                     class="col-md-12 col-sm-12"
                                     wrapper="/radio"
                                     options="[[label:'Female', value:'female'],[label:'Male', value:'male']]"
                                     data-error="Please select gender"/>
                            <f:field bean="organiser" property="dateOfBirth" label="Date of birth" type="text"
                                     placeholder="dd/mm/yyyy"
                                     class="col-md-6 col-xs-12"
                                     inputClass="border-input" wrapper="/datepicker"
                                     data-date-viewmode="years"
                                     data-start-date="01/01/1990"
                                     data-dateval="true"
                                     data-dateval-error="Invalid date format"/>
                            <f:field bean="organiser" property="cityStatePostcode" label="Location" type="text"
                                     placeholder="start typing suburb name or postcode"
                                     class="col-md-12 col-sm-6" inputClass="locationsearch border-input"
                                     data-remote="/validate/postcodeValidate"
                                     data-error="Valid location is required"
                                     data-remote-error="Please select valid location"
                                     wrapper="/"/>
                            <f:field bean="organiser" property="about" label="About"
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
