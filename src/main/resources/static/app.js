var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        var topic = document.getElementById("name").value;
        console.log('Connected: ' + frame);
        console.log('Connecte topic: ' + topic);

//        stompClient.subscribe('/topic/greetings', function (greeting) {
        stompClient.subscribe('/topic/'+topic, function (greeting) {
            //showGreeting(JSON.parse(greeting.body).content);
            showGreeting(greeting.body);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {

    var txt = document.getElementById("txt").value;
    console.log('filter content: ' + txt);

    if(message.includes(txt)){
        $("#greetings").append("<tr><td>" + message + "</td></tr>");
    }else{
        console.log(message+" 不包含 " + txt)
    }

}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

