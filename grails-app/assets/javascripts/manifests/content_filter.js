/**
 * Created by barbarakushnarenko on 3/07/2016.
 */
// FileName: manifests/filter.js
//= require bootstrap-select.js
//= require bootstrap-datepicker.js
//= require paper_kit/ct-paper-checkbox.js
//= require paper_kit/ct-paper-radio.js
//= require_self


jQuery(document).ready(function($) {

    $(':checkbox').checkbox();
    $(':radio').radio();
    if($('.datepicker').length != 0){
        $('.datepicker').datepicker({
            weekStart:1,
            color: '{color}'
        });
    };


    $(':checkbox').on('toggle', function() {
        applyFilter();
    });

    $('.datepicker').on('changeDate', function(){
        applyFilter();
    });

    function applyFilter() {
        var formData = new FormData();
        var checkedBoxes = $('.checkbox.checked')
        var date = $(':input.datepicker').val()
        var filterItemsArray = {}

        //collate checkbox selections
        checkedBoxes.each(function(index){
            var i = index + 1
            var valueType = $(this).find(':input').attr('name')
            var value = $(this).find(':input').val()
            var filterItemObject = { 'valueType' : valueType, 'value' : value }
            filterItemsArray['filterItem_'+i] = filterItemObject
        });

        //add event date to list of criteria
        if (date != '') {
            var dateObject = {'valueType' : 'eventDate', 'value' : date};
            filterItemsArray['eventDate'] = dateObject;
        }

        var itemsJson = JSON.stringify(filterItemsArray);
        formData.append('filterItems', itemsJson);
        $.ajax({
            url: '/listing/filter',
            type: 'POST',
            data: formData,
            success: function (data) {
                $('.cd-gallery').html(data.htmlOutput);
            },
            dataType: 'json',
            cache: false,
            contentType: false,
            processData: false
        });
    };

    //open/close lateral filter
    $('.cd-filter-trigger').on('click', function () {
        (filterVisible) ? triggerFilter(false) : triggerFilter(true)
    });
    $('.cd-filter .cd-close').on('click', function () {
        triggerFilter(false);
    });

    var filterVisible = false;
    function triggerFilter($bool) {
        filterVisible = $bool;
        var elementsToTrigger = $([$('.cd-filter-trigger'), $('.cd-filter'), $('.cd-tab-filter'), $('.cd-gallery'), $('.cd-close')]);
        elementsToTrigger.each(function () {
            $(this).toggleClass('filter-is-visible', $bool);
        });
    }

    //mobile version - detect click event on filters tab
    var filter_tab_placeholder = $('.cd-tab-filter .placeholder a'),
        filter_tab_placeholder_default_value = 'Select',
        filter_tab_placeholder_text = filter_tab_placeholder.text();

    $('.cd-tab-filter li').on('click', function (event) {
        //detect which tab filter item was selected
        var selected_filter = $(event.target).data('type');

        //check if user has clicked the placeholder item
        if ($(event.target).is(filter_tab_placeholder)) {
            (filter_tab_placeholder_default_value == filter_tab_placeholder.text()) ? filter_tab_placeholder.text(filter_tab_placeholder_text) : filter_tab_placeholder.text(filter_tab_placeholder_default_value);
            $('.cd-tab-filter').toggleClass('is-open');

            //check if user has clicked a filter already selected
        } else if (filter_tab_placeholder.data('type') == selected_filter) {
            filter_tab_placeholder.text($(event.target).text());
            $('.cd-tab-filter').removeClass('is-open');

        } else {
            //close the dropdown and change placeholder text/data-type value
            $('.cd-tab-filter').removeClass('is-open');
            filter_tab_placeholder.text($(event.target).text()).data('type', selected_filter);
            filter_tab_placeholder_text = $(event.target).text();

            //add class selected to the selected filter item
            $('.cd-tab-filter .selected').removeClass('selected');
            $(event.target).addClass('selected');
        }
    });

    //close filter dropdown inside lateral .cd-filter
    $('.cd-filter-block h4').on('click', function () {
        if ($(this).find('i').hasClass('fa-chevron-down')) {
            $(this).find('i').removeClass('fa-chevron-down');
            $(this).find('i').addClass('fa-chevron-up');
        } else {
            $(this).find('i').removeClass('fa-chevron-up');
            $(this).find('i').addClass('fa-chevron-down');
        }
        $(this).toggleClass('closed').siblings('.cd-filter-content').slideToggle(300);
    })

    //fix lateral filter and gallery on scrolling
    $(window).on('scroll', function () {
        (!window.requestAnimationFrame) ? fixGallery() : window.requestAnimationFrame(fixGallery);
    });

    function fixGallery() {
        var offsetTop = $('.cd-main-content').offset().top;
        var offsetBottom = Math.round($('.footer').offset().top - $(window).height());
            scrollTop = $(window).scrollTop();
        (scrollTop > offsetTop && scrollTop < offsetBottom ) ? $('.cd-main-content').addClass('is-fixed') : $('.cd-main-content').removeClass('is-fixed');
    }
});
