<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kết quả thao tác - Quản lý Tin tức</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .result-card {
            border: none;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .success-icon {
            color: #28a745;
        }
        .error-icon {
            color: #dc3545;
        }
        .info-icon {
            color: #17a2b8;
        }
        .btn-action {
            transition: all 0.3s ease;
        }
        .btn-action:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
        }
        .countdown {
            font-weight: bold;
            color: #007bff;
        }
    </style>
</head>
<body class="bg-light">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/danh-sach-tin-tuc">
                <i class="fas fa-newspaper me-2"></i>Quản lý Tin tức
            </a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="${pageContext.request.contextPath}/danh-sach-tin-tuc">
                    <i class="fas fa-list me-1"></i>Danh sách tin tức
                </a>
                <a class="nav-link" href="${pageContext.request.contextPath}/tintuc-form">
                    <i class="fas fa-plus me-1"></i>Thêm tin tức
                </a>
                <a class="nav-link" href="${pageContext.request.contextPath}/quan-ly">
                    <i class="fas fa-cogs me-1"></i>Quản lý tin tức
                </a>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <!-- Success Result -->
                <c:if test="${not empty message}">
                    <div class="card result-card">
                        <div class="card-header bg-success text-white text-center">
                            <h4 class="card-title mb-0">
                                <i class="fas fa-check-circle me-2 success-icon"></i>Thành công
                            </h4>
                        </div>
                        <div class="card-body text-center py-5">
                            <div class="mb-4">
                                <i class="fas fa-check-circle success-icon fa-5x mb-3"></i>
                                <h3 class="text-success">Hoàn thành!</h3>
                                <p class="lead text-muted">${message}</p>
                            </div>
                            
                            <!-- Thông tin chi tiết nếu có -->
                            <c:if test="${not empty tinTuc}">
                                <div class="alert alert-info">
                                    <h6><i class="fas fa-info-circle me-1"></i>Chi tiết tin tức:</h6>
                                    <p class="mb-1"><strong>Mã tin tức:</strong> ${tinTuc.maTT}</p>
                                    <p class="mb-1"><strong>Tiêu đề:</strong> <c:out value="${tinTuc.tieuDe}"/></p>
                                    <p class="mb-1"><strong>Danh mục:</strong> <c:out value="${tinTuc.danhMuc.tenDanhMuc}"/></p>
                                    <p class="mb-0"><strong>Liên kết:</strong> 
                                        <a href="<c:out value='${tinTuc.lienKet}'/>" target="_blank" class="text-decoration-none">
                                            <i class="fas fa-external-link-alt me-1"></i>Xem tin tức
                                        </a>
                                    </p>
                                </div>
                            </c:if>
                            
                            <div class="d-grid gap-2 d-md-block">
                                <a href="${pageContext.request.contextPath}/danh-sach-tin-tuc"
                                   class="btn btn-primary btn-lg btn-action">
                                    <i class="fas fa-list me-2"></i>Xem danh sách tin tức
                                </a>
                                <a href="${pageContext.request.contextPath}/tintuc-form"
                                   class="btn btn-success btn-lg btn-action">
                                    <i class="fas fa-plus me-2"></i>Thêm tin tức mới
                                </a>
                            </div>
                        </div>
                    </div>
                </c:if>

                <!-- Error Result -->
                <c:if test="${not empty error}">
                    <div class="card result-card">
                        <div class="card-header bg-danger text-white text-center">
                            <h4 class="card-title mb-0">
                                <i class="fas fa-exclamation-triangle me-2 error-icon"></i>Lỗi
                            </h4>
                        </div>
                        <div class="card-body text-center py-5">
                            <div class="mb-4">
                                <i class="fas fa-exclamation-triangle error-icon fa-5x mb-3"></i>
                                <h3 class="text-danger">Có lỗi xảy ra!</h3>
                                <p class="lead text-muted">${error}</p>
                            </div>
                            
                            <div class="alert alert-warning">
                                <h6><i class="fas fa-lightbulb me-1"></i>Gợi ý khắc phục:</h6>
                                <ul class="list-unstyled text-start mb-0">
                                    <li><i class="fas fa-check me-1"></i>Kiểm tra lại thông tin đã nhập</li>
                                    <li><i class="fas fa-check me-1"></i>Đảm bảo các trường bắt buộc được điền đầy đủ</li>
                                    <li><i class="fas fa-check me-1"></i>Liên kết phải có định dạng URL hợp lệ</li>
                                    <li><i class="fas fa-check me-1"></i>Nội dung không được vượt quá 500 ký tự</li>
                                    <li><i class="fas fa-check me-1"></i>Thử lại sau một lúc nếu lỗi hệ thống</li>
                                </ul>
                            </div>
                            
                            <div class="d-grid gap-2 d-md-block">
                                <a href="javascript:history.back()" 
                                   class="btn btn-warning btn-lg btn-action">
                                    <i class="fas fa-arrow-left me-2"></i>Quay lại
                                </a>
                                <a href="${pageContext.request.contextPath}/tintuc-form"
                                   class="btn btn-primary btn-lg btn-action">
                                    <i class="fas fa-plus me-2"></i>Thử lại
                                </a>
                            </div>
                        </div>
                    </div>
                </c:if>

                <!-- Default/Info Result -->
                <c:if test="${empty message && empty error}">
                    <div class="card result-card">
                        <div class="card-header bg-info text-white text-center">
                            <h4 class="card-title mb-0">
                                <i class="fas fa-info-circle me-2 info-icon"></i>Thông tin
                            </h4>
                        </div>
                        <div class="card-body text-center py-5">
                            <div class="mb-4">
                                <i class="fas fa-info-circle info-icon fa-5x mb-3"></i>
                                <h3 class="text-info">Chào mừng!</h3>
                                <p class="lead text-muted">
                                    Đây là trang hiển thị kết quả thao tác trong hệ thống quản lý tin tức.
                                </p>
                            </div>
                            
                            <div class="alert alert-light">
                                <h6><i class="fas fa-star me-1"></i>Các chức năng chính:</h6>
                                <div class="row text-start">
                                    <div class="col-md-6">
                                        <ul class="list-unstyled">
                                            <li class="mb-2">
                                                <i class="fas fa-plus text-success me-2"></i>
                                                Thêm tin tức mới
                                            </li>
                                            <li class="mb-2">
                                                <i class="fas fa-list text-primary me-2"></i>
                                                Xem danh sách tin tức
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-md-6">
                                        <ul class="list-unstyled">
                                            <li class="mb-2">
                                                <i class="fas fa-filter text-info me-2"></i>
                                                Lọc theo danh mục
                                            </li>
                                            <li class="mb-2">
                                                <i class="fas fa-trash text-danger me-2"></i>
                                                Xóa tin tức không cần thiết
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="d-grid gap-2 d-md-block">
                                <a href="${pageContext.request.contextPath}/danh-sach-tin-tuc"
                                   class="btn btn-primary btn-lg btn-action">
                                    <i class="fas fa-home me-2"></i>Trang chính
                                </a>
                                <a href="${pageContext.request.contextPath}/tintuc-form"
                                   class="btn btn-success btn-lg btn-action">
                                    <i class="fas fa-plus me-2"></i>Bắt đầu thêm tin tức
                                </a>
                            </div>
                        </div>
                    </div>
                </c:if>

                <!-- Auto redirect countdown for success -->
                <c:if test="${not empty message}">
                    <div class="text-center mt-3">
                        <small class="text-muted">
                            <i class="fas fa-clock me-1"></i>
                            Tự động chuyển về danh sách tin tức sau <span class="countdown" id="countdown">10</span> giây
                        </small>
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3 mt-5">
        <div class="container">
            <p class="mb-0">
                <i class="fas fa-copyright me-1"></i>2024 - Hệ thống Quản lý Tin tức
            </p>
            <p class="mb-0">
                <small class="text-muted">Phát triển bởi 22666811 - Nguyễn Phi Hùng</small>
            </p>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <c:if test="${not empty message}">
    <script>
        // Auto redirect countdown for success messages
        let countdown = 10;
        const countdownElement = document.getElementById('countdown');
        
        const timer = setInterval(function() {
            countdown--;
            if (countdownElement) {
                countdownElement.textContent = countdown;
            }
            
            if (countdown <= 0) {
                clearInterval(timer);
                window.location.href = '${pageContext.request.contextPath}/danhsach-tintuc';
            }
        }, 1000);
        
        // Stop countdown when user interacts with page
        document.addEventListener('click', function() {
            clearInterval(timer);
            if (countdownElement) {
                countdownElement.parentElement.innerHTML = '<i class="fas fa-hand-paper me-1"></i>Đã dừng tự động chuyển trang';
            }
        });
    </script>
    </c:if>
    
    <script>
        // Add smooth animations
        document.addEventListener('DOMContentLoaded', function() {
            const resultCard = document.querySelector('.result-card');
            if (resultCard) {
                resultCard.style.opacity = '0';
                resultCard.style.transform = 'translateY(30px)';
                
                setTimeout(function() {
                    resultCard.style.transition = 'all 0.5s ease';
                    resultCard.style.opacity = '1';
                    resultCard.style.transform = 'translateY(0)';
                }, 100);
            }
        });
    </script>
</body>
</html>