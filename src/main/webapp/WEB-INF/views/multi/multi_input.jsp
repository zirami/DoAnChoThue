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
<style>
.center {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container center ">
		<div class="col">
			<div class="row">${msg}</div>
			<div class="row form-control">
				<form action="test" method="post">
					<div class="form-group">
						<!-- Khi dùng spring:bind không dùng được form:form -->
						<!-- tên Attribute -->
						<spring:bind path="loaiTb">
							<select name="ten" required>
								<!-- thuộc tính cần lấy về -->
								<option value="" selected="selected">-Chọn thiết bị-</option>
								<c:forEach items="${listLoaiTB }" var="loaiTb">
									<option value="${loaiTb.ten }">${loaiTb.ten }</option>
								</c:forEach>
							</select>
						</spring:bind>
						<!-- tên Attribute -->
						<spring:bind path="phanQuyen">
							<!-- thuộc tính cần lấy về -->
							<input type="text" name="mapq" placeholder="Mã phân quyền"
								required>
							<!-- thuộc tính cần lấy về -->
							<input type="text" name="tenpq" placeholder="Tên phân quyền"
								required>
						</spring:bind>
						<input type="submit" value="gửi">
					</div>
				</form>
				<hr>
			</div>
			<!--=============================== Test phiếu nhập ===============================-->
			<div class="row form-control">
				<form class="" action="">
					<div class="col">
						<div class="row">
							<div class="col form-group">
								<label>Mã Phiếu mượn</label>
								<input type="text">
							</div>
							<div class="col form-group">
								<label>Người mượn</label>
								<input type="text">
							</div>
							<div class="col form-group">
								<label>Nhân viên tạo</label>
								<input type="text">
							</div>
						</div>
						<div class="row form-group">
							<input type="submit">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="/common/footer.jsp"%>
</body>
</html>