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
    <g:if test="${type != 'hidden'}">
        <h6 for="${property}">${label} <g:if test="${required}"><span class="icon-danger">*</span></g:if></h6>
    </g:if>
    <textarea name="${property}" id="${property}" value="${value}" type="${attrs.remove('type')}"
              class="form-control ${attrs.remove('inputClass')}"
              placeholder="${attrs.remove('placeholder')}"
        <g:applyCodec
                encodeAs="none">${otherAttributes(attrs)}</g:applyCodec> ${required == true ? "required" : null}></textarea>
    <h5><small><span id="textarea-limited-message" class="pull-right">${attrs.remove('data-limit')} characters
    left</span></small></h5>
</div>

