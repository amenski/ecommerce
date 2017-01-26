<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Status</title>
</head>
<script type="text/javascript">
	function stopUpload(fileName){
        window.top.window.vm.account_detail.myProfile.stopUpldLogo({Status:'Done', FileName:fileName});
	}
</script>
<body onLoad="stopUpload('${message}')">
<img alt="" src="webresources/file_upload/${message}">
</body>
</html>