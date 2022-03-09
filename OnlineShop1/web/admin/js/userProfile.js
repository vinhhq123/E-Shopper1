/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('#saveC').click(function () {
//            var fullname = $('#newFullname').val();
//            var title = $('#optionsRadios5').val();
//            var phone = $('#newMobile').val();
//            var address = $('#newAddress').val();
//            var uId = $('#currentUserId').val();
        var imageV = $('#file').val();
//            var formData = $("#editProfile").serialize(); //Lấy tất cả data trong form
        var formData = document.getElementById('editProfile');
//        var url = "http://localhost:8080/OnlineShop1/user/updateProfile";
        console.log(imageV);
        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            data: new FormData(formData),
            url: 'user/updateProfile',
            processData: false,
            contentType: false,
            success: function (result) {
                $('#successEditMessage').html(result);
            },
            error: function (result) {
                $("#successEditMessage").html(result);
            }

        });
    });
});
