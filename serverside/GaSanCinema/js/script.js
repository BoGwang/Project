var marketer = {
    init: function () {
        //marketer.login_popup();
        marketer.login_modal();
        marketer.coupon();
    },
    login_popup : function () {
        $('.btn-close, .popup-screen').click(function () {
            $('.popup').hide()
        })
        $('.btn-popup').click(function () {
            $('.popup').show()
        })
    },
    login_modal : function () {
        $('.btn-popup').click(function () {
            $('html').addClass('active_login')
        })
        $('.btn-close, .popup-screen').click(function () {
            $('html').removeClass('active_login')
        })
    },
    coupon : function () {
        window.onload = function () {
            $('html').addClass('active_login')
        }
        $('.btn-close, .popup-screen').click(function () {
            $('html').removeClass('active_login')
        })
    }
}
$(function () {
    marketer.init()
})