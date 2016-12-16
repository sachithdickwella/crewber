$(document).ready(function () {
    $(function () {   
        var hash = window.location.hash;
        hash && $('ul.nav a[href="' + hash + '"]').tab('show');
        $('.nav-tabs a').click(function (e) {
            var uri = window.location.toString();
            if (uri.indexOf("?") > 0) {
                var hash = window.location.hash;
                var clean_uri = uri.substring(0, uri.indexOf("?")) + hash;
                window.history.replaceState({}, document.title, clean_uri);
            }
            $(this).tab('show');
            var scrollmem = $('body').scrollTop() || $('html').scrollTop();
            window.location.hash = this.hash;
            $('html,body').scrollTop(scrollmem);
        });
    });
});