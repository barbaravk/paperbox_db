/**
 * Created by barbarakushnarenko on 3/06/2016.
 */
if (typeof jQuery === 'undefined') { throw new Error('Galleryinput\'s JavaScript requires jQuery') }

+function ($) { "use strict";

    var isIE = window.navigator.appName == 'Microsoft Internet Explorer'

// GALLERYINPUT PUBLIC CLASS DEFINITION
// =================================

var Galleryinput = function (element, options) {
    this.$element = $(element)
    this.$gallery = $('#gallery')
    this.$items = $('.ftg-items')

    this.tile =
        "<div class='tile'>" +
            "<a class='tile-inner'>" +
                "<img class='item'" +
                "src='data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP\/\/\/yH5BAEAAAAALAAAAAABAAEAAAIBRAA7' id='image'/>" +
                "<span class='title' id='title'>Click to add title</span>" +
                "<span class='subtitle' id='subtitle'>Click to add subtitle</span>" +
           "</a>" +
            "<div class='ftg-social'>" +
                "<a class='clear-image' id='clearImage'><i class='fa fa-trash-o'></i></a>" +
            "</div>" +
        "</div>"

    this.$input = this.$element.find(':file')
    if (this.$input.length === 0) return

    this.name = this.$input.attr('name') || options.name

    this.listen()
}

Galleryinput.prototype.listen = function() {
    this.$input.on('change.bs.galleryinput', $.proxy(this.change, this))

    this.$element.find('[data-trigger="galleryinput"]').on('click.bs.galleryinput', $.proxy(this.trigger, this))
    this.$element.find('[data-dismiss="galleryinput"]').on('click.bs.galleryinput', $.proxy(this.clear, this))
    this.$items.on('click', '.clear-image', $.proxy(this.clear, this))
},

    Galleryinput.prototype.change = function(e) {
        var files = e.target.files === undefined ? (e.target && e.target.value ? [{name: e.target.value.replace(/^.+\\/, '')}] : []) : e.target.files

        e.stopPropagation()

        var file = files[0]

        if (file) {
            if ((typeof file.type !== "undefined" ? file.type.match(/^image\/(gif|png|jpeg)$/) : file.name.match(/\.(gif|png|jpe?g)$/i)) && typeof FileReader !== "undefined") {
                var reader = new FileReader(),
                    element = this.$element,
                    gallery = this.$gallery,
                    items = this.$items,
                    tile = $(this.tile)

                reader.onload = function (re) {
                    var image = imageResize(re)
                    element.find('.galleryinput-filename').text(file.name)
                    tile.find('.item').attr('data-src', image.src)

                    if (gallery.data('finalTilesGallery') == undefined) {
                        items.append(tile)
                        gallery.finalTilesGallery({
                            minTileWidth: 200,
                            margin: 5
                        })
                    }
                    else gallery.data('finalTilesGallery').addElements(tile)
                    var image_id = 'image_' + $('.tile').length
                    var title_id = 'imageTitle_' + $('.tile').length
                    var subtitle_id = 'imageSubtitle_' + $('.tile').length
                    var clear_id = 'clearImage_' + $('.tile').length
                    tile.find('span.title').attr('id', title_id)
                    tile.find('span.subtitle').attr('id', subtitle_id)
                    tile.find('img').attr('id', image_id)
                    tile.find('a.clear-image').attr('id', clear_id)
                    $('#'+ title_id).editable('click', function(e){
                        e.target.addClass('edited')
                    })
                    $('#'+ subtitle_id).editable('click', function(e){
                        e.target.addClass('edited')
                    })

                    element.trigger('change.bs.galleryinput', image.src)
                }
                reader.readAsDataURL(file)
            } else {
                this.$element.trigger('change.bs.galleryinput')
            }
        }
    },

    Galleryinput.prototype.clear = function(e) {
        if (e) e.preventDefault()
        this.$input.attr('name', '')

        //ie8+ doesn't support changing the value of input with type=file so clone instead
        if (isIE) {
            var inputClone = this.$input.clone(true);
            this.$input.after(inputClone);
            this.$input.remove();
            this.$input = inputClone;
        } else {
            this.$input.val('')
        }

        var clearId = e.currentTarget.id.match(/\d+/)[0] - 1

        if ($('.tile').length == 1) {
            this.$gallery.data('finalTilesGallery').clear()
        } else  this.$gallery.data('finalTilesGallery').removeAt(clearId)

        this.$element.trigger('clear.bs.galleryinput')

        if (e !== undefined) {
            this.$input.trigger('change')
        }
    },

    Galleryinput.prototype.trigger = function(e) {
        this.$input.trigger('click')
        e.preventDefault()
    }


// GALLERYINPUT PLUGIN DEFINITION
// ===========================

var old = $.fn.Galleryinput

$.fn.Galleryinput = function (options) {
    return this.each(function () {
        var $this = $(this),
            data = $this.data('bs.galleryinput')
        if (!data) $this.data('bs.galleryinput', (data = new Galleryinput(this, options)))
        if (typeof options == 'string') data[options]()
    })
}

$.fn.Galleryinput.Constructor = Galleryinput


// GALLERYINPUT NO CONFLICT
// ====================

$.fn.Galleryinput.noConflict = function () {
    $.fn.Galleryinput = old
    return this
}


// GALLERYINPUT DATA-API
// ==================

$(document).on('click.galleryinput.data-api', '[data-provides="galleryinput"]', function (e) {
    var $this = $(this)
    if ($this.data('bs.galleryinput')) return
    $this.Galleryinput($this.data())

    var $target = $(e.target).closest('[data-dismiss="galleryinput"],[data-trigger="galleryinput"]');
    if ($target.length > 0) {
        e.preventDefault()
        $target.trigger('click.bs.galleryinput')
    }
})

}(window.jQuery);

function imageResize (i) {
    var image = new Image()
    image.src = i.target.result
    var h = image.height
    var w = image.width

    if (h >= 960) {
        w = w * (960 / h)
        h = 960
    }
    if (w >= 1440) {
        h = h * (1440 / w)
        w = 1440
    }

    var mainCanvas = document.createElement("canvas")
    mainCanvas.width = w
    mainCanvas.height = h
    var ctx = mainCanvas.getContext("2d")

    ctx.drawImage(image, 0, 0, w, h)
    image.src = mainCanvas.toDataURL("image/png")
    return image
}

function galleryItemResize (i) {
    var count = $('.item').length + 1
    var image = new Image()
    image.src = i.target.result
    var h = image.height
    var w = image.width

    if (h >= 960) {
        w = w * (960 / h)
        h = 960
    }
    if (w >= 1440) {
        h = h * (1440 / w)
        w = 1440
    }

    var mainCanvas = document.createElement("canvas")
    mainCanvas.width = w
    mainCanvas.height = h
    var ctx = mainCanvas.getContext("2d")

    ctx.drawImage(image, 0, 0, w, h)
    image.src = mainCanvas.toDataURL("image/png")
    return image
}
