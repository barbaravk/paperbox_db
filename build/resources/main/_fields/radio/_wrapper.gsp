<%
    def otherAttributes = {attrs ->

        String attrList = ''
        attrs.each{k, v ->
            if (!v){
                attrList = attrList + k + ' '
            }
            else attrList = attrList + k + '=\"' + v + '\" '
        }
        return attrList
    }
%>

<div class="form-group ${attrs.remove('class')}">
    <g:if test="${type != 'hidden'}" >
        <h6 for="${property}">${label} <g:if test="${required}"><span class="icon-danger">*</span></g:if></h6>
    </g:if>
    <div class="row">
        <g:each in="${attrs.remove('options')}">
            <div class="col-md-3">
                <label class="radio">
                    <input type="radio" name="${property}" data-toggle="radio" id="${it.value}"
                           value="${it.value}" <g:if test="${value == it.value}">checked</g:if>
                        ${required == true ? "required" : null}
                        <g:applyCodec encodeAs="none"> ${otherAttributes(attrs)} </g:applyCodec> />
                    <i></i>${it.label}
                </label>
            </div>
        </g:each>
    </div>
        <p class="help-block with-errors"></p>
</div>
