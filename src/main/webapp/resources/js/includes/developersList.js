/**
 * Created by de1mos on 8.08.16.
 */
requirejs.config({
    baseUrl: 'resources/js/plugins',
    paths: {
        jquery: 'jquery'
    }
});

define(["jquery"], function() {

    $(function () {
        setButtonsHandlers();
    });

    function setButtonsHandlers() {
        $('.expand-collapse').click(function() {
           var $this = $(this);
            if($this.hasClass("fa-plus-square-o")) {
                $this.removeClass("fa-plus-square-o").addClass("fa-minus-square-o");
                $this.parent().next().show();
            } else {
                $this.addClass("fa-plus-square-o").removeClass("fa-minus-square-o");
                $this.parent().next().hide();
            }
        });
    }

});