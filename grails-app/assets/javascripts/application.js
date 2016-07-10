// FileName: application.js
//= require locationSearch.js
//= require validator.js
//= require bootstrap-datepicker.js
//= require_tree paper_kit
//= require jasny-bootstrap.js
//= require ajaxUpload.js
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


