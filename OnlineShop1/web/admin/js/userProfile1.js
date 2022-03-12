/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('#saveC').click(function () {
        var fullname = $('#newFullname').val();
//            var title = $('#optionsRadios5').val();
        var phone = $('#newMobile').val();
        var address = $('#newAddress').val();
//            var uId = $('#currentUserId').val();
        var patternPhone = /^[0-9]{10}$/;
        var imageV = $('#file').val();
//            var formData = $("#editProfile").serialize(); //Lấy tất cả data trong form
        var formData = document.getElementById('editProfile');
//        var url = "http://localhost:8080/OnlineShop1/user/updateProfile";
        console.log(imageV);
        if (fullname.length === 0) {
            $("#errorFullName").html('Fullname cannot be blalnk !!!');
        } else if (address.length === 0) {
            $("#errorAddress").html('Address cannot be blalnk !!!');
        } else if (phone.length === 0) {
            $("#errorPhone").html('Mobile cannot be blalnk !!!');
        } else if (phone.length !== 10) {
            $("#errorPhone").html('Mobile must be 10 digits');
        } else if (!patternPhone.test(phone)) {
            $("#errorPhone").html('Mobile can contains only digits');
        } else {
            $.ajax({
                type: 'POST',
                enctype: 'multipart/form-data',
                data: new FormData(formData),
                url: 'user/updateProfile',
                processData: false,
                contentType: false,
                success: function (result) {
                    $("#errorPhone").html('');
                    $("#errorAddress").html('');
                    $("#errorFullName").html('');
                    $('#successEditMessage').html(result);
//                    $('#successEditMessage').delay(5000).html('');
                    setTimeout(function () {
                        $('#successEditMessage').html('');
                    }, 5000);
                },
                error: function (result) {
                    $("#successEditMessage").html(result);
                }
            });
        }
    });
});
