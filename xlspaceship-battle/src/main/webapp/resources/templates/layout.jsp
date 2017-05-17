<!doctype html>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
	<head>
		<title><tiles:insertAttribute name="title" /></title>
		<tiles:insertAttribute name="headerscript" />
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<tiles:insertAttribute name="header" />
			</div>
			<div id="main">
				<tiles:insertAttribute name="body" />
			</div>
			<div id="footer">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</body>
</html>
