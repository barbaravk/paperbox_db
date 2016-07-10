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

    <!-- Fonts and icons -->
    <asset:stylesheet href="themify-icons.css"/>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
    <g:layoutHead/>
</head>
<body>
<g:render template='/includes/navbar_2'/>
<g:render template='/includes/ajaxLogin'/>
    <div id="section list-listing" class="listing index" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <main class="cd-main-content section-nude">
        <div class="cd-tab-filter-wrapper">
            <span class="cd-filter-trigger"
                  style="opacity: 0.3; font-size: 1.3em"><i class="fa fa-filter"></i> </span>

            <div class="cd-filter-content input-group" style="width: 70%; margin-left: 15%; position: absolute">
                <span class="input-group-addon"
                      style="opacity: 0.3; font-size: 1.3em"><i class="fa fa-search"> </i></span>
                <input class="form-control" type="search" placeholder="search" style="height: 50px; border-radius: 0">
            </div>

            <div class="cd-filter-content input-group" style="width: 15%; margin-left: 85%">
                <span class="input-group-addon" style="opacity: 0.3; font-size: 1.3em"><i class="fa fa-calendar"></i></span>
                <input name="date" id="date" value="" type="text"
                       class="form-control datepicker"
                       placeholder="event date" style="height: 50px; border-radius: 0" />
            </div>
        </div>

            <section class="cd-gallery">
                <g:layoutBody/>
                <div class="cd-fail-message">No results found</div>
            </section> <!-- cd-gallery -->


        <div class="cd-filter">
            <form>
                <div class="cd-filter-block">
                    <h4><i class="fa fa-chevron-up"></i> Listing Category</h4>
                    <div class="cd-filter-content cd-filters list">
                        <g:each in="${com.paperbox.ListingCategory.list()}">
                        <div class="checkbox">
                            <input type="checkbox" name="listingCategory" value="${it.id}" data-toggle="checkbox">
                            ${it.categoryName}
                        </div>
                        </g:each>
                    </div> <!-- cd-filter-content -->
                </div> <!-- cd-filter-block -->

                <div class="cd-filter-block">
                    <h4><i class="fa fa-chevron-up"></i> Location</h4>
                    <div class="cd-filter-content">
                        <div class="cd-select cd-filters">
                            <select name="huge" class="selectpicker" data-style="form-control" data-menu-style="">
                                <option disabled selected>select option</option>
                                <option value="1">option 1</option>
                                <option value="1">option 2</option>
                            </select>
                        </div> <!-- cd-select -->
                    </div> <!-- cd-filter-content -->
                </div> <!-- cd-filter-block -->

                <div class="cd-filter-block">
                    <h4><i class="fa fa-chevron-up"></i> Radio buttons</h4>
                    <div class="cd-filter-content cd-filters">
                        <label class="radio">
                            <input type="radio" name="optionsRadios" data-toggle="radio" id="optionsRadios1" value="option1">
                            <i></i>radio is off
                        </label>
                        <label class="radio">
                            <input type="radio" name="optionsRadios" data-toggle="radio" id="optionsRadios1"
                                   value="option1" checked>
                            <i></i>radio is on
                        </label>
                    </div> <!-- cd-filter-content -->
                </div> <!-- cd-filter-block -->
            </form>


        </div> <!-- cd-filter -->
    </main>
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
