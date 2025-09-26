<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách Tin tức</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .news-card {
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }
        .news-card:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }
        .news-content {
            display: -webkit-box;
            -webkit-line-clamp: 3;
            -webkit-box-orient: vertical;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body class="bg-light">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="#">
                <i class="fas fa-newspaper me-2"></i>Quản lý Tin tức
            </a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="${pageContext.request.contextPath}/tintuc-form">
                    <i class="fas fa-plus me-1"></i>Thêm tin tức
                </a>
                <a class="nav-link" href="${pageContext.request.contextPath}/quan-ly">
                    <i class="fas fa-cog me-1"></i>Quản lý
                </a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row">
            <div class="col-md-12">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2>
                        <i class="fas fa-list me-2"></i>Danh sách Tin tức
                        <c:if test="${not empty selectedDanhMucName}">
                            <span class="badge bg-info ms-2">${selectedDanhMucName}</span>
                        </c:if>
                    </h2>
                </div>

                <!-- Bộ lọc theo danh mục -->
                <div class="card mb-4">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/danhsach-tintuc" method="get" class="d-flex align-items-end gap-3">
                            <div class="flex-grow-1">
                                <label for="danhMuc" class="form-label">
                                    <i class="fas fa-filter me-1"></i>Lọc theo Danh mục
                                </label>
                                <select id="danhMuc" name="danhMuc" class="form-select">
                                    <option value="">-- Tất cả danh mục --</option>
                                    <c:forEach var="dm" items="${danhSachDanhMuc}">
                                        <option value="${dm.maDM}" ${selectedDanhMuc.maDM == dm.maDM ? 'selected' : ''}>
                                            ${dm.tenDanhMuc}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <button type="submit" class="btn btn-outline-primary">
                                    <i class="fas fa-search me-1"></i>Lọc
                                </button>
                                <c:if test="${not empty selectedDanhMuc}">
                                    <a href="${pageContext.request.contextPath}/danhsach-tintuc" class="btn btn-outline-secondary">
                                        <i class="fas fa-times me-1"></i>Bỏ lọc
                                    </a>
                                </c:if>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- Hiển thị thông báo lỗi -->
                <c:if test="${not empty error}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="fas fa-exclamation-circle me-2"></i>${error}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <!-- Danh sách tin tức -->
                <c:choose>
                    <c:when test="${empty danhSachTinTuc}">
                        <div class="text-center py-5">
                            <i class="fas fa-newspaper fa-3x text-muted mb-3"></i>
                            <h4 class="text-muted">Không có tin tức nào</h4>
                            <p class="text-muted">
                                <c:choose>
                                    <c:when test="${not empty selectedDanhMuc}">
                                        Không có tin tức nào trong danh mục này
                                    </c:when>
                                    <c:otherwise>
                                        Hãy thêm tin tức đầu tiên
                                    </c:otherwise>
                                </c:choose>
                            </p>
                            <a href="${pageContext.request.contextPath}/tintuc-form" class="btn btn-primary">
                                <i class="fas fa-plus me-1"></i>Thêm tin tức mới
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="row">
                            <c:forEach var="tinTuc" items="${danhSachTinTuc}" varStatus="status">
                                <div class="col-md-6 col-lg-4 mb-4">
                                    <div class="card news-card h-100">
                                        <div class="card-header bg-primary text-white">
                                            <div class="d-flex justify-content-between align-items-center">
                                                <small>
                                                    <i class="fas fa-tag me-1"></i>
                                                    ${tinTuc.danhMuc.tenDanhMuc}
                                                </small>
                                                <small>
                                                    <i class="fas fa-hashtag me-1"></i>
                                                    ID: ${tinTuc.maTT}
                                                </small>
                                            </div>
                                        </div>
                                        <div class="card-body d-flex flex-column">
                                            <h5 class="card-title">${tinTuc.tieuDe}</h5>
                                            <p class="card-text news-content flex-grow-1">
                                                ${tinTuc.noiDungTT}
                                            </p>
                                            <div class="mt-auto">
                                                <a href="${tinTuc.lienKet}" target="_blank" class="btn btn-outline-primary btn-sm">
                                                    <i class="fas fa-external-link-alt me-1"></i>Xem chi tiết
                                                </a>
                                            </div>
                                        </div>
                                        <div class="card-footer text-muted">
                                            <small>
                                                <i class="fas fa-user me-1"></i>
                                                Quản lý: ${tinTuc.danhMuc.nguoiQuanLy}
                                            </small>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                        <!-- Thống kê -->
                        <div class="mt-4">
                            <div class="card bg-light">
                                <div class="card-body">
                                    <div class="row text-center">
                                        <div class="col-md-4">
                                            <h4 class="text-primary">${danhSachTinTuc.size()}</h4>
                                            <p class="mb-0">Tin tức hiển thị</p>
                                        </div>
                                        <div class="col-md-4">
                                            <h4 class="text-success">${danhSachDanhMuc.size()}</h4>
                                            <p class="mb-0">Danh mục có sẵn</p>
                                        </div>
                                        <div class="col-md-4">
                                            <h4 class="text-info">
                                                <c:choose>
                                                    <c:when test="${not empty selectedDanhMuc}">Lọc theo danh mục</c:when>
                                                    <c:otherwise>Tất cả danh mục</c:otherwise>
                                                </c:choose>
                                            </h4>
                                            <p class="mb-0">Chế độ hiển thị</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <footer class="bg-dark text-light py-4 mt-5">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h6><i class="fas fa-graduation-cap me-2"></i>Bài tập Thực hành</h6>
                    <p class="mb-0">22666811 - Nguyễn Phi Hùng - Bài 06</p>
                </div>
                <div class="col-md-6 text-md-end">
                    <p class="mb-0">
                        <i class="fas fa-database me-2"></i>
                        Quản lý Tin tức
                    </p>
                </div>
            </div>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>