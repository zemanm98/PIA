// Javascript script used for websocket communication through STOMP library with application
// Has methods for sending and receiving messages for starting and ending ride. Also
// for location updating
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/gs-guide-websocket'
});

var bikeId = 0;
var userId = 0;
var rideId = 0;
var standId = 0;
var stopLoop = 0;

let items = [
  ["49.7307131", "13.3468571"],
  ["49.7369542", "13.3567342"],
  ["49.7398803", "13.3698853"],
  ["49.7468554", "13.3737054"],
  ["49.7478695", "13.3784675"]
];
var loopId = 0;
stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/update/rideStarted', (ridemessage) => {
        rideCreated(JSON.parse(ridemessage.body));
    });
    stompClient.subscribe('/update/location', (locationmessage) => {
        updateLocation(JSON.parse(locationmessage.body));
    });
    startRide(userId, bikeId);
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

window.onload = function(){
var loggedAs = document.getElementById('loggedAs');
var repairBikes = document.getElementById('repairBikes');
var history = document.getElementById('rideHistory');
var logout = document.getElementById('logout');
var info = document.getElementById('info');
var disconnect = document.getElementById('disconnect');
var connect = document.getElementById('connect');

const languageSelect = document.getElementById('language-select');
languageSelect.addEventListener('change', function() {
  if (languageSelect.value === 'cz') {
    loggedAs.textContent = 'Přihlášen jako: ';
    if(repairBikes != null){
      repairBikes.textContent = 'Opravit Kola';
    }
    history.textContent = 'Historie Jízd';
    logout.textContent = 'Odhlásit se';
    info.textContent = 'Jízda může být ukončena pokud jste nejvýše 50 metrů od stojanu.';
    disconnect.textContent = 'Ukončit jízdu s kolem';
    connect.textContent = 'Začít jízdu s kolem';
  } else if (languageSelect.value === 'en') {
    loggedAs.textContent = 'Logged in as: ';
    if(repairBikes != null){
      repairBikes.textContent = 'Bikes To Repair';
    }
    history.textContent = 'See Ride History';
    logout.textContent = 'Log out';
    info.textContent = 'Ride can be ended when within 50 meters of any stand.';
    disconnect.textContent = 'End Ride';
    connect.textContent = 'Ride Bike';
  }
});
}

function setConnected(connected) {
    $("#connect").prop("disabled", connected);

    $("#conversation").html("");
}

function connect(user, bike) {
    stompClient.activate();
    bikeId = bike;
    userId = user;
}

function startRide(userId, bikeId){
    $("#disconnect").prop("disabled", true);
    stompClient.publish({
                destination: "/app/startRide",
                body: JSON.stringify({'bikeId': bikeId,
                                      'userId': userId})
            });
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
    stompClient.publish({
                    destination: "/app/end",
                    body: JSON.stringify({'bikeId': bikeId,
                                          'rideId': rideId,
                                          'standId': standId})
                });
    stopLoop = 1;
    window.location.href = "http://localhost:8080/";
}

function rideCreated(message) {
    if (message.toString().localeCompare("0") == 0){
        disconnect();
    }
    else{
        rideId = message;
        $("#conversation").html("<tr><td>Ride started</td></tr>");
        //sendGeoLocation();
        loopId = 0;
        stopLoop = 0;
        myLoop();

    }
}

function updateLocation(message){
    standId = message;
}

function myLoop() {
  setTimeout(function() {
    sendLocation(loopId);
    if (standId.toString().localeCompare("0") != 0){
        $("#disconnect").prop("disabled", false);
    }
    loopId++;
    if (stopLoop == 0) {
      myLoop();
    }
  }, 5000)
}

function sendLocation(index){
    if (index >= 5){
        index = 4;
    }
    stompClient.publish({
                            destination: "/app/update",
                            body: JSON.stringify({'bikeId': bikeId,
                                                  'latitude': items[index][0],
                                                  'longitude': items[index][1]})
                        });

}

function sendGeoLocation(){
    navigator.geolocation.getCurrentPosition((position) => {
        stompClient.publish({
                        destination: "/app/update",
                        body: JSON.stringify({'bikeId': bikeId,
                                              'latitude': position.coords.latitude,
                                              'longitude': position.coords.longitude})
                    });
    });
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#disconnect" ).click(() => disconnect());
});