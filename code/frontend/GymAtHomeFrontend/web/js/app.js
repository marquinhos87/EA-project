REQUEST_IP = "192.168.1.53";
REQUEST_PORT = "8081"
REQUEST_PATH = "/GymAtHome/api/getUsernameByRequestId"

function getRequest(username, token, id) {
    document.getElementById('#weight').innerHTML = 'data changed';
    var json = "{\n" +
        "    \"username\": \"" + username + "\",\n" +
        "    \"token\": \"" + token + "\",\n" +
        "    \"id\":" + id + "\n" +
        "}"
    url = REQUEST_IP + ":" + REQUEST_PORT + REQUEST_PATH
    var x = $.post(url, json).done(function (data) {
        responseJSON = JSON.parse(data)
        $("#weight").html(responseJSON.weight)
    })
}
