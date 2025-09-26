-- Tạo database QUANLYDANHMUC
CREATE DATABASE IF NOT EXISTS QUANLYDANHMUC;
USE QUANLYDANHMUC;

-- Tạo bảng DANHMUC
CREATE TABLE DANHMUC (
    MADM INT PRIMARY KEY AUTO_INCREMENT,
    TENDANHMUC VARCHAR(255) NOT NULL,
    NGUOIQUANLY VARCHAR(255) NOT NULL,
    GHICHU TEXT
);

-- Tạo bảng TINTUC
CREATE TABLE TINTUC (
    MATT INT PRIMARY KEY AUTO_INCREMENT,
    TIEUDE VARCHAR(500) NOT NULL,
    NOIDUNGTT TEXT NOT NULL,
    LIENKET VARCHAR(1000) NOT NULL,
    MADM INT NOT NULL,
    FOREIGN KEY (MADM) REFERENCES DANHMUC(MADM) ON DELETE CASCADE
);

-- Thêm dữ liệu mẫu cho bảng DANHMUC
INSERT INTO DANHMUC (TENDANHMUC, NGUOIQUANLY, GHICHU) VALUES
('Tin tức thế giới', 'Nguyễn Văn A', 'Các tin tức quốc tế và thế giới'),
('Công nghệ', 'Trần Thị B', 'Tin tức về công nghệ, IT, AI'),
('Thể thao', 'Lê Văn C', 'Tin tức thể thao trong và ngoài nước'),
('Kinh tế', 'Phạm Thị D', 'Tin tức kinh tế, tài chính'),
('Giải trí', 'Hoàng Văn E', 'Tin tức giải trí, showbiz'),
('Sức khỏe', 'Đặng Thị F', 'Tin tức sức khỏe, y tế');

-- Thêm dữ liệu mẫu cho bảng TINTUC
INSERT INTO TINTUC (TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES
('OpenAI ra mắt ChatGPT-5 với khả năng mới', 'OpenAI vừa công bố phiên bản ChatGPT-5 với nhiều cải tiến vượt bậc về khả năng xử lý ngôn ngữ tự nhiên và hiểu biết ngữ cảnh.', 'http://example.com/chatgpt5-launch', 2),
('World Cup 2026 sẽ được tổ chức tại 3 quốc gia', 'FIFA chính thức xác nhận World Cup 2026 sẽ diễn ra tại Mỹ, Canada và Mexico với 48 đội tham dự.', 'http://example.com/worldcup-2026', 3),
('Thị trường chứng khoán Việt Nam tăng mạnh', 'VN-Index đã vượt mốc 1,300 điểm trong phiên giao dịch hôm nay nhờ dòng tiền mạnh từ khối ngoại.', 'http://example.com/vnindex-tang', 4),
('Phát hiện loài động vật mới ở Amazon', 'Các nhà khoa học vừa phát hiện một loài khỉ mới tại rừng Amazon với đặc điểm sinh học độc đáo.', 'http://example.com/new-species-amazon', 1),
('Festival âm nhạc quốc tế Việt Nam 2024', 'Festival âm nhạc quốc tế quy tụ nhiều nghệ sĩ nổi tiếng thế giới sẽ được tổ chức tại Hà Nội vào tháng 12.', 'http://example.com/music-festival-2024', 5),
('Nghiên cứu mới về tác dụng của vitamin D', 'Nghiên cứu cho thấy việc bổ sung vitamin D có thể giảm nguy cơ mắc các bệnh về tim mạch.', 'http://example.com/vitamin-d-research', 6),
('Google phát triển chip AI mới', 'Google công bố chip TPU thế hệ mới với hiệu năng xử lý AI tăng gấp 10 lần so với thế hệ trước.', 'http://example.com/google-new-chip', 2),
('Đội tuyển Việt Nam vào vòng loại World Cup', 'Đội tuyển bóng đá Việt Nam chính thức giành quyền vào vòng loại cuối World Cup 2026.', 'http://example.com/vietnam-worldcup-qualifier', 3),
('Giá vàng tăng cao kỷ lục', 'Giá vàng trong nước đã vượt mốc 80 triệu đồng/lượng, cao nhất trong lịch sử.', 'http://example.com/gold-price-record', 4),
('Ca sĩ nổi tiếng thông báo kết hôn', 'Nữ ca sĩ nổi tiếng Hương Giang chính thức thông báo kết hôn với bạn trai doanh nhân.', 'http://example.com/singer-wedding', 5);