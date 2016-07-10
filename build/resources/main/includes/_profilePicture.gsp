<h6>Profile Picture</h6>
<div class="content no-padding" id="profile-image-preview">
    <div class="image fileinput-preview" data-trigger="fileinput">
        <img src="${request.contextPath}/fileUpload/displayProfilePicture" class="image" alt="Profile picture">
    </div>

    <div class="hidden" id="hover-wrapper">
        <a class="btn btn-icon btn-primary btn-fill btn-tooltip btn-overlay" data-toggle="tooltip" data-placement="top"
           data-original-title="remove" id="remove-image" data-dismiss="fileinput">
            <i class="fa fa-trash-o"></i>
        </a>
    </div>
</div>

<div class="content text-center" style="margin-top: 20px">
    <span class="btn btn-primary btn-file">
        <span class="fileinput-new"><i class="fa fa-plus-circle" aria-hidden="true"></i>Add Photo</span>
        <span class="fileinput-exists"><i class="fa fa-pencil" aria-hidden="true"></i>Change</span>
        <input type="file" class="photo-upload" name="..."></span>
</div>

