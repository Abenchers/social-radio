<!DOCTYPE html>
<html lang="en-US">
<head>
<!-- <script type="text/javascript" src="jquery-1.8.3.min.js"></script> -->
<script type="text/javascript"
	src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
</head>
<body onload="load()">

	<script type="text/javascript">
		function load() {
			setInterval(playerControl, 5000);
			startUser('userId');
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
							var player = "<audio id='myPlayer' src='"+data.streamingURL+"' controls preload='auto' autoplay='autoplay' onended='startUser(userId)'></audio>";
							$('#player').html(player);

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
			if (player.paused || player.ended || 0 == player.currentTime) {
				startUser('userId');
			}

		}

	</script>

	<div id="player"></div>
	<input type="hidden" id="socialList" value="socialRadio" />
</body>
</html>