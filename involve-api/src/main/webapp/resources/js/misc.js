
var DATE_FORMAT = "dd/mm/yy";

function getNextDay() {

    var today = new Date();
    var d = today.getDate();
    var m = today.getMonth();
    var y = today.getFullYear();

    var nextDay = new Date(y, m, d + 1);
    return nextDay;
}
function getCurrentDay() {
    var d = new Date();
    d.setHours(0, 0, 0, 0);
    return d;
}
function getDateById(id) {
    if ($('#' + id).val() != "") {
        var arraydates = $('#' + id).val().split("/");
        var newdate = arraydates[2] + "/" + arraydates[1] + "/" + arraydates[0];
        return new Date(newdate);
    }
}

function isEmpty(id) {
    if ($('#' + id).val() != "")
        return false;
    else
        return true;
}