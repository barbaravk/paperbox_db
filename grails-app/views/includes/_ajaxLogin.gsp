<span id="logoutLink" style="display: none;">
    <g:link elementId='_logout' controller='logout'>Logout test</g:link>
</span>

<div class="modal fade in" id="ajaxLogin" name="ajaxLogin" tabindex="-1" role="dialog" aria-hidden="false"
     style="display: none;">
    <div class="modal-dialog modal-medium">
        <div class="modal-content ">
            <div class="modal-header no-border-header text-center" id="loginModalHeader">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                <h3 class="modal-title">Welcome to <br> <span class="paperbox">paperbox</span></h3>
            </div>

            <div class="modal-body text-center">
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

                <form action="${request.contextPath}/login/authenticate" method="POST"
                      id="ajaxLoginForm" data-toggle="validator" role="form">
                    <div class="form-group" id="usernameInput">
                        <input type="text" value="" id="username" name="username" placeholder="Email"
                               class="form-control border-input" required>
                    </div>

                    <div class="form-group">
                        <input type="password" value="" id="password" name="password" placeholder="Password"
                               class="form-control border-input" required>
                    </div>

                    <div class="form-group">
                        <input class="btn btn-fill btn-block" type="submit" id="authAjax" value="Log in"/>
                    </div>
                </form>

                <div class="form-group">
                    <a class="btn btn-block btn-primary" href="${request.contextPath}/register">
                        Sign up with email
                    </a>
                </div>

                <p class="form-group text-muted">
                    <a href="#" class="text-primary">Forgot your password?</a>
                </p>
            </div>
        </div>
    </div>
</div>
