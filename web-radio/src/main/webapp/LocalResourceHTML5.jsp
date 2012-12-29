<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<body>
	<input id="file_input" type="file" webkitdirectory directory />
	<script>
		fs.root.getDirectory('Documents', {}, function(dirEntry) {
			var dirReader = dirEntry.createReader();
			dirReader.readEntries(function(entries) {
				<br>
				for ( var i = 0; i < entries.length; i++) {
					var entry = entries[i];
					if (entry.isDirectory) {
						console.log('Directory: ' + entry.fullPath);
					} else if (entry.isFile) {
						console.log('File: ' + entry.fullPath);
					}
				}

			}, errorHandler);
		}, errorHandler);

		window.requestFileSystem(window.TEMPORARY, 1024 * 1024, onInitFs,
				errorHandler);
	</script>
</body>
</html>