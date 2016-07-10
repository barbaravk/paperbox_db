<html>
<head>
    <meta name="layout" content="${gspLayout ?: 'main'}"/>
    <asset:javascript src="application.js"/>
    <title><g:message code='springSecurity.login.title'/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4 text-center">
            <div class="header no-border-header" id="registerHeader">
                <h3 class="title">Welcome to <br> <span class="paperbox">paperbox</span></h3>
            </div>
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
            <form action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm"
                  data-toggle="validator" role="form">
                <div class="form-group" id="usernameInput">
                    <input type="text" value="" id="username" name="username" placeholder="Email"
                           class="form-control border-input" required>
                </div>

                <div class="form-group">
                    <input type="password" value="" id="password" name="password" placeholder="Password"
                           class="form-control border-input" required>
                </div>

                <div class="form-group">
                    <input class="btn btn-fill btn-block" type="submit" id="authLogin" value="Log in"/>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
