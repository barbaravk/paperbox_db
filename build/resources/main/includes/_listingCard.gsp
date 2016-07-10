<div class="col-lg-3 col-md-4 col-sm-6">
    <div class="card card-image">
        <a href="${request.contextPath}/listing/show/${listing.id}">
            <img src="${request.contextPath}/listing/displayBackgroundImage/${listing.id}" class="image">
        </a>
        <div class="details">
            <a href="#">
                <div class="author">
                    <div class="image">
                        <img src="${request.contextPath}/listing/displayProfilePicture/${user.id}"
                             class="img-no-padding img-responsive">
                    </div>
                    <span class="name"
                          style="display: block; margin-right: 30%; padding-bottom: 5px">${listingName}</span>
                    <div class="meta">${listingTagline}</div>
                </div>
            </a>
            <div class="actions">
                <button class="btn btn-lg btn-simple btn-icon">
                    <i class="ti-star"></i>
                </button>
            </div>
        </div>
    </div>
</div>
