-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- CLIENT Table Create SQL
CREATE TABLE CLIENT
(
    `id`             BIGINT         NOT NULL    IDENTITY PRIMARY KEY COMMENT 'id',
    `phone_number`   VARCHAR(11)    NOT NULL    COMMENT '연락처',
    `push_agree`     TINYINT(1)     NOT NULL    COMMENT '푸시동의 여부',
    `register_date`  TIMESTAMP      NULL        DEFAULT now() COMMENT '등록일',
    `update_date`    TIMESTAMP      NULL        DEFAULT now() COMMENT '수정일'
);


-- CLIENT Table Create SQL
CREATE TABLE TICKET
(
    `id`             BIGINT          NOT NULL    IDENTITY PRIMARY KEY COMMENT 'id',
    `client_id`      BIGINT          NOT NULL    COMMENT '고객_id',
    `trip_type_id`   BIGINT          NOT NULL    COMMENT '여행 유형_id',
    `bus_type_id`    BIGINT          NOT NULL    COMMENT '버스 유형',
    `src_name`       VARCHAR(255)    NOT NULL    COMMENT '출발지',
    `src_address`    VARCHAR(255)    NOT NULL    COMMENT '출발지 주소',
    `src_latitude`   DOUBLE          NOT NULL    COMMENT '출발지 위도',
    `src_longitude`  DOUBLE          NOT NULL    COMMENT '출발지 경도',
    `stopover`       VARCHAR(255)    NULL        COMMENT '경유지',
    `dst_name`       VARCHAR(255)    NOT NULL    COMMENT '도착지',
    `dst_address`    VARCHAR(255)    NOT NULL    COMMENT '도착지 주소',
    `dst_latitude`   DOUBLE          NOT NULL    COMMENT '도착지 위도',
    `dst_longitude`  DOUBLE          NOT NULL    COMMENT '도착지 경도',
    `depart_date`    TIMESTAMP       NOT NULL    COMMENT '출발일',
    `return_date`    TIMESTAMP       NOT NULL    COMMENT '도착일',
    `together`       TINYINT(1)      NOT NULL    COMMENT '기사_동승 여부',
    `user_cnt`       INT             NULL        COMMENT '탑승객 수',
    `card`           TINYINT(1)      NULL        DEFAULT 0 COMMENT '카드결제여부',
    `tax_receipt`    TINYINT(1)      NULL        DEFAULT 0 COMMENT '세금계산서 여부',
    `distance`       DOUBLE          NULL        COMMENT '거리',
    `status`         VARCHAR(255)    NOT NULL    COMMENT '상태',
    `cancel_reason`  VARCHAR(45)     NULL        COMMENT '취소사유',
    `comment`        VARCHAR(500)    NULL        COMMENT '요청사항',
    `expire_date`    TIMESTAMP       NOT NULL    COMMENT '만료기한',
    `register_date`  TIMESTAMP       NULL        DEFAULT now() COMMENT '등록일',
    `update_date`    TIMESTAMP       NULL        DEFAULT now() COMMENT '수정일'
);


-- CLIENT Table Create SQL
CREATE TABLE TRIP_TYPE
(
    `id`             BIGINT          NOT NULL    IDENTITY PRIMARY KEY COMMENT 'id',
    `type`           VARCHAR(255)    NOT NULL    COMMENT '여행 유형',
    `register_date`  TIMESTAMP       NULL        DEFAULT now() COMMENT '등록일',
    `update_date`    TIMESTAMP       NULL        DEFAULT now() COMMENT '수정일'
);


-- CLIENT Table Create SQL
CREATE TABLE BUS_TYPE
(
    `id`             BIGINT         NOT NULL    IDENTITY PRIMARY KEY COMMENT 'id',
    `type`           VARCHAR(45)    NOT NULL    COMMENT '버스 유형',
    `register_date`  TIMESTAMP      NULL        COMMENT '등록일',
    `update_date`    TIMESTAMP      NULL        DEFAULT now() COMMENT '수정일'
);


-- CLIENT Table Create SQL
CREATE TABLE BUS_DRIVER
(
    `id`                      BIGINT           NOT NULL    IDENTITY PRIMARY KEY COMMENT 'id',
    `bus_id`                  BIGINT           NOT NULL    COMMENT '버스_id',
    `bus_company_id`          BIGINT           NOT NULL    COMMENT '소속회사_id',
    `name`                    VARCHAR(45)      NOT NULL    COMMENT '이름',
    `face_img`                VARCHAR(1000)    NOT NULL    COMMENT '기사님 사진',
    `license_img`             VARCHAR(1000)    NOT NULL    COMMENT '면허증',
    `phone_number`            VARCHAR(45)      NULL        COMMENT '연락처',
    `status`                  VARCHAR(45)      NOT NULL    COMMENT '상태',
    `car_num`                 VARCHAR(45)      NULL        COMMENT '차량번호',
    `car_photo1`              VARCHAR(1000)    NOT NULL    COMMENT '차량_사진1',
    `car_photo2`              VARCHAR(1000)    NOT NULL    COMMENT '차량_사진2',
    `car_photo3`              VARCHAR(1000)    NOT NULL    COMMENT '차량_사진3',
    `car_photo4`              VARCHAR(1000)    NOT NULL    COMMENT '차량_사진4',
    `comment`                 VARCHAR(1000)    NULL        COMMENT '소개말',
    `score`                   DOUBLE           NULL        COMMENT '평점',
    `insurance`               TINYINT(1)       NOT NULL    COMMENT '보험여부',
    `bidding_cnt`             INT              NULL        COMMENT '입찰횟수',
    `concurrent_bidding_cnt`  INT              NULL        COMMENT '동시 입찰 횟수',
    `review_cnt`              INT              NULL        COMMENT '리뷰 개수',
    `register_date`           TIMESTAMP        NULL        DEFAULT now() COMMENT '등록일',
    `update_date`             TIMESTAMP        NULL        DEFAULT now() COMMENT '수정일'
);


-- CLIENT Table Create SQL
CREATE TABLE BUS_COMPANY
(
    `id`                BIGINT           NOT NULL    IDENTITY PRIMARY KEY COMMENT 'id',
    `company_name`      VARCHAR(255)     NOT NULL    COMMENT '회사명',
    `garage_name`       VARCHAR(255)     NOT NULL    COMMENT '차고지명',
    `garage_address`    VARCHAR(1000)    NOT NULL    COMMENT '차고지 주소',
    `business_license`  VARCHAR(1000)    NOT NULL    COMMENT '사업자등록증',
    `driver_cnt`        INT              NULL        COMMENT '소속된 기사 수',
    `register_date`     TIMESTAMP        NULL        DEFAULT now() COMMENT '등록일',
    `update_date`       TIMESTAMP        NULL        DEFAULT now() COMMENT '수정일'
);


-- CLIENT Table Create SQL
CREATE TABLE BUS
(
    `id`             BIGINT          NOT NULL    IDENTITY PRIMARY KEY COMMENT 'id',
    `bus_type_id`    BIGINT          NOT NULL    COMMENT 'bus_type_id',
    `name`           VARCHAR(255)    NOT NULL    COMMENT '버스명',
    `capacity`       TINYINT         NOT NULL    COMMENT '인승',
    `bus_year`       SMALLINT        NOT NULL    COMMENT '연식',
    `register_date`  TIMESTAMP       NULL        COMMENT '등록일',
    `update_date`    TIMESTAMP       NULL        DEFAULT now() COMMENT '수정일'
);


-- CLIENT Table Create SQL
CREATE TABLE REVIEW
(
    `id`             BIGINT           NOT NULL    IDENTITY PRIMARY KEY COMMENT 'id',
    `client_id`      BIGINT           NOT NULL    COMMENT '고객_id',
    `driver_id`      BIGINT           NOT NULL    COMMENT '기사_id',
    `comment`        VARCHAR(1000)    NOT NULL    COMMENT '후기 내용',
    `register_date`  DATETIME         NULL        DEFAULT now() COMMENT '등록일',
    `update_date`    DATETIME         NULL        DEFAULT now() COMMENT '수정일'
);


-- CLIENT Table Create SQL
CREATE TABLE BIDDING
(
    `id`               BIGINT       NOT NULL    IDENTITY PRIMARY KEY COMMENT 'id',
    `driver_id`        BIGINT       NOT NULL    COMMENT '기사_id',
    `ticket_id`        BIGINT       NOT NULL    COMMENT '티켓_id',
    `price`            DOUBLE       NOT NULL    COMMENT '견적가격',
    `additional_fee1`  DOUBLE       NOT NULL    COMMENT '추가비용(통행료)',
    `additional_fee2`  DOUBLE       NOT NULL    COMMENT '추가비용(주차료)',
    `additional_fee3`  DOUBLE       NOT NULL    COMMENT '추가비용(숙박비)',
    `additional_fee4`  DOUBLE       NOT NULL    COMMENT '추가비용(식사비)',
    `additional_fee5`  DOUBLE       NOT NULL    COMMENT '추가비용(부가세)',
    `register_date`    TIMESTAMP    NULL        DEFAULT now() COMMENT '등록일',
    `update_date`      TIMESTAMP    NULL        DEFAULT now() COMMENT '수정일'
);





-- TRIP_TYPE 데이터
INSERT INTO TRIP_TYPE(type)
values ('WEDDING');
INSERT INTO TRIP_TYPE(type)
values ('WORKSHOP');
INSERT INTO TRIP_TYPE(type)
values ('MT');
INSERT INTO TRIP_TYPE(type)
values ('TOUR');
INSERT INTO TRIP_TYPE(type)
values ('MEETING');
INSERT INTO TRIP_TYPE(type)
values ('RELIGION');
INSERT INTO TRIP_TYPE(type)
values ('FIELD_TRIP');
INSERT INTO TRIP_TYPE(type)
values ('CLUB');
INSERT INTO TRIP_TYPE(type)
values ('CONCERT');
INSERT INTO TRIP_TYPE(type)
values ('ETC');


-- BUS_TYPE 데이터
INSERT INTO BUS_TYPE(type)
values ('MINI');
INSERT INTO BUS_TYPE(type)
values ('MINI_PRIMIUM');
INSERT INTO BUS_TYPE(type)
values ('MEDIUM');
INSERT INTO BUS_TYPE(type)
values ('MEDIUM_PRIMUIM');
INSERT INTO BUS_TYPE(type)
values ('LARGE');
INSERT INTO BUS_TYPE(type)
values ('LARGE_PRIMIUM');
INSERT INTO BUS_TYPE(type)
values ('PRIMIUM');

-- BUS_COMPANY 데이터
INSERT INTO BUS_COMPANY(company_name, garage_name, garage_address, business_license, driver_cnt)
VALUES ('1번회사', '서울차고', '서울시 송파구', '', 15);
INSERT INTO BUS_COMPANY(company_name, garage_name, garage_address, business_license, driver_cnt)
VALUES ('2번회사', '경기도 차고', '경기도 수원시', '', 10);

-- BUS 데이터
INSERT INTO BUS(name, bus_type_id, capacity, bus_year)
VALUES ('벤츠 버스', 1, 25, 2010);
INSERT INTO BUS(name, bus_type_id, capacity, bus_year)
VALUES ('현대 버스', 2, 35, 2019);
INSERT INTO BUS(name, bus_type_id, capacity, bus_year)
VALUES ('기아 버스', 3, 15, 2020);
INSERT INTO BUS(name, bus_type_id, capacity, bus_year)
VALUES ('스타렉스',4, 40, 2015);

-- BUS_DRIVER 데이터
INSERT INTO BUS_DRIVER(bus_company_id, bus_id, face_img, license_img, name, phone_number, status, car_photo1, car_photo2,
                       car_photo3, car_photo4, comment, score, insurance, bidding_cnt, concurrent_bidding_cnt,
                       review_cnt, car_num)
VALUES (1, 1, '', '', '1번기사', '123', 'ABLE', '', '', '', '', '1번 기사입니다', 2.3, 1, 10, 15, 3, '1234');
INSERT INTO BUS_DRIVER(bus_company_id, bus_id, face_img, license_img, name, phone_number, status, car_photo1, car_photo2,
                       car_photo3, car_photo4, comment, score, insurance, bidding_cnt, concurrent_bidding_cnt,
                       review_cnt, car_num)
VALUES (2, 2, '', '', '2번기사', '123', 'ABLE', '', '', '', '', '2번 기사입니다', 2.3, 1, 10, 15, 3, '1234');
INSERT INTO BUS_DRIVER(bus_company_id, bus_id, face_img, license_img, name, phone_number, status, car_photo1, car_photo2,
                       car_photo3, car_photo4, comment, score, insurance, bidding_cnt, concurrent_bidding_cnt,
                       review_cnt, car_num)
VALUES (1, 3, '', '', '3번기사', '123', 'ABLE', '', '', '', '', '3번 기사입니다', 2.3, 1, 10, 15, 3, '1234');


-- BIDDING 데이터
INSERT INTO BIDDING(driver_id, ticket_id, price, additional_fee1, additional_fee2, additional_fee3, additional_fee4,
                    additional_fee5)
VALUES (1, 1, 250000, 0, 0, 0, 0, 0);
INSERT INTO BIDDING(driver_id, ticket_id, price, additional_fee1, additional_fee2, additional_fee3, additional_fee4,
                    additional_fee5)
VALUES (2, 1, 200000, 0, 0, 0, 0, 0);
INSERT INTO BIDDING(driver_id, ticket_id, price, additional_fee1, additional_fee2, additional_fee3, additional_fee4,
                    additional_fee5)
VALUES (3, 1, 150000, 0, 0, 0, 0, 0);
