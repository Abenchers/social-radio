<!DOCTYPE html>
<html lang="en-US">
<head>
<script type="text/javascript"
	src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript">
	function load() {
		//startUser('userId');
		//setInterval(playerControl, 4000);
		//playReproductor();
	}

	function startUser(userId) {
		$
				.ajax({
					async : false,
					type : 'POST',
					contentType : 'application/json',
					url : 'rest/userManagerService/start',
					data : "{\"userId\":\"socialList\"}",
					dataType : 'json',
					success : function(data) {
						var player = "<audio id='myPlayer' src='"+data.streamingURL+"' controls preload='auto' autoplay='autoplay'></audio>";
						$('#player').html(player);
						playReproductor();

					},
					error : function(data) {
						console.log(data);
						alert("Error" + data);
					}
				});

	}

	function playerControl() {
		var player = document.getElementById("myPlayer");
		player.play;
		alert("Comparando");
		alert("player.paused=" + player.paused + " " + "player.ended"
				+ player.ended);
		if (player.paused || player.ended) {
			startUser('userId');
		}

	}

	function playReproductor() {
		var player = document.getElementById("myPlayer");
		player.play;
	}
</script>

</head>
<body onload="load()">

	<div id="player"></div>
	<audio
		id="myPlayer" src="http://localhost:1234/862f62f7-cc14-428a-84c7-39484dd29081"
		autoplay="autoplay"></audio>
	<input type="hidden" id="socialList" value="socialRadio" />
</body>
</html>