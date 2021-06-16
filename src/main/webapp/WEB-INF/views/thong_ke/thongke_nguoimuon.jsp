<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<table class="data-table-export table" id="myTable">
		<thead>
			<tr>
				<!-- Người Mượn -->
				<th>Loại Người Mượn</th>
				<th>Số lượt mượn</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="kq" items="${kqThongke}">
				<tr>
					<th>${kq[0]}</th>
					<th>${kq[1]}</th>
				</tr>
			</c:forEach>
			<tr>
				<th>Khác</th>
				<th>69</th>
			</tr>
		</tbody>
	</table>
</body>
</html>
