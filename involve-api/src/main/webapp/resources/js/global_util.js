function ajaxCall(http, url, data, sucessCallback, errorCallback) {
    http(
            {
                method: "POST",
                url: url,
                contentType: "application/json; charset=utf-8",
                data: data
            }).
            success(function (data, status, headers, config) {
                if (data.errorCode && data.errorCode >= 700) {
                    alert(data.errorCode + ": " + data.message);
                } else {
                    sucessCallback(data);
                }
            }).
            error(function (data, status, headers, config) {
                if (status == 701) {
                    window.location = data;
                }
                if (errorCallback) {
                    errorCallback(data);
                } else {
                    alert(data);
                }
            });
}
function ajaxCallNotJsonContentType(http, url, data, sucessCallback, errorCallback) {
    http(
            {
                type: 'POST',
                url: url,
                dataType: 'text',
                processData: false,
                contentType: false,
                data: data
            }).
            success(function (data, status, headers, config) {
                sucessCallback(data);
            }).
            error(function (data, status, headers, config) {
                if (errorCallback)
                    errorCallback(data);
                else {
                    alert(data.resposeText);
                }
            });
}
