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
	<div class="col">
		<div class="row">
			<form action="test" method="post">
				<div class="form-group">
					<!-- Khi dùng spring:bind không dùng được form:form -->
					<!-- tên Attribute -->
					<spring:bind path="loaiTb.ten">
						<select name="ten">
							<!-- thuộc tính cần lấy về -->
							<option value="" selected="selected">-Chọn thiết bị-</option>
							<c:forEach items="${listLoaiTB }" var="loaiTb">
								<option value="${loaiTb.ten }">${loaiTb.ten }</option>
							</c:forEach>
						</select>
						<c:if test="${status.error}">
							<p>Lỗi : ${status.errorMessages[0]}</p>
						</c:if>
						
					</spring:bind>
					<!-- tên Attribute -->
					<spring:bind path="phanQuyen.mapq">
						<!-- thuộc tính cần lấy về -->
						<input type="text" name="mapq" placeholder="Mã phân quyền">
						<c:if test="${status.error}">
							<p>Lỗi : ${status.errorMessages[0]}</p>
						</c:if>
					</spring:bind>
					<spring:bind path="phanQuyen.tenpq">
						<!-- thuộc tính cần lấy về -->
						<input type="text" name="tenpq" placeholder="Tên phân quyền">
						<c:if test="${status.error}">
							<p>Lỗi : ${status.errorMessages[0]}</p>
						</c:if>
					</spring:bind>
					<input type="submit" value="gửi">
				</div>
			</form>
		</div>
	</div>
	<%@include file="/common/footer.jsp"%>
</body>
</html>