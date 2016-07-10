<div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <a class="navbar-nav navbar-brand-paperbox" href="/#">
                <span style="color: #8f3e50">paper</span><span style="color: #fc5e53">box</span>
            </a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right" id="navbarLogin">
                    <sec:ifLoggedIn>
                        <li>
                            <a href="#" class="btn btn-primary btn-sm btn-icon" data-toggle="dropdown">
                                <i class="ti-email"></i>
                            </a>
                            <span class="label label-warning notification-bubble">2</span>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="profile-photo dropdown-toggle" data-toggle="dropdown"
                               aria-expanded="false">
                                <div class="profile-photo-small">
                                    <img src="${request.contextPath}/fileUpload/displayProfilePicture"
                                                 class="img-circle img-responsive img-no-padding" />
                                </div>
                                <p class="text-primary small" style="float: left; margin: 27px 5px;">
                                    Hi, <sec:loggedInUserInfo field="firstName"/>
                                </p>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li><a href="#">Me</a></li>
                                <li><a href="#">Settings</a></li>
                                <li class="divider"></li>
                                <li><a href="${request.contextPath}/logout" id="_logout">Logout</a></li>
                            </ul>
                        </li>
                    </sec:ifLoggedIn>
                    <sec:ifNotLoggedIn>
                        <a class="btn btn-primary btn-simple" data-toggle="modal"
                           data-target="#ajaxLogin">
                            Login</a>
                    </sec:ifNotLoggedIn>
            </ul>
        </div>
</div>
