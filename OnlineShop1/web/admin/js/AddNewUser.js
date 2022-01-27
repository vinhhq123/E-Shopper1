/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// https://stackoverflow.com/questions/940577/javascript-regular-expression-email-validation
var emailPattern =  /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
//var errorEmail = $('#errorEmailMessage').html();
function validation() {
   // Validate empty   
//    var x = document.forms["addNewUser"]["email"].value;
//    if (x == null || x == "") {
//        alert("Email cannot be empty..!!");
//        return false;
//    }
    
    var email = document.forms["addNewUser"]["email"];
    if(!emailPattern.test(email.value)){
//        errorEmail = "Email is not in the right format !!!";
        alert("Email is not in the right format !!!");
        return false;
    }
}