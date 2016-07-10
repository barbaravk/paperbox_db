<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main"/>
    <asset:javascript src="application.js"/>
    <s2ui:title messageCode='spring.security.ui.register.title'/>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4 text-center">
            <div class="header no-border-header" id="registerHeader">
                <h3 class="title">Welcome to <br> <span class="paperbox">paperbox</span></h3>
            </div>
            <g:if test='${!emailSent}'>
                <a class="btn btn-fill btn-block btn-facebook"
                   href="${request.contextPath}/j_spring_security_facebook_redirect">
                    Sing in with
                    <i class="ti-facebook" style="font-size: 15px"
                       href="${request.contextPath}/j_spring_security_facebook_redirect"></i>
                </a>

                <div class="division">
                    <div class="line l"></div>
                    <span>or</span>

                    <div class="line r"></div>
                </div>
            </g:if>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 col-md-offset-4 text-center">
            <form action="${request.contextPath}/register/register" method="POST" name="registerForm"
                  id="registerForm" data-toggle="validator" role="form">
                <g:if test='${emailSent}'>
                    <br/>
                    <g:message code='spring.security.ui.register.sent'/>
                </g:if>
                <g:else>
                    <div class="form-group" id="firstNameInput">
                            <input type="text" value="" id="firstName" name="firstName" placeholder="First name"
                                   class="form-control border-input" data-error="First name required" required>
                        <p class="help-block with-errors pull-left"></p>
                    </div>

                    <div class="form-group" id="emailInput">
                            <input type="email" value="" id="email" name="email" placeholder="Email"
                                   class="form-control border-input" data-error="Please provide valid email" required>
                        <p class="help-block with-errors pull-left"></p>
                    </div>

                    <div class="form-group">
                            <input type="password" value="" id="inputPassword" name="password" placeholder="Password"
                                   class="form-control border-input" data-password="validate"
                                   data-password-error="Password must contain upper case letters and numbers"
                                   data-minlength="8"
                                   data-minlength-error="Password must be at least 8 characters long"
                                   data-error="Password required" required>
                        <p class="help-block with-errors pull-left"></p>
                    </div>

                    <div class="form-group">
                            <input type="password" value="" id="password2" name="password2"
                                   placeholder="Repeat password"
                                   class="form-control border-input" data-match="#inputPassword"
                                   data-error="Please repeat password"
                                   data-match-error="Passwords do not match"
                                   required>
                        <p class="help-block with-errors pull-left"></p>
                    </div>

                    <div class="form-group">
                        <input class="btn btn-fill btn-block" type="submit" id="submit"
                               value="Create account"/>
                    </div>
                </g:else>
            </form>
        </div>
    </div>
</div>
</body>
</html>
