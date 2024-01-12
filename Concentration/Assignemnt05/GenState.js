var grid = [[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]];
var turn = 0;
var time = 30;
var gameOver = false; 
var matches = 0;

function getSquare(row, col) {
	return grid[row][col];
	}
	
function genState(R,C) {
 
	var table = ''; 
	var rows = R;
	var cols = C;
	var x = 0;  
		
	for (r = 0; r < rows; r++)
		{
			table += '<tr>'; 
				for (c = 0; c < cols; c++)
				{
					var content = getSquare(r,c); 
					if (content == 0)
						{
							table += '<td>'
										 + '<div class="newCard">'
										 		+ '<div class="front" id="card' + x + '" onclick="flipCard(\'card' + x + '\',\'card' + (x+1) + '\')" >' + '</div>'
										 		+ '<div class="back" id="card' + (x+1) + '" onclick="flipCard(\'card' + x + '\',\'card' + (x+1) + '\')" >' + '</div>'
										 + '</div>'
									 + '</td>';
									 x +=2; 	
						}
					else if (content == 1)
						{
							table += '<td class="circleCard">' + '</td>';
						}
					else  
					{
						table += '<td class="unFlippedCard">' + '</td>';		
					}
				}				
			table += '</tr>';	
		}
	document.getElementById('gameGrid').innerHTML = '<table>' + table + '</table>';
}


function writeText() {
   var textInput = document.getElementById("inputText");
   var text = textInput.value;
   var newText = document.getElementById("outPutText"); 
   newText.innerHTML = text; 
}

var counter = 0; 
flipped = false; 

function flipCard(el1, el2) {
	var elem1 = document.getElementById(el1);
	var elem2 = document.getElementById(el2);	 
	
	if (flipped == false) {
		elem1.style.transition= "transform .5s linear 0s";
		elem1.style.transform = "perspective(600px ) rotateY( -180deg)";
		elem2.style.transition= "transform .5s linear 0s";
		elem2.style.transform = "perspective(600px ) rotateY( 0deg)";
		
		flipped = true;
		counter += 1; 
		
	}
	else {	
		elem1.style.transition= "transform .5s linear 0s";
		elem1.style.transform = "perspective(600px ) rotateY( 0deg)";
		elem1.style.transition= "transform .5s linear 0s";
		elem2.style.transform = "perspective(600px ) rotateY( 180deg)";	 
		
		flipped = false;
		counter = 0; 
 	}
}

function loginReq() {
    var name = document.getElementById("Name").value;
    var password = document.getElementById("password").value; 	
    var data = "userName=" + name + "&password=" + password;
    var localRequest = new XMLHttpRequest();
    var messageDiv = document.getElementById("messageDiv"); 
            	
    localRequest.open("POST", "http://universe.tc.uvu.edu/cs2550/assignments/PasswordCheck/check.php");
    localRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    localRequest.onreadystatechange = 
    function() {
		console.log(localRequest);
		if (localRequest.readyState == 4 && localRequest.status == 200) {
		    var obj = JSON.parse(localRequest.response);
		    if (obj.result == "valid") {
		    	window.open("dyntable.html");
		    	localStorage.setItem("username", obj.userName);
		    	localStorage.setItem("timestamp", obj.timestamp); 
		    	}
		    else {
		    	messageDiv.innerHTML = "Bad username or password";
		    }
		}
	};
	localRequest.send(data);	
}

function getStamp() {
	document.getElementById("usernameDisplay").innerHTML = localStorage.getItem("username"); ;
	document.getElementById("timeStamp").innerHTML = localStorage.getItem("timestamp");  
}

function clearData() {
	localStorage.removeItem("username"); 
	localStorage.removeItem("timestamp");
	document.getElementById("usernameDisplay").innerHTML = ""; 
	document.getElementById("timeStamp").innerHTML = "";  	
} 



