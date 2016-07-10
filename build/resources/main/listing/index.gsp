<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="filter"/>
    <asset:stylesheet href="content_filter/content_filter.css"/>
    <g:set var="entityName" value="${message(code: 'listing.label', default: 'Listing')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>
<body>
        <div class="row index">
            <g:each in="${listingList}">
                <c:listingCard id="${it.id}"/>
            </g:each>
        </div>
<asset:javascript src="manifests/content_filter.js"/>
</body>
</html>
