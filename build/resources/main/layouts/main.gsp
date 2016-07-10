<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <asset:javascript src="manifests/main.js"/>
    <title>
        Paperbox Pilot
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet href="bootstrap.css"/>
    <asset:stylesheet href="paper_kit/ct-paper.css"/>
    <asset:stylesheet href="final_tiles/finaltilesgallery.css"/>

    <!-- Fonts and icons -->
    <asset:stylesheet href="themify-icons.css"/>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">

    <g:layoutHead/>
</head>
<body>
<g:render template='/includes/navbar_2' />
    <div class="section section-nude">
            <g:render template='/includes/ajaxLogin'/>
            <g:layoutBody/>
    </div>

<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-sm-9">
                <div class="links">
                    <ul class="uppercase-links">
                        <li>
                            <a href="#">
                                Home
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Company
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Team
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Blog
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Contact
                            </a>
                        </li>


                    </ul>
                    <hr>

                    <div class="copyright">
                        © 2016 Paperbox pilot, made with love
                    </div>
                </div>
            </div>

            <div class="col-md-4 col-md-offset-2 col-sm-2 col-sm-offset-1">
                <div class="social-area">
                    <a class="btn btn-icon btn-facebook btn-fill">
                        <i class="ti-facebook"></i>
                    </a>
                    <a class="btn btn-icon btn-twitter btn-fill">
                        <i class="ti-twitter"></i>
                    </a>
                    <a class="btn btn-icon btn-google btn-fill">
                        <i class="ti-google"></i>
                    </a>
                    <a class="btn btn-icon btn-pinterest btn-fill">
                        <i class="ti-pinterest"></i>
                    </a>
                </div>
            </div>
        </div>

    </div>
</footer>
</body>
</html>
