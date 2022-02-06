/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var loadFile = function (event) {
    var image = document.getElementById("output");
    image.src = URL.createObjectURL(event.target.files[0]);
};
// https://stackoverflow.com/questions/940577/javascript-regular-expression-email-validation
var emailPattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
var phonePattern = /\d/g;
//var errorEmail = $('#errorEmailMessage').html();
function validationEdit() {
    // Validate empty   
//    var x = document.forms["addNewUser"]["email"].value;
//    if (x == null || x == "") {
//        alert("Email cannot be empty..!!");
//        return false;
//    }

    var email = document.forms["editUser"]["email"];
    var phone = document.forms["editUser"]["phone"];
    var avatar = document.forms["editUser"]["image"];
    if (!emailPattern.test(email.value)) {
//        errorEmail = "Email is not in the right format !!!";
        alert("Email is not in the right format !!!");
        return false;
    }
    if (!phonePattern.test(phone.value)) {
        alert("Phone is not in correct format !!!");
        return false;
    }
    if (phone.value.length !== 10) {
        alert("Phone must be 10 numbers !!!");
        return false;
    }
    // CHUA CHECK DC
    if (document.getElementById("file").files.length === 0) {
        alert("Please choose an image !!!");
        return false;
    }
    alert(document.forms["editUser"]["name"]);
}

function removeSpaces(string) {
    return string.replace(/\s+/g, ' ').trim();

}
