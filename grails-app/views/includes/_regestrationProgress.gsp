<ol class="progress-custom">
    <li class="is-complete" data-step="">
        Create an accout
    </li>
    <li class="is-active">
        Tell us about yourself
    </li>
    <li>
        Tell us about your event
    </li>
    <li class="progress__last">
        Start planning
    </li>
</ol>

<style>
.progress-custom {
    list-style: none;
    margin: 0;
    padding: 0;
    display: table;
    table-layout: fixed;
    width: 100%;
    color: #d7d6d2;
}

.progress-custom > li {
    position: relative;
    display: table-cell;
    text-align: center;
    font-size: 0.8em;
}

.progress-custom > li:before {
    content: attr(data-step);
    display: block;
    margin: 0 auto;
    background: #d7d6d2;
    color: white;
    width: 3em;
    height: 3em;
    text-align: center;
    margin-bottom: 0.25em;
    line-height: 3em;
    border-radius: 100%;
    position: relative;
    z-index: 10;
}

.progress-custom > li:after {
    content: '';
    position: absolute;
    display: block;
    background: #d7d6d2;
    width: 100%;
    height: 0.3em;
    top: 1.25em;
    left: 50%;
    margin-left: 1.5em;
    z-index: 10;
}

.progress-custom > li:last-child:after {
    display: none;
}

.progress-custom > li.is-complete, li.is-active{
    color: #8f3e50;
}

.progress-custom > li.is-complete:before, .progress-custom > li.is-complete:after,
.progress-custom > li.is-active:before {
    color: #FFF;
    background: #8f3e50;
}

/*.progress-custom > li.is-active {*/
    /*color: #FF6D5E;*/
/*}*/

/*.progress-custom > li.is-active:before {*/
    /*color: #FFF;*/
    /*background: #FF6D5E;*/
/*}*/

/**
* Needed for IE8
*/
.progress__last:after {
    display: none !important;
}

/**
* Size Extensions
*/
.progress-custom-custom--medium {
    font-size: 1.5em;
}

.progress-custom--large {
    font-size: 2em;
}

.progress-custom {
    margin-bottom: 30px;
}
</style>
