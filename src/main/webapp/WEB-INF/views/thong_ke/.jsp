<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<c:if test="${loaiThongke.equals(PM) }">
		<table class="data-table-export table">
			<thead>
				<tr>
					<!-- PHIẾU MƯỢN -->
					<th>Ngày Mượn</th>
					<th>Ngày Trả</th>
					<th>Số lượng ${loaiThongke}</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="kq" items="${kqThongke}">
					<tr>
						<th>${kq[0]}</th>
						<th>${kq[1]}</th>
						<td>${kq[2]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<table hidden="hidden" id="myTable">
			<thead>
				<tr>
					<!-- PHIẾU MƯỢN -->
					<c:choose>
						<c:when test="${loaiThongke.equals(PM) }">
							<th>Ngày Mượn</th>
						</c:when>
						<c:otherwise>
							<th>Ngày</th>
						</c:otherwise>
					</c:choose>
					<th>${loaiThongke}</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="kq" items="${kqThongke}">
					<tr>
						<th>${kq[0]}</th>
						<td>${kq[2]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>