<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Tin tức</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .news-item {
            transition: background-color 0.2s ease;
        }
        .news-item:hover {
            background-color: #f8f9fa;
        }
        .btn-delete {
            transition: all 0.2s ease;
        }
        .btn-delete:hover {
            transform: scale(1.1);
        }
    </style>
</head>
<body class="bg-light">
    <nav class="navbar navbar-expand-lg navbar-dark bg-danger">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/danhsach-tintuc">
                <i class="fas fa-cogs me-2"></i>Quản lý Tin tức
            </a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="${pageContext.request.contextPath}/danhsach-tintuc">
                    <i class="fas fa-list me-1"></i>Danh sách tin tức
                </a>
                <a class="nav-link" href="${pageContext.request.contextPath}/tintuc-form">
                    <i class="fas fa-plus me-1"></i>Thêm tin tức
                </a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header bg-danger text-white">
                        <h4 class="card-title mb-0">
                            <i class="fas fa-trash-alt me-2"></i>Quản lý - Xóa Tin tức
                        </h4>
                    </div>
                    <div class="card-body">
                        <!-- Hiển thị thông báo -->
                        <c:if test="${not empty message}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                <i class="fas fa-check-circle me-2"></i>${message}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>

                        <c:if test="${not empty error}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <i class="fas fa-exclamation-circle me-2"></i>${error}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>

                        <!-- Hướng dẫn -->
                        <div class="alert alert-info mb-4" role="alert">
                            <i class="fas fa-info-circle me-2"></i>
                            <strong>Hướng dẫn:</strong> Danh sách dưới đây hiển thị tất cả tin tức trong hệ thống. 
                            Nhấn nút <strong>Xóa</strong> để xóa tin tức không cần thiết ra khỏi cơ sở dữ liệu.
                        </div>

                        <!-- Danh sách tin tức để quản lý -->
                        <c:choose>
                            <c:when test="${empty danhSachTinTuc}">
                                <div class="text-center py-5">
                                    <i class="fas fa-inbox fa-3x text-muted mb-3"></i>
                                    <h4 class="text-muted">Không có tin tức nào</h4>
                                    <p class="text-muted">Hệ thống chưa có tin tức nào để quản lý</p>
                                    <a href="${pageContext.request.contextPath}/tintuc-form" class="btn btn-primary">
                                        <i class="fas fa-plus me-1"></i>Thêm tin tức đầu tiên
                                    </a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead class="table-dark">
                                            <tr>
                                                <th width="60"><i class="fas fa-hashtag"></i></th>
                                                <th><i class="fas fa-heading me-1"></i>Tiêu đề</th>
                                                <th width="150"><i class="fas fa-tags me-1"></i>Danh mục</th>
                                                <th width="200"><i class="fas fa-file-text me-1"></i>Nội dung</th>
                                                <th width="120"><i class="fas fa-link me-1"></i>Liên kết</th>
                                                <th width="100" class="text-center"><i class="fas fa-cogs"></i></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="tinTuc" items="${danhSachTinTuc}" varStatus="status">
                                                <tr class="news-item">
                                                    <td>
                                                        <span class="badge bg-secondary">${tinTuc.maTT}</span>
                                                    </td>
                                                    <td>
                                                        <strong><c:out value="${tinTuc.tieuDe}"/></strong>
                                                    </td>
                                                    <td>
                                                        <span class="badge bg-info"><c:out value="${tinTuc.danhMuc.tenDanhMuc}"/></span>
                                                    </td>
                                                    <td>
                                                        <div style="max-width: 200px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
                                                            <c:out value="${tinTuc.noiDungTT}"/>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <a href="<c:out value='${tinTuc.lienKet}'/>" target="_blank" class="btn btn-sm btn-outline-primary" title="Mở liên kết">
                                                            <i class="fas fa-external-link-alt"></i>
                                                        </a>
                                                    </td>
                                                    <td class="text-center">
                                                        <button type="button" 
                                                                class="btn btn-sm btn-danger btn-delete" 
                                                                data-bs-toggle="modal" 
                                                                data-bs-target="#deleteModal"
                                                                data-ma-tt="${tinTuc.maTT}"
                                                                data-tieu-de="<c:out value='${tinTuc.tieuDe}'/>"
                                                                title="Xóa tin tức">
                                                            <i class="fas fa-trash"></i>
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>

                                <!-- Thống kê -->
                                <div class="mt-4">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="card bg-primary text-white">
                                                <div class="card-body text-center">
                                                    <i class="fas fa-newspaper fa-2x mb-2"></i>
                                                    <h4>${fn:length(danhSachTinTuc)}</h4>
                                                    <p class="mb-0">Tổng tin tức</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="card bg-success text-white">
                                                <div class="card-body text-center">
                                                    <i class="fas fa-check-circle fa-2x mb-2"></i>
                                                    <h4>Có thể xóa</h4>
                                                    <p class="mb-0">Trạng thái</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="card bg-warning text-white">
                                                <div class="card-body text-center">
                                                    <i class="fas fa-exclamation-triangle fa-2x mb-2"></i>
                                                    <h4>Thận trọng</h4>
                                                    <p class="mb-0">Khi xóa dữ liệu</p>
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
        </div>
    </div>

    <!-- Modal xác nhận xóa -->
    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="deleteModalLabel">
                        <i class="fas fa-exclamation-triangle me-2"></i>Xác nhận xóa
                    </h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="text-center">
                        <i class="fas fa-trash-alt fa-3x text-danger mb-3"></i>
                        <h5>Bạn có chắc chắn muốn xóa tin tức này?</h5>
                        <p class="text-muted mb-3">
                            <strong id="newsTitle"></strong>
                        </p>
                        <p class="text-warning">
                            <i class="fas fa-exclamation-triangle me-1"></i>
                            Hành động này không thể hoàn tác!
                        </p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        <i class="fas fa-times me-1"></i>Hủy
                    </button>
                    <a id="deleteLink" href="#" class="btn btn-danger">
                        <i class="fas fa-trash me-1"></i>Xóa tin tức
                    </a>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Handle delete button click
        document.addEventListener('DOMContentLoaded', function() {
            var deleteButtons = document.querySelectorAll('.btn-delete');
            var newsTitle = document.getElementById('newsTitle');
            var deleteLink = document.getElementById('deleteLink');
            
            deleteButtons.forEach(function(button) {
                button.addEventListener('click', function() {
                    var maTT = this.getAttribute('data-ma-tt');
                    var tieuDe = this.getAttribute('data-tieu-de');
                    
                    newsTitle.textContent = tieuDe;
                    deleteLink.href = '${pageContext.request.contextPath}/quan-ly?action=delete&maTT=' + maTT;
                });
            });
            
            // Auto close alerts after 5 seconds
            setTimeout(function() {
                var alerts = document.querySelectorAll('.alert-dismissible');
                alerts.forEach(function(alert) {
                    var bsAlert = new bootstrap.Alert(alert);
                    bsAlert.close();
                });
            }, 5000);
        });
    </script>
</body>
</html>