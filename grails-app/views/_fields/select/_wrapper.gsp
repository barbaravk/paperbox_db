<%
    def otherAttributes = { attrs ->

        String attrList = ''
        attrs.each { k, v ->
            if (!v) {
                attrList = attrList + k + ' '
            } else attrList = attrList + k + '=\"' + v + '\" '
        }
        return attrList
    }
%>

<div class="form-group ${attrs.remove('class')}">
    <g:if test="${type != 'hidden' && label != ' '}">
        <h6 for="${property}">${label} <g:if test="${required}"><span class="icon-danger">*</span></g:if></h6>
    </g:if>
        <select name="${property}" id="${property}" ${required == true ? "required" : null}
                class="selectpicker ${attrs.remove('inputClass')}"
                data-style="form-control border-input"
                data-menu-style=""
            <g:applyCodec encodeAs="none">${otherAttributes(attrs)}</g:applyCodec>>
            <option disabled selected>${attrs.remove('placeholder')}</option>
            <g:each var="cat" in="${com.paperbox.ListingCategory.listOrderByCategoryName()}">
                <option value="${cat.id}">${cat.categoryName}</option>
            </g:each>

        </select>
    <p class="help-block with-errors pull-left"></p>
</div>
