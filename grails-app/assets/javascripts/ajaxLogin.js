/**
 * Created by barbarakushnarenko on 3/04/2016.
 */
var onLogin;

$.ajaxSetup({
    beforeSend: function(jqXHR, event) {
        if (event.url != $("#ajaxLoginForm").attr("action")) {
            onLogin = event.success;
        }
    },
    statusCode: {
        401: function() {
            showLogin();
        }
    }
});

function logout(event) {
    event.preventDefault();
    $.ajax({
        url: $("#_logout").attr("href"),
        method: "POST",
        success: function(data, textStatus, jqXHR) {
            window.location = "/";
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("Logout error, textStatus: " + textStatus +
                ", errorThrown: " + errorThrown);
        }
    });
}
;
function authAjax() {
    $("#loginMessage").html("Sending request ...").show();

    var form = $("#ajaxLoginForm");
    $.ajax({
        url:      form.attr("action"),
        method:   "POST",
        data:      form.serialize(),
        dataType: "JSON",
        success: function(json, textStatus, jqXHR) {
            if (json.success) {
                form[0].reset();
                $("#loginMessage").empty();
                $("#ajaxLogin").modal('hide');
                $("#navbarLogin").html(
                        '<li>' +
                            '<a href="#" class="btn btn-primary btn-sm btn-icon" data-toggle="dropdown">' +
                                '<i class="ti-email"></i>' +
                            '</a>' +
                            '<span class="label label-warning notification-bubble">2</span>' +
                        '</li>' +
                        '<li class="dropdown">' +
                           '<a href="#" class="profile-photo dropdown-toggle" data-toggle="dropdown"' +
                        ' aria-expanded="false">' +
                                '<div class="profile-photo-small">' +
                                    '<img src="/fileUpload/displayProfilePicture" class="img-circle img-responsive img-no-padding" />' +
                               '</div>' +
                                '<p class="text-primary small" style="float: left; margin: 27px 5px;">'+
                                        'Hi, ' + json.firstName +
                                '</p>' +
                            '</a>' +
                            '<ul class="dropdown-menu dropdown-menu-right">' +
                                '<li><a                             href="#">Me</a></li>' +
                                '<li><a href="#">Settings</a></li>' +
                                '<li class="divider"></li>' +
                                '<li><a href="/logout" id="_logout">Logout</a></li>' +
                            '</ul>' +
                        '</li>'
                );
                $("#logout").click(logout);
                if (onLogin) {
                    onLogin(json, textStatus, jqXHR);
                }
            }
            else if (json.error) {
                $("#loginModalHeader").addClass("modal-has-error");
                $("#loginModalHeader").html('<div class="icon icon-danger pull-left">'+
                    '<i class="ti-info icon-danger" style="font-size: 36px"></i>'+
                    '</div>'+'<span>' +
                    json.error + "</error>");
            }
            else {
                $("#loginMessage").html(jqXHR.responseText);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            if (jqXHR.status == 401 && jqXHR.getResponseHeader("Location")) {
                $("#loginMessage").html('<span class="errorMessage">' +
                    'Sorry, there was a problem with the login request</error>');
            }
            else {
                var responseText = jqXHR.responseText;
                if (responseText) {
                    var json = $.parseJSON(responseText);
                    if (json.error) {
                        $("#loginMessage").html('<span class="errorMessage">' +
                            json.error + "</error>");
                        return;
                    }
                }
                else {
                    responseText = "Sorry, an error occurred (status: " +
                        textStatus + ", error: " + errorThrown + ")";
                }
                $("#loginMessage").html('<span class="errorMessage">' +
                    responseText + "</error>");
            }
        }
    });
}

$(function() {

    //$("#ajaxLogin").on('hidden.bs.modal', function () {
    //    window.alert('hidden event fired!');
    //});

    $("#ajaxLoginForm").submit(function(event) {
        if (!$("#authAjax").hasClass('disabled')) {
            event.preventDefault();
            authAjax();
        }
    });

    $("#authAjax").click(
        function() {
            if (!$("#authAjax").hasClass('disabled')) { authAjax(); }
            else return;
        }
    );
    $("#logout").click(logout);
}
);
