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
    <input name="${property}" id="${property}" value="${value}" type="${attrs.remove('type')}"
           class="form-control ${attrs.remove('inputClass')}"
           placeholder="${attrs.remove('placeholder')}" ${required == true ? "required" : null} <g:applyCodec
            encodeAs="none">${otherAttributes(attrs)}</g:applyCodec>/>

    <p class="help-block with-errors pull-left"></p>
</div>
