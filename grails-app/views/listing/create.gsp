<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'listing.label', default: 'Listing')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div id="create-listing" class="content create" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.listing}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.listing}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <div class="container">
        <form action="${postUrl ?: '/listing/save'}" method="POST" id="createListingForm"
              data-toggle="validator" role="form">
            <div class="row media">
                <h3>Add service listing</h3>
                <f:field bean="listing" property="listingName" type="text" label="Name"
                         placeholder="name for your service listing"
                         class="col-md-5 col-sm-12"
                         inputClass="border-input" wrapper="/"
                         data-error="Listing name is required"/>
                <f:field bean="listing" property="listingTagline" type="text" label="Tagline"
                         placeholder="name for your service listing"
                         class="col-md-7 col-sm-12"
                         inputClass="border-input" wrapper="/"/>
                <f:field bean="listing" property="listingCategory" label="Category"
                         placeholder="select listing category"
                         class="col-md-5 col-sm-12"
                         inputClass="border-input" wrapper="/select"
                         data-error="Please select category" />
                <g:render template="/includes/tagInput"/>
                <f:field bean="listing" property="listingDescription" label="Description"
                         placeholder="This is a textarea limited to 150 characters."
                         class="col-md-12 col-sm-12" inputClass="textarea-limited border-input"
                         element="textarea" rows="5" data-limit="150"
                         wrapper="/textarea"/>
            </div>
            <div class="row media">
                <h3 style="margin-bottom: 10px">Services and packages</h3>
                <g:render template='/includes/listingItem'/>

                <div class="item-add" style="margin-bottom: 20px">
                    <span class="btn btn-small btn-primary btn-simple btn-file" id="listingItemAdd">
                        <i class="ti-plus" aria-hidden="true"></i>
                        add line
                    </span>
                </div>
            </div>

            <div class="row media">
                <h3 style="margin-bottom: 10px">Gallery</h3>

                <div class="gallery-add" data-provides="galleryinput" style="margin-bottom: 20px">
                    <span class="btn btn-small btn-primary btn-simple btn-file">
                        <i class="ti-plus" aria-hidden="true"></i>
                        add photos
                        <input type="file" class="photo-upload">
                    </span>
                </div>

                <div id="gallery" class="final-tiles-gallery effect-fade-out caption-top social-icons-right social-icons-circle">
                    <div class="ftg-items"></div>
                </div>

            </div>

            <div class="row buttons-row">
                <div class="col-md-4">
                    <input name="exit" class="btn btn-info btn-block"
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
<asset:javascript src="manifests/listing.js"/>
</body>
</html>
