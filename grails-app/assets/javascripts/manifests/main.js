/**
 * Created by barbarakushnarenko on 5/06/2016.
 */
// FileName: main.js
//= require jquery-1.10.2.js
//= require jquery-ui-1.10.4.custom.min.js
//= require bootstrap.js
//= require ajaxLogin.js
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $('#spinner').ajaxStart(function() {
            $(this).fadeIn();
        }).ajaxStop(function() {
            $(this).fadeOut();
        });
    })(jQuery);
}


