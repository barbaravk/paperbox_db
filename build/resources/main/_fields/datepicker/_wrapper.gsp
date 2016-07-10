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
    <div class="input-group border-input">
        <input name="${property}" id="${property}" value="${value}" type="${attrs.remove('type')}"
           class="form-control datepicker ${attrs.remove('inputClass')}"
           placeholder="${attrs.remove('placeholder')}"
            <g:applyCodec encodeAs="none"> ${otherAttributes(attrs)} </g:applyCodec>
            ${required == true ? "required" : null}/>
        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
    </div>
    <p class="help-block with-errors pull-left"></p>
</div>


