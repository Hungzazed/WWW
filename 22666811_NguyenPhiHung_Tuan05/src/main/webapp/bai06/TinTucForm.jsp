<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Tin tức mới</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
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
                <a class="nav-link" href="${pageContext.request.contextPath}/quan-ly">
                    <i class="fas fa-cog me-1"></i>Quản lý
                </a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4 class="card-title mb-0">
                            <i class="fas fa-plus me-2"></i>Thêm Tin tức mới
                        </h4>
                    </div>
                    <div class="card-body">
                        <!-- Hiển thị thông báo lỗi -->
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <i class="fas fa-exclamation-circle me-2"></i>${error}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>

                        <!-- Kiểm tra nếu không có danh mục -->
                        <c:if test="${empty danhSachDanhMuc}">
                            <div class="alert alert-warning" role="alert">
                                <i class="fas fa-exclamation-triangle me-2"></i>
                                <strong>Chú ý:</strong> Không có danh mục nào. Cần có ít nhất một danh mục để thêm tin tức.
                            </div>
                        </c:if>

                        <!-- Form thêm tin tức -->
                        <form id="tinTucForm" action="${pageContext.request.contextPath}/tintuc-form" method="post" class="${empty danhSachDanhMuc ? 'd-none' : ''}">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="mb-3">
                                        <label for="tieuDe" class="form-label">
                                            <i class="fas fa-heading me-1"></i>Tiêu đề <span class="text-danger">*</span>
                                        </label>
                                        <input type="text" 
                                               class="form-control" 
                                               id="tieuDe" 
                                               name="tieuDe" 
                                               placeholder="Nhập tiêu đề tin tức..."
                                               required 
                                               maxlength="500">
                                        <div class="form-text">
                                            <i class="fas fa-info-circle me-1"></i>Tiêu đề tối đa 500 ký tự
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="maDM" class="form-label">
                                            <i class="fas fa-tags me-1"></i>Danh mục <span class="text-danger">*</span>
                                        </label>
                                        <select id="maDM" name="maDM" class="form-select" required>
                                            <option value="">-- Chọn danh mục --</option>
                                            <c:forEach var="danhMuc" items="${danhSachDanhMuc}">
                                                <option value="${danhMuc.maDM}">${danhMuc.tenDanhMuc}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="lienKet" class="form-label">
                                            <i class="fas fa-link me-1"></i>Liên kết <span class="text-danger">*</span>
                                        </label>
                                        <input type="url" 
                                               class="form-control" 
                                               id="lienKet" 
                                               name="lienKet" 
                                               placeholder="http://example.com"
                                               required 
                                               pattern="^http://.*"
                                               title="Liên kết phải bắt đầu bằng http://">
                                        <div class="form-text">
                                            <i class="fas fa-info-circle me-1"></i>Phải bắt đầu bằng "http://"
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="mb-4">
                                        <label for="noiDung" class="form-label">
                                            <i class="fas fa-file-text me-1"></i>Nội dung <span class="text-danger">*</span>
                                        </label>
                                        <textarea class="form-control" 
                                                  id="noiDung" 
                                                  name="noiDung" 
                                                  rows="5" 
                                                  placeholder="Nhập nội dung tin tức..."
                                                  required 
                                                  maxlength="255"></textarea>
                                        <div class="form-text">
                                            <i class="fas fa-info-circle me-1"></i>
                                            Nội dung tối đa 255 ký tự. 
                                            <span id="charCount">0/255</span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <hr>

                            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <a href="${pageContext.request.contextPath}/danhsach-tintuc" class="btn btn-outline-secondary">
                                    <i class="fas fa-times me-1"></i>Hủy
                                </a>
                                <button type="reset" class="btn btn-outline-warning">
                                    <i class="fas fa-redo me-1"></i>Làm lại
                                </button>
                                <button type="submit" class="btn btn-primary">
                                    <i class="fas fa-save me-1"></i>Thêm tin tức
                                </button>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- Hướng dẫn -->
                <div class="card mt-4">
                    <div class="card-body">
                        <h6 class="card-title"><i class="fas fa-info-circle me-2"></i>Hướng dẫn</h6>
                        <ul class="mb-0">
                            <li><strong>Tiêu đề:</strong> Nhập tiêu đề ngắn gọn, hấp dẫn cho tin tức</li>
                            <li><strong>Danh mục:</strong> Chọn danh mục phù hợp với nội dung tin tức</li>
                            <li><strong>Liên kết:</strong> URL đầy đủ để đọc chi tiết tin tức (bắt đầu bằng http://)</li>
                            <li><strong>Nội dung:</strong> Tóm tắt ngắn gọn về tin tức (tối đa 255 ký tự)</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Đếm ký tự trong textarea
        document.getElementById('noiDung').addEventListener('input', function() {
            const maxLength = 255;
            const currentLength = this.value.length;
            const charCount = document.getElementById('charCount');
            
            charCount.textContent = currentLength + '/' + maxLength;
            
            if (currentLength > maxLength * 0.9) {
                charCount.className = 'text-warning';
            } else if (currentLength === maxLength) {
                charCount.className = 'text-danger';
            } else {
                charCount.className = '';
            }
        });

        // Auto focus vào tiêu đề
        document.addEventListener('DOMContentLoaded', function() {
            const tieuDeInput = document.getElementById('tieuDe');
            if (tieuDeInput) {
                tieuDeInput.focus();
            }
        });

        // Validation form
        document.getElementById('tinTucForm').addEventListener('submit', function(e) {
            const tieuDe = document.getElementById('tieuDe').value.trim();
            const noiDung = document.getElementById('noiDung').value.trim();
            const lienKet = document.getElementById('lienKet').value.trim();
            const maDM = document.getElementById('maDM').value;
            
            let errors = [];
            
            if (!tieuDe) {
                errors.push('Vui lòng nhập tiêu đề tin tức');
            }
            
            if (!noiDung) {
                errors.push('Vui lòng nhập nội dung tin tức');
            } else if (noiDung.length > 255) {
                errors.push('Nội dung không được vượt quá 255 ký tự');
            }
            
            if (!lienKet) {
                errors.push('Vui lòng nhập liên kết');
            } else if (!lienKet.startsWith('http://')) {
                errors.push('Liên kết phải bắt đầu bằng "http://"');
            }
            
            if (!maDM) {
                errors.push('Vui lòng chọn danh mục');
            }
            
            if (errors.length > 0) {
                e.preventDefault();
                alert('Lỗi:\n' + errors.join('\n'));
                return false;
            }
        });

        // Auto thêm http:// nếu user quên
        document.getElementById('lienKet').addEventListener('blur', function() {
            let value = this.value.trim();
            if (value && !value.startsWith('http://') && !value.startsWith('https://')) {
                this.value = 'http://' + value;
            }
        });
    </script>
</body>
</html>