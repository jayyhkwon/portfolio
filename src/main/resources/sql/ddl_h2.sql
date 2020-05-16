-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- CLIENT Table Create SQL
CREATE TABLE CLIENT
(
    `id`              BIGINT          NOT NULL    IDENTITY PRIMARY KEY ,
    `phone_number`    VARCHAR(11)     NOT NULL    COMMENT '연락처',
    `push_agree`      TINYINT(1)      NOT NULL    COMMENT '푸시동의 여부',
    `register_date`   TIMESTAMP       NULL        DEFAULT now() COMMENT '등록일',
    `update_date`     TIMESTAMP       NULL        DEFAULT now() COMMENT '수정일'
);


-- CLIENT Table Create SQL
CREATE TABLE TICKET
(
    `id`                BIGINT          NOT NULL    IDENTITY PRIMARY KEY ,
    `client_id`         BIGINT          NULL        COMMENT '고객_id',
    `src`               VARCHAR(255)    NULL        COMMENT '출발지',
    `stopover`          VARCHAR(255)    NULL        COMMENT '경유지',
    `departure`         VARCHAR(255)    NULL        COMMENT '도착지',
    `distance`          DOUBLE          NULL        COMMENT '거리',
    `trip_type_id`      INT             NULL        COMMENT '여행 유형_id',
    `passenger_count`   INT             NULL        COMMENT '탑승객 수',
    `accompany_driver`  TINYINT(1)      NULL        COMMENT '기사_동승 여부',
    `bus_type_id`       BIGINT          NULL        COMMENT '버스 유형',
    `status`            VARCHAR(255)    NULL        COMMENT '상태',
    `expire_date`       TIMESTAMP       NULL        COMMENT '만료기한',
    `src_date`          TIMESTAMP       NULL        COMMENT '출발일',
    `departure_date`    TIMESTAMP       NULL        COMMENT '도착일',
    `payment_type`      VARCHAR(20)     NULL        COMMENT '결제방식',
    `comment`           VARCHAR(500)    NULL        COMMENT '요청사항',
    `register_date`     TIMESTAMP       NULL        DEFAULT now() COMMENT '등록일',
    `update_date`       TIMESTAMP       NULL        DEFAULT now() COMMENT '수정일'
);


-- CLIENT Table Create SQL
CREATE TABLE TRIP_TYPE
(
    `id`             BIGINT          NOT NULL    IDENTITY PRIMARY KEY ,
    `type`           VARCHAR(255)    NULL        COMMENT '여행 유형',
    `register_date`  TIMESTAMP       NULL        DEFAULT now() COMMENT '등록일',
    `update_date`    TIMESTAMP       NULL        DEFAULT now() COMMENT '수정일'
);


-- CLIENT Table Create SQL
CREATE TABLE BUS_TYPE
(
    `id`             BIGINT         NOT NULL    IDENTITY PRIMARY KEY ,
    `type`           VARCHAR(45)    NULL        COMMENT '버스 유형',
    `register_date`  TIMESTAMP      NULL        DEFAULT now() COMMENT '등록일',
    `update_date`    TIMESTAMP      NULL        DEFAULT now() COMMENT '수정일'
);

-- CLIENT Table Create SQL
CREATE TABLE TICKET_BIDDING
(
    `id`                  BIGINT          NOT NULL    IDENTITY PRIMARY KEY ,
    `driver_id`           BIGINT          NULL        COMMENT 'driver_id',
    `ticket_id`           BIGINT          NULL        COMMENT 'ticket_id',
    `client_id`           BIGINT          NULL        COMMENT 'client_id',
    `price`               DOUBLE          NULL        COMMENT '견적가격',
    `add_cost1`           DOUBLE          NOT NULL    COMMENT '추가비용(통행료)',
    `add_cost2`           DOUBLE          NULL        COMMENT '추가비용(주차료)',
    `add_cost3`           DOUBLE          NULL        COMMENT '추가비용(숙박비)',
    `add_cost4`           DOUBLE          NULL        COMMENT '추가비용(식사비)',
    `add_cost5`           DOUBLE          NULL        COMMENT '추가비용(부가세)',
    `register_date`       TIMESTAMP       NULL        DEFAULT now() COMMENT '등록일',
    `update_date`         TIMESTAMP       NULL        DEFAULT now() COMMENT '수정일',
    `client_virtual_num`  VARCHAR(255)    NULL        COMMENT '고객 가상번호',
    `driver_virtual_num`  VARCHAR(255)    NULL        COMMENT '기사 가상번호'
);



-- CLIENT Table Create SQL
CREATE TABLE DRIVER
(
    `id`                        BIGINT           NOT NULL    IDENTITY PRIMARY KEY ,
    `company_id`                BIGINT           NOT NULL    COMMENT '소속회사_id',
    `bus_id`                    BIGINT           NOT NULL    COMMENT '버스_id',
    `face_img`                  VARCHAR(1000)    NULL        COMMENT '기사님 사진',
    `license_img`               VARCHAR(1000)    NOT NULL    COMMENT '면허증',
    `name`                      VARCHAR(45)      NULL        COMMENT '이름',
    `phone_number`              VARCHAR(45)      NULL        COMMENT '연락처',
    `status`                    VARCHAR(45)      NULL        COMMENT '상태',
    `car_photo1`                VARCHAR(1000)    NOT NULL    COMMENT '차량_사진1',
    `car_photo2`                VARCHAR(1000)    NOT NULL    COMMENT '차량_사진2',
    `car_photo3`                VARCHAR(1000)    NOT NULL    COMMENT '차량_사진3',
    `car_photo4`                VARCHAR(1000)    NOT NULL    COMMENT '차량_사진4',
    `comment`                   VARCHAR(1000)    NULL        COMMENT '소개말',
    `score`                     DOUBLE           NULL        COMMENT '평점',
    `insurance`                 TINYINT(1)       NULL        COMMENT '보험여부',
    `bidding_count`             INT              NULL        COMMENT '입찰횟수',
    `concurrent_bidding_count`  INT              NULL        COMMENT '동시 입찰 횟수',
    `review_count`              INT              NULL        COMMENT '리뷰 개수',
    `car_num`                   VARCHAR(45)      NULL        COMMENT '차량번호',
    `register_date`             TIMESTAMP        NULL        DEFAULT now() COMMENT '등록일',
    `update_date`               TIMESTAMP        NULL        DEFAULT now() COMMENT '수정일'
);


-- CLIENT Table Create SQL
CREATE TABLE COMPANY
(
    `id`                BIGINT           NOT NULL    IDENTITY PRIMARY KEY ,
    `company_name`      VARCHAR(255)     NULL        COMMENT '회사명',
    `garage_name`       VARCHAR(255)     NULL        COMMENT '차고지명',
    `garage_address`    VARCHAR(1000)    NULL        COMMENT '차고지 주소',
    `business_license`  VARCHAR(1000)    NULL        COMMENT '사업자등록증',
    `driver_count`      INT              NULL        COMMENT '소속된 기사 수',
    `register_date`     TIMESTAMP        NULL        DEFAULT now() COMMENT '등록일',
    `update_date`       TIMESTAMP        NULL        DEFAULT now() COMMENT '수정일'
);


-- CLIENT Table Create SQL
CREATE TABLE BUS
(
    `id`             BIGINT       NOT NULL    IDENTITY PRIMARY KEY ,
    `bus_type_id`    BIGINT       NOT NULL    COMMENT 'bus_type_id',
    `capacity`       TINYINT      NOT NULL    COMMENT '인승',
    `bus_year`       SMALLINT     NOT NULL    COMMENT '연식',
    `register_date`  TIMESTAMP    NULL        DEFAULT now() COMMENT '등록일',
    `update_date`    TIMESTAMP    NULL        DEFAULT now() COMMENT '수정일'
);


-- CLIENT Table Create SQL
CREATE TABLE REVIEW
(
    `id`             BIGINT           NOT NULL    IDENTITY PRIMARY KEY ,
    `client_id`      BIGINT           NULL        COMMENT '고객_id',
    `driver_id`      BIGINT           NULL        COMMENT '기사_id',
    `comment`        VARCHAR(1000)    NULL        COMMENT '후기 내용',
    `register_date`  DATETIME         NOT NULL    DEFAULT now() COMMENT '등록일',
    `update_date`    DATETIME         NULL        DEFAULT now() COMMENT '수정일'
);

