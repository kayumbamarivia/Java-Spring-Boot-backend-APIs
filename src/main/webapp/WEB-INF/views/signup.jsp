<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Registration</title>
</head>
<body>
	<div>
		<h2>Student Registration</h2>
		<form action="signup" method="post">
			<div>
				<label for="first_name">First Name</label><input type="text"
					id="first_name" name="first_name" required>
			</div>
			<div>
				<label for="last_name">Last Name</label> <input type="text"
					id="last_name" name="last_name" required>
			</div>
			<div>
				<label for="email">Email</label> <input type="email" id="email"
					name="email" required>
			</div>
			<button type="submit">Register</button>
		</form>
	</div>
</body>
</html>
