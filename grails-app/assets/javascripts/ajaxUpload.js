/**
 * Created by barbarakushnarenko on 1/05/2016.
 */

$('.fileinput').on('change.bs.fileinput', function (e, image) {
    e.stopPropagation()
    if (image != '') {
        var blob = dataURItoBlob(image);
        var formData = new FormData();
        formData.append('file', blob);
        $.ajax({
            url: '/fileUpload/saveProfilePicture',
            type: 'POST',
            data: formData,
            success: function () {
            },
            cache: false,
            contentType: false,
            processData: false
        });
    }
});

$('#remove-image').click(function () {
    $.ajax({
        url: '/fileUpload/removeProfilePicture',
        type: 'POST',
        success: function () {
            $.ajax({
                url: '/fileUpload/displayProfilePicture',
                type: 'GET',
                success: function () {
                    $('.fileinput-preview').html(
                        '<img src="/fileUpload/displayProfilePicture" alt="Profile picture">')
                }
            })
        }
    });
    $('#hover-wrapper').addClass('hidden');

});

$('#profile-image-preview').hover(function(){
    if($('[data-provides="fileinput"]').hasClass('fileinput-exists')) {
        $('#hover-wrapper').removeClass('hidden')
    }
},
    function(){
        if($('[data-provides="fileinput"]').hasClass('fileinput-exists')) {
            $('#hover-wrapper').addClass('hidden')
        }
    });

function dataURItoBlob(dataURI) {
    'use strict'
    var byteString, mimestring;

    if (dataURI.split(',')[0].indexOf('base64') !== -1) {
        byteString = atob(dataURI.split(',')[1])
    } else {
        byteString = decodeURI(dataURI.split(',')[1])
    }

    mimestring = dataURI.split(',')[0].split(':')[1].split(';')[0]
    var content = new Array();

    for (var i = 0; i < byteString.length; i++) {
        content[i] = byteString.charCodeAt(i)
    }

    return new Blob([new Uint8Array(content)], {type: mimestring});
};



