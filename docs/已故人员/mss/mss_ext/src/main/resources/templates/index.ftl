<html>
<head>
<title>Upload File Request Page</title>
</head>
<body>
    <form method="POST" action="/word/upload" enctype="multipart/form-data">
        File to upload: <input type="file" name="file"><br />
        Dict Name: <input type="text" name="dicname"><br /> <br />
        <input type="submit" value="Upload"> Press here to upload the file!
    </form>
</body>
</html>
