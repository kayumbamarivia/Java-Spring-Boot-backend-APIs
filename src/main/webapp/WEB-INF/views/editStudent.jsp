<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.jmv.studentManagement.model.Student"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Your New Stylish Page Title</title>
</head>
<body>
	<%
	Student student = (Student) request.getAttribute("student");
	%>
	<div>
		<h1>Edit Student</h1>
	</div>

	<div>
		<form action="/api/students/home/<%=student.getId()%>/edit" method="post">
			<div>
				<label for="first_name">First Name</label><input type="text"
					id="first_name" name="first_name"
					value="<%=student.getFirst_name()%>" required>
			</div>
			<div>
				<label for="last_name">Last Name</label> <input type="text"
					id="last_name" name="last_name" value="<%=student.getLast_name()%>"
					required>
			</div>
			<div>
				<label for="email">Email</label> <input type="email" id="email"
					name="email" value="<%=student.getEmail()%>" required>
			</div>
			<button type="submit">Update</button>
			<button><a href="../../home">Cancel</a></button>
		</form>
	</div>

</body>
</html>
