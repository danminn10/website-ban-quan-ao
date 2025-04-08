# 👕 Website Bán Quần Áo - Backend

Đây là dự án **backend** cho hệ thống website bán quần áo, được xây dựng bằng ngôn ngữ Java sử dụng Spring Boot. Backend cung cấp API phục vụ cho các chức năng như quản lý sản phẩm, người dùng, đơn hàng và giỏ hàng.

## 🚀 Tính năng chính

- ✅ Quản lý người dùng: đăng ký, đăng nhập, phân quyền (ROLE_USER, ROLE_ADMIN)
- ✅ Quản lý sản phẩm: thêm, sửa, xóa, tìm kiếm
- ✅ Giỏ hàng: thêm/sửa/xóa sản phẩm, tính tổng tiền
- ✅ Đơn hàng: đặt hàng, theo dõi và quản lý đơn
- ✅ Bảo mật với Spring Security và JWT (nếu có)

## 🛠️ Công nghệ sử dụng

- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA (Hibernate)
- MySQL / H2 Database
- Maven
- RESTful API
- (Tuỳ chọn) Swagger UI để test API

## ⚙️ Cài đặt & chạy thử

### 1. Clone dự án

```bash
git clone https://github.com/danminn10/website-ban-quan-ao.git
cd website-ban-quan-ao
2. Cấu hình cơ sở dữ liệu
Mở file src/main/resources/application.properties và chỉnh sửa thông tin kết nối database:

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/ten_csdl
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Bạn có thể dùng MySQL, hoặc chuyển sang H2 nếu muốn test nhanh.

3. Chạy ứng dụng
bash
Copy
Edit
./mvnw spring-boot:run
Hoặc mở bằng IDE như IntelliJ và nhấn nút Run.

4. Truy cập API
bash
Copy
Edit
http://localhost:8080/api/...
Nếu có Swagger:

bash
Copy
Edit
http://localhost:8080/swagger-ui/index.html
📁 Cấu trúc thư mục
bash
Copy
Edit
src/
├── main/
│   ├── java/com/example/project/
│   │   ├── controller/      # REST API Controllers
│   │   ├── model/           # Entity và DTO
│   │   ├── repository/      # JPA Repositories
│   │   ├── service/         # Business logic
│   │   └── config/          # Cấu hình Security, Web
│   └── resources/
│       ├── application.properties
│       └── data.sql         # (Tùy chọn) Dữ liệu mẫu
🧑‍💻 Đóng góp
Mọi đóng góp đều được hoan nghênh! Hãy tạo issue hoặc gửi pull request nếu bạn muốn đóng góp.

📄 Giấy phép
Dự án này sử dụng MIT License.
