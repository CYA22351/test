$(document).ajaxSend(function(event,xhr,options){
    xhr.setRequestHeader("user_token",localStorage.getItem(("userToken")))
});
$(document).ajaxError(function(event,xhr,options,exc){
    if (xhr.status==401){
        location.href="blog_Login.html";
    }
})