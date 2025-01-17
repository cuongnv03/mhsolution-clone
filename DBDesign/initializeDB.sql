CREATE TABLE data_introduction (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    cover_url TEXT
);

CREATE TABLE visions (
    id SERIAL PRIMARY KEY,
    data_introduction_id INT REFERENCES data_introduction(id) ON DELETE CASCADE,
    title TEXT NOT NULL,
    content TEXT NOT NULL
);

CREATE TABLE missions (
    id SERIAL PRIMARY KEY,
    data_introduction_id INT REFERENCES data_introduction(id) ON DELETE CASCADE,
    title TEXT NOT NULL,
    content TEXT NOT NULL
);

CREATE TABLE core_values (
    id SERIAL PRIMARY KEY,
    data_introduction_id INT REFERENCES data_introduction(id) ON DELETE CASCADE,
    title TEXT NOT NULL,
    content TEXT NOT NULL
);

CREATE TABLE competitives (
    id SERIAL PRIMARY KEY,
    data_introduction_id INT REFERENCES data_introduction(id) ON DELETE CASCADE,
    title TEXT NOT NULL,
    content TEXT NOT NULL
);

CREATE TABLE founders (
    id SERIAL PRIMARY KEY,
    seo_id TEXT UNIQUE NOT NULL,
    name TEXT NOT NULL,
    avatar TEXT NOT NULL,
    job_title TEXT NOT NULL,
    about TEXT NOT NULL
);

CREATE TABLE socials (
    id SERIAL PRIMARY KEY,
    founder_id INT REFERENCES founders(id) ON DELETE CASCADE,
    type TEXT NOT NULL,
    src TEXT NOT NULL,
    icon TEXT
);

CREATE TABLE partners (
    id SERIAL PRIMARY KEY,
    seo_id TEXT UNIQUE NOT NULL,
    avatar TEXT NOT NULL,
    name TEXT NOT NULL,
    src TEXT
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    seo_id TEXT UNIQUE NOT NULL,
    title TEXT NOT NULL,
    short_content TEXT,
    website TEXT,
    info_url TEXT,
    cover_url TEXT,
    icon_url TEXT,
    icon_hover_url TEXT,
    phone_number TEXT,
    created_at DATE NOT NULL
);

CREATE TABLE product_contents (
    id SERIAL PRIMARY KEY,
    product_id INT REFERENCES products(id) ON DELETE CASCADE,
    title TEXT,
    content TEXT NOT NULL,
    src TEXT
);

CREATE TABLE product_infos (
    id SERIAL PRIMARY KEY,
    product_id INT REFERENCES products(id) ON DELETE CASCADE,
    title TEXT NOT NULL,
    content TEXT NOT NULL
);

CREATE TABLE data_footer (
    id SERIAL PRIMARY KEY,
    seo_id TEXT UNIQUE NOT NULL,
    address TEXT NOT NULL,
    email TEXT NOT NULL,
    phone_number TEXT NOT NULL
);

CREATE TABLE footer_socials (
    id SERIAL PRIMARY KEY,
    footer_id INT REFERENCES data_footer(id) ON DELETE CASCADE,
    type TEXT NOT NULL,
    src TEXT NOT NULL
);

CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    seo_id TEXT UNIQUE NOT NULL,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    position INT NOT NULL
);

CREATE TABLE news (
    id SERIAL PRIMARY KEY,
    seo_id TEXT UNIQUE NOT NULL,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    category_new_id INT REFERENCES categories(id) ON DELETE SET NULL,
    created_at DATE NOT NULL,
    cover_url TEXT,
    is_highlights BOOLEAN DEFAULT FALSE
);

CREATE TABLE jobs (
    id SERIAL PRIMARY KEY,
    seo_id TEXT UNIQUE NOT NULL,
    title TEXT NOT NULL,
    salary TEXT,
    level TEXT,
    career TEXT,
    job_description TEXT NOT NULL,
    job_requirements TEXT NOT NULL,
    job_benefits TEXT NOT NULL,
    work_experience TEXT,
    work_type TEXT,
    work_address TEXT,
    number_recruits INT,
    end_time TIMESTAMP,
    cover_url TEXT
);

CREATE TABLE job_infos (
    id SERIAL PRIMARY KEY,
    job_id INT REFERENCES jobs(id) ON DELETE CASCADE,
    title TEXT NOT NULL,
    content TEXT NOT NULL
);

CREATE TABLE contact_forms (
    id SERIAL PRIMARY KEY,
    full_name TEXT NOT NULL,
    phone_number TEXT NOT NULL,
    email TEXT NOT NULL,
    message TEXT,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION update_submitted_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.submitted_at := CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_submitted_at
BEFORE INSERT ON contact_forms
FOR EACH ROW
EXECUTE FUNCTION update_submitted_at();

-- Insert sample data into data_introduction
INSERT INTO data_introduction (title, content, cover_url)
VALUES
('GIỚI THIỆU', 'Công ty MH Solution là một đơn vị hàng đầu chuyên cung cấp các dịch vụ và giải pháp về dữ liệu...', 
'https://cdn.mhdigital.vn/2022/03/17/94821909413467413-1647517280.jpg');

-- Insert sample data into visions
INSERT INTO visions (data_introduction_id, title, content)
VALUES
(1, 'Tầm nhìn', 'TRỞ THÀNH MỘT TẬP ĐOÀN CÔNG NGHỆ TRONG TOP 30 VIỆT NAM VÀO NĂM 2030.');

-- Insert sample data into missions
INSERT INTO missions (data_introduction_id, title, content)
VALUES
(1, 'Sứ mệnh', 'DẪN ĐẦU CÁC XU HƯỚNG CÔNG NGHỆ MỚI ĐỂ MANG TỚI CÁC GIẢI PHÁP CÔNG NGHỆ HIỆU QUẢ CHO KHÁCH HÀNG');

-- Insert sample data into core_values
INSERT INTO core_values (data_introduction_id, title, content)
VALUES
(1, 'Giá trị cốt lõi', 'Chúng tôi tập trung vào phát triển con người và văn hóa doanh nghiệp...');

-- Insert sample data into competitives
INSERT INTO competitives (data_introduction_id, title, content)
VALUES
(1, 'Công nghệ cao', 'Giải quyết các thách thức trong kinh doanh của bạn với công nghệ Big Data, Data Mining, AI, RPA...');

-- Insert sample data into founders
INSERT INTO founders (seo_id, name, avatar, job_title, about)
VALUES
('van-thi-vui-2', 'Văn Thị Vui', 'https://cdn.mhdigital.vn/2021/12/22/63771704111366306-1640168265.png', 'CMO', 'Hơn 10 năm kinh nghiệm mảng Marketing cho các doanh nghiệp B2B & B2C.');

-- Insert sample data into socials
INSERT INTO socials (founder_id, type, src, icon)
VALUES
(1, 'fb', 'https://www.facebook.com/anhappy90', 'https://www.facebook.com/images/fb_icon_325x325.png');

-- Insert sample data into partners
INSERT INTO partners (seo_id, avatar, name, src)
VALUES
('viettel-1', 'https://cdn.mhdigital.vn/2021/11/26/59239146264377269-1637911172.png', 'Viettel', '');

-- Insert sample data into products
INSERT INTO products (seo_id, title, short_content, website, info_url, cover_url, icon_url, icon_hover_url, phone_number, created_at)
VALUES
('nen-tang-du-lieu-mo-open-data-platform-5', 'Nền tảng Dữ liệu mở (Open Data Platform)', 
'Nền tảng tổng hợp, phân tích dữ liệu.', 'https://versatica.io', 'https://cdn.mhdigital.vn/2021/11/30/59239589912113787-1638269666.png',
'https://cdn.mhdigital.vn/2021/12/07/51763272717902880-1638842913.jpg', 'https://cdn.mhdigital.vn/2021/12/09/323670698816469-1639040239.png', 
'https://cdn.mhdigital.vn/2021/12/09/323670698816470-1639040243.png', '0975718168', '2024-11-01');

-- Insert sample data into product_contents
INSERT INTO product_contents (product_id, title, content, src)
VALUES
(1, '', 'Nhiều tổ chức, doanh nghiệp có tiềm năng lớn về dữ liệu...', 'https://techvccloud.mediacdn.vn/2020/10/27/dedicated-server-1.jpg');

-- Insert sample data into product_infos
INSERT INTO product_infos (product_id, title, content)
VALUES
(1, 'Xây dựng kho dữ liệu tập trung', 'Dữ liệu từ nhiều nguồn khác nhau được thu thập và tổ chức...');

-- Insert sample data into data_footer
INSERT INTO data_footer (seo_id, address, email, phone_number)
VALUES
('7173-tran-thai-tong-dich-vong-cau-giay-ha-noi-1', '71-73, Trần Thái Tông, Dịch Vọng, Cầu Giấy, Hà Nội', 'contact@mhdigital.vn', '0965136688');

-- Insert sample data into footer_socials
INSERT INTO footer_socials (footer_id, type, src)
VALUES
(1, 'facebook', 'https://www.facebook.com/mhsolution.vn');

-- Insert sample data into categories
INSERT INTO categories (seo_id, title, content, position)
VALUES
('hoat-dong-xa-hoi-5', 'Hoạt động xã hội', 'Hoạt động xã hội, ủng hộ quỹ, từ thiện...', 4);

-- Insert sample data into news
INSERT INTO news (seo_id, title, content, category_new_id, created_at, cover_url, is_highlights)
VALUES
('workshop-sinh-trac-van-tay--kham-pha-tiem-nang-con-nguoi-98', 'Workshop “Sinh trắc vân tay – Khám phá tiềm năng con người”', 
'Chiều ngày 7/12/2024, workshop “Sinh trắc vân tay – Khám phá tiềm năng con người” đã diễn ra...', 1, '2024-12-07',
'https://cdn.mhdigital.vn/mhsolution/FLMwrQS40B73.jpeg', false);

-- Insert sample data into jobs
INSERT INTO jobs (seo_id, title, salary, level, career, job_description, job_requirements, job_benefits, work_experience, work_type, work_address, number_recruits, end_time, cover_url)
VALUES
('data-engineer-intern-52', 'Data Engineer Intern', 'Thỏa thuận', 'Thực tập sinh', 'Phần mềm', 
'<ul><li>Hỗ trợ thực hiện thiết kế, xây dựng...</li></ul>', '<ul><li>Sinh viên năm 3, 4...</li></ul>', '<ul><li>Được trải nghiệm môi trường...</li></ul>',
'Không yêu cầu kinh nghiệm', 'Full time và Part time', 'Tầng 5, tòa nhà số 71-73...', 4, '2024-11-29 00:00:00', '');

-- Insert sample data into job_infos
INSERT INTO job_infos (job_id, title, content)
VALUES
(1, 'Thông tin thêm', 'Thời gian làm việc Thứ 2 - Thứ 6...');

