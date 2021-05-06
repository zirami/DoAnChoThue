<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic Page Info -->
<meta charset="utf-8">
<title>DeskApp - Bootstrap Admin Dashboard HTML Template</title>
<%@include file="/common/loadcss.jsp"%>
</head>
<body>
	<div class="row">
		<div class="col">
			<div>Array</div>
			<div>
				<c:forEach items="${array}" var="item1">
					<h3>${item1}</h3>
				</c:forEach>
			</div>
		</div>
		<div class="col">
			<div>List</div>
			<div>
				<c:forEach items="${list }" var="item2">
					<h3>${item2}</h3>
				</c:forEach>
			</div>
		</div>
	</div>
	<form action="multi" method="post" >
		<div class="form-group">
			<label>Select/deselect all</label>
			<select name="multidata[]" multiple
				class="selectpicker form-control" data-size="5"
				data-style="btn-outline-warning" data-actions-box="true"
				data-selected-text-format="count">
				<!-- dùng form:select thì đổi thành path="multi-data[]" -->
				<option value="Mustard">Mustard</option>
				<option value="Ketchup">Ketchup</option>
				<option value="Relish">Relish</option>
			</select>
			<input type="submit" class="btn btn-primary" value="gửi">
		</div>
	</form>
	<%@include file="/common/footer.jsp"%>
</body>
</html>