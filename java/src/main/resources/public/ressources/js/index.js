function login() {
  let email = document.getElementById("email").value;
  let password = document.getElementById("password").value;

  fetch("http://localhost:8080/api/users/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ password, email })
  }).then(function(response) {
    if (response.status == 200)
      response.text().then(function(text) {
        console.log();
        window.location = "/html/match.html?uuid=" + JSON.parse(text).uuid;
      });

    console.log();
  });
}

function loadData() {
  fetch(
    "http://localhost:8080/api/users/" +
      new URLSearchParams(window.location.search).get("uuid"),
    {
      method: "GET",
      headers: {
        "Content-Type": "application/json"
      }
    }
  ).then(function(response) {
    if (response.status == 200)
      response.text().then(function(text) {
        let user = JSON.parse(text);

        for (let match of user.matchList) {
          console.log(match);
          fetch("http://localhost:8080/api/users/" + match, {
            method: "GET",
            headers: {
              "Content-Type": "application/json"
            }
          }).then(function(response) {
            if (response.status == 200)
              response.text().then(function(usjson) {
                let userMatch = JSON.parse(usjson);
                document.getElementById("matchlist").innerHTML +=
                  '<li><img src="' +
                  userMatch.picUrl +
                  "\" alt='' /><h3>" +
                  userMatch.userName +
                  " </h3><h4>Developpeur</h4></li>";
              });
          });
        }

        for (let conv of user.convList) {
          fetch("http://localhost:8080/api/conv/" + conv, {
            method: "GET",
            headers: {
              "Content-Type": "application/json"
            }
          }).then(function(response) {
            if (response.status == 200)
              response.text().then(function(convjson) {
                let convMatch = JSON.parse(convjson);
                let last;
                for (let messages in convMatch.usersMessages) {
                  last = convMatch.usersMessages[messages].lastMessage;
                  fetch("http://localhost:8080/api/users/" + messages, {
                    method: "GET",
                    headers: {
                      "Content-Type": "application/json"
                    }
                  }).then(function(response) {
                    if (response.status == 200) {
                      response.text().then(function(userjson) {
                        let userMatch = JSON.parse(userjson);
                        document.getElementById("convlist").innerHTML +=
                          "<div>" +
                          "<div>" +
                          "<img src='" +
                          userMatch.picUrl +
                          "' alt='' />" +
                          "<h3>" +
                          userMatch.userName +
                          "</h3>" +
                          "<h4>Developpeur</h4>" +
                          "</div>" +
                          "<p>" +
                          convMatch.lastMessage.content +
                          "</p>" +
                          "</div>";
                      });
                    }
                  });
                }
              });
          });
        }
      });
  });
}

function goProfil(){
        window.location = "/html/profil.html?uuid=" + new URLSearchParams(window.location.search).get("uuid");

}
function goMatch(){
        window.location = "/html/match.html?uuid=" + new URLSearchParams(window.location.search).get("uuid");

}
function goConv(){
        window.location = "/html/conv.html?uuid=" + new URLSearchParams(window.location.search).get("uuid");

}