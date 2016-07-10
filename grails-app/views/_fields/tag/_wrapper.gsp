<%
    def otherAttributes = {attrs ->

        String attrList = ''
        attrs.each { k, v ->
            if (!v) {
                attrList = attrList + k + ' '
            } else attrList = attrList + k + '=\"' + v + '\" '
        }
        return attrList
    }

    def valueString = { value ->
        String values = ''
        value.each {
            if (it == value.last()) {
                values = values + it.tagString
            }
            else values = values + it.tagString + ', '
        }
        return values
    }
%>

<div class="form-group ${attrs.remove('class')}">
    <g:if test="${type != 'hidden' && label != ' '}">
        <h6 for="${property}">${label} <g:if test="${required}"><span class="icon-danger">*</span></g:if></h6>
    </g:if>
    <input name="${property}" id="${property}" value="${value ? valueString(value) : attrs.placeholder}" type="${attrs.remove('type')}"
           class="tagsinput tag-fill new ${attrs.remove('inputClass')}"
           ${required == true ? "required" : null}
        <g:applyCodec encodeAs="none">${otherAttributes(attrs)}</g:applyCodec>/>
    <p class="help-block with-errors pull-left"></p>
</div>
