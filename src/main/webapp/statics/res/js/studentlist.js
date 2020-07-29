var sHead = null;
var errorInfo = null;
$(function () {
    sHead = $("#sHead");
    errorInfo = $("#errorInfo");
    if (errorInfo.val() == null || errorInfo.val() == "") {
        sHead.next().html("*图片大小请保持在2M以下*图片格式必须是jpg,jpeg,png,pneg");
    } else {
        sHead.next().html(errorInfo.val());
    }
})