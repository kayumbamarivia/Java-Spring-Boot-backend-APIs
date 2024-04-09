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

	<div>
		<h1>Beautiful Student Portal</h1>
		<div>
			<a href="../home">List of students Student</a>
		</div>
	</div>

	<div>
		<table>
			<caption>Beautiful List of Students</caption>
			<thead>
				<tr>
					<th>ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<% Student student = (Student) request.getAttribute("student");%>
				<tr>
					<td><%= student.getId() %></td>
					<td><%= student.getFirst_name() %></td>
					<td><%= student.getLast_name() %></td>
					<td><%= student.getEmail() %></td>
					<td>
						<div>
							<a href="../home/<%= student.getId()%>/edit">Edit</a> <a href="../home/<%= student.getId()%>/delete" onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
