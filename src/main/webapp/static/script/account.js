$(function () {
    $("#captcha-image").on('click', function () {
        $("#captcha-image").attr('src', '/code.jpg?' + Math.floor(Math.random() * 100)).fadeIn();
    });
    $("#getCode").on('click', function () {
        let obj = $(this);
        let flag = obj.attr("disabled");
        if (flag !== undefined && flag === 'disabled') {
            layer.msg("请勿频繁操作！", {icon: 2});
            return false;
        }
        const $email = $("#email").val();
        const email = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;

        if (!email.test($email)) {
            layer.msg("邮箱格式不正确！", {icon: 2});
            return false;
        } else {
            let count = 60;
            const countDown = setInterval(function () {
                if (count === 0) {
                    obj.removeAttr('disabled');
                    $("#getCodeTxt").text('重新发送')
                    clearInterval(countDown);
                } else {
                    obj.attr('disabled', true);
                    $("#getCodeTxt").text(count + '秒后可重新获取');
                }
                count--;
            }, 1000);
            getEmailCode($email);
        }
    });

});

function getEmailCode(email) {
    const index = layer.load(1);
    $.ajax({
        url: '/email?email=' + email,
        type: "POST",
        success: function (msg) {
            layer.close(index);
            if (msg.success) {
                layer.msg("验证码发送成功", {icon: 1});

            } else {
                layer.msg(msg.message, {icon: 2});
            }
        },
        dataType: "json"
    })
}

