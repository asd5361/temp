
-- 허가여부는 추후에 안 쓰면 수정 예정
-- 질문 ) 투표의 유형이 어떻게 되는가? (일반? 공식?)
------------------------------------------------------------ 전자 투표 --------------------------------------------------
-------------------------list // 전체 게시글 조회
SELECT 
    VOTE_NO
    ,V.MANAGER_NO
    ,TITLE
    ,CONTENT
    ,ID
FROM 
    VOTE_BOARD V
JOIN
    MANAGER M
ON 
    M.MANAGER_NO = V.MANAGER_NO
WHERE 
    DEL_YN = 'N' 
ORDER BY 
    VOTE_NO DESC
;
-------------------------detail //게시글 상세 조회
-- 게시글 조회
SELECT 
    VOTE_NO
    ,V.MANAGER_NO
    ,TITLE
    ,CONTENT
    ,ENROLL_DATE
    ,MODIFY_DATE
    ,DEADLINE_DATE
    ,ACCEPT_YN
    ,ID
FROM 
    VOTE_BOARD V
JOIN
    MANAGER M
ON 
    M.MANAGER_NO = V.MANAGER_NO
WHERE 
    DEL_YN = 'N' 
AND
    VOTE_NO = 1
ORDER BY 
    VOTE_NO DESC
;
-- 게시글의 투표 항목 조회
SELECT 
    ITEM_NO
    ,VOTE_NO
    ,ITEM_NAME
    ,VOTE_ORDER
    ,VOTE_TYPE
FROM    
    VOTE_ITEM
WHERE 
    VOTE_NO = 1
;

--------------+++-------위에 두 쿼리 합친 버전 게시글 상세 조회 (+항목)-------+++---------------
SELECT 
    V.VOTE_NO
    ,V.MANAGER_NO
    ,TITLE
    ,CONTENT
    ,ENROLL_DATE
    ,MODIFY_DATE
    ,DEADLINE_DATE
    ,ACCEPT_YN
    ,ID
    ,ITEM_NO
    ,ITEM_NAME
    ,VOTE_ORDER
    ,VOTE_TYPE
FROM 
    VOTE_BOARD V
JOIN
    MANAGER M
ON 
    M.MANAGER_NO = V.MANAGER_NO
JOIN
    VOTE_ITEM I
ON
    V.VOTE_NO = I.VOTE_NO
WHERE 
    DEL_YN = 'N' 
AND
    V.VOTE_NO = 1
ORDER BY 
    V.VOTE_NO DESC
;

-- 로그인 회원 투표 여부 조회
SELECT 
    COUNT(*) AS COUNT
FROM 
    VOTE_REPLY 
WHERE 
    PRTC_NO = 6
AND 
    VOTE_NO = 1
;

-- 조회수 증가
UPDATE
    VOTE_BOARD
SET 
    HIT = HIT + 1
WHERE VOTE_NO = 5
;
--------------------------insert //게시글 작성 + 투표 항목 선정

INSERT
INTO 
VOTE_BOARD
(
    VOTE_NO
    ,MANAGER_NO
    ,TITLE
    ,CONTENT
)
VALUES 
(
    SEQ_VOTE_BOARD_NO.NEXTVAL
    ,11
    ,'11번 테스트 전자 투표'
    ,'테스트용 투표 진행합니다.'
);
    
INSERT All
INTO
VOTE_ITEM
(
    ITEM_NO
    ,VOTE_NO
    ,ITEM_NAME
    ,VOTE_ORDER
    ,VOTE_TYPE
)
VALUES
(
    (SELECT GET_ITEM_SEQ() FROM DUAL )
    ,6
    ,'TEST1'
    ,1
    ,'일반투표'
)
INTO 
VOTE_ITEM
(
    ITEM_NO
    ,VOTE_NO
    ,ITEM_NAME
    ,VOTE_ORDER
    ,VOTE_TYPE
)
VALUES
(
    (SELECT GET_ITEM_SEQ() FROM DUAL )
    ,6
    ,'TEST2'
    ,2
    ,'일반투표'
)
INTO 
VOTE_ITEM
(
    ITEM_NO
    ,VOTE_NO
    ,ITEM_NAME
    ,VOTE_ORDER
    ,VOTE_TYPE
)
VALUES
(
    (SELECT GET_ITEM_SEQ() FROM DUAL )
    ,6
    ,'TEST3'
    ,3
    ,'일반투표'
)
select * from dual;
COMMIT;
----------------------------------edit //게시글 수정 (글제목, 글내용)
UPDATE 
    VOTE_BOARD 
SET
    TITLE = '변경, 게시글 수정 테스트 투표 제목'
    ,CONTENT = '변경, 게시글 수정 테스트 투표 설명글'
WHERE 
    VOTE_NO = 6
;
---------------------------------delete // 게시글 삭제
UPDATE 
    VOTE_BOARD 
SET
    DEL_YN = 'Y'
WHERE 
    VOTE_NO = 6
;
-------------------------------------select //게시글 검색
SELECT 
    VOTE_NO
    ,V.MANAGER_NO
    ,TITLE
    ,CONTENT
    ,ID
FROM 
    VOTE_BOARD V
JOIN
    MANAGER M
ON 
    M.MANAGER_NO = V.MANAGER_NO
WHERE
    DEL_YN = 'N'
AND
    TITLE like '%투표%'
AND 
    CONTENT like '%투표%'
ORDER BY 
    VOTE_NO DESC
;
------------------------------------------ voteCount // 전체 투표 수 조회 (투표율)
SELECT 
    COUNT(*) 
FROM 
    VOTE_ITEM 
WHERE 
    VOTE_NO = 1
;
---------------------------------------------voteEnds //투표종료
-- 마감 일자 삽입
UPDATE
    VOTE_BOARD
SET
    DEADLINE_DATE = SYSDATE
WHERE
    VOTE_NO = 3
;
-- 투표 조회
SELECT 
    VOTE_NO
    ,ITEM_NAME
    ,COUNT(*)
    ,VOTE_ORDER
FROM 
    (SELECT 
        R.REPLY_NO, R.VOTE_NO, R.ITEM_NO, ITEM_NAME, VOTE_ORDER
    FROM 
        VOTE_REPLY R
    JOIN
        VOTE_ITEM I
    ON
        R.ITEM_NO = I.ITEM_NO)
WHERE 
    VOTE_NO = 3
GROUP BY
    VOTE_NO,ITEM_NO, ITEM_NAME, VOTE_ORDER
;
-- 투표 모든 결과 테이블 삽입
INSERT ALL 
INTO
    VOTE_HISTORY
(
    VOTE_HISTORY_NO
    ,VOTE_NO
    ,VOTE_NAME
    ,VOTE_COUNT
    ,VOTE_ORDER
)
VALUES
(
    (SELECT GET_HISTORY_SEQ() FROM DUAL )
    ,3
    ,'보라색'
    ,1
    ,1
)
INTO
    VOTE_HISTORY
(
    VOTE_HISTORY_NO
    ,VOTE_NO
    ,VOTE_NAME
    ,VOTE_COUNT
    ,VOTE_ORDER
)
VALUES
(
    (SELECT GET_HISTORY_SEQ() FROM DUAL )
    ,3
    ,'파란색'
    ,3
    ,2
)
INTO
    VOTE_HISTORY
(
    VOTE_HISTORY_NO
    ,VOTE_NO
    ,VOTE_NAME
    ,VOTE_COUNT
    ,VOTE_ORDER
)
VALUES
(
    (SELECT GET_HISTORY_SEQ() FROM DUAL )
    ,3
    ,'노란색'
    ,2
    ,3
)
SELECT * FROM DUAL
;
----------------------------------------------- voteCheck // 투표 조회
SELECT 
    ITEM_NAME
    ,COUNT(*)
    ,VOTE_ORDER
FROM 
    (SELECT 
        R.REPLY_NO, R.VOTE_NO, R.ITEM_NO, ITEM_NAME, VOTE_ORDER
    FROM 
        VOTE_REPLY R
    JOIN
        VOTE_ITEM I
    ON
        R.ITEM_NO = I.ITEM_NO)
WHERE 
    VOTE_NO = 5
GROUP BY
    ITEM_NO, ITEM_NAME, VOTE_ORDER
;

-------------------------------------------------voting // 투표하기
INSERT INTO
    VOTE_REPLY
(
    REPLY_NO
    ,VOTE_NO
    ,ITEM_NO
    ,PRTC_NO
)
VALUES
(
    SEQ_VOTE_REPLY_NO.NEXTVAL
    ,1
    ,3
    ,11
)
;
-----------------------------------list // 관리자 전체 투표 게시글 조회
SELECT 
    VOTE_NO
    ,V.MANAGER_NO
    ,TITLE
    ,CONTENT
    ,ENROLL_DATE
    ,MODIFY_DATE
    ,DEADLINE_DATE
    ,DEL_YN
    ,ACCEPT_YN
    ,ID
FROM 
    VOTE_BOARD V
JOIN
    MANAGER M
ON 
    M.MANAGER_NO = V.MANAGER_NO
ORDER BY 
    VOTE_NO DESC
;

--------------------------------------detail // 관리자 게시글 상세 조회
SELECT 
    VOTE_NO
    ,V.MANAGER_NO
    ,TITLE
    ,CONTENT
    ,ENROLL_DATE
    ,MODIFY_DATE
    ,DEADLINE_DATE
    ,ACCEPT_YN
    ,ID
FROM 
    VOTE_BOARD V
JOIN
    MANAGER M
ON 
    M.MANAGER_NO = V.MANAGER_NO
WHERE 
    VOTE_NO = 1
ORDER BY 
    VOTE_NO DESC
;
SELECT 
    ITEM_NO
    ,VOTE_NO
    ,ITEM_NAME
    ,VOTE_ORDER
    ,VOTE_TYPE
FROM    
    VOTE_ITEM
WHERE 
    VOTE_NO = 1
;
------------------------------------------select // 관리자 게시글 검색
SELECT 
    VOTE_NO
    ,V.MANAGER_NO
    ,TITLE
    ,CONTENT
    ,ENROLL_DATE
    ,MODIFY_DATE
    ,DEADLINE_DATE
    ,ACCEPT_YN
    ,ID
FROM 
    VOTE_BOARD V
JOIN
    MANAGER M
ON 
    M.MANAGER_NO = V.MANAGER_NO
AND
    ID = 'admin'
AND
    TITLE like '%투표%'
AND 
    CONTENT like '%투표%'
AND
    (ENROLL_DATE BETWEEN TO_DATE('20240103') AND TO_DATE('20240104'))
AND
    DEADLINE_DATE IS NULL
AND
    DEL_YN = 'N'
AND
    ACCEPT_YN = 'N'
ORDER BY 
    VOTE_NO DESC
;
-------------------------------------------------- history // 투표 결과 상세 조회
SELECT 
    VOTE_HISTORY_NO
    ,MANAGER_NO
    ,TITLE
    ,CONTENT
    ,ACCEPT_YN
    ,H.VOTE_NO
    ,VOTE_NAME
    ,VOTE_COUNT
    ,VOTE_ORDER
FROM
    VOTE_HISTORY H
JOIN
    VOTE_BOARD B
ON
    H.VOTE_NO = B.VOTE_NO
WHERE
    H.VOTE_NO = 3
;
----------------------------------------------- history // 관리자 투표 전체 결과 이력 조회  
SELECT 
    VOTE_HISTORY_NO
    ,MANAGER_NO
    ,TITLE
    ,CONTENT
    ,ENROLL_DATE
    ,DEADLINE_DATE
    ,ACCEPT_YN
    ,H.VOTE_NO
    ,VOTE_NAME
    ,VOTE_COUNT
    ,VOTE_ORDER
FROM
    VOTE_HISTORY H
JOIN
    VOTE_BOARD B
ON
    H.VOTE_NO = B.VOTE_NO
;
-----------------------------------------------------------------------------------민원접수----------------------------------------------
-----------------------mySumitList//내 민원 글조회 (썸네일 사용 시)
SELECT 
    C.COMPLAINT_NO
    ,MEMBER_NO
    ,TITLE
    
    ,ENROLL_DATE
    ,STATUS
    ,IMG_NO
    ,IMG_NAME
    ,PATH
FROM
    COMPLAINT C
JOIN
    COMPLAINT_IMG I
ON
    C.COMPLAINT_NO = I.COMPLAINT_NO
WHERE
    DEL_YN = 'N'
AND
    C.MEMBER_NO = 1
ORDER BY
    COMPLAINT_NO DESC
;

-----------------------------------------sumit //접수
-- 민원 접수
INSERT INTO
    COMPLAINT
(
    COMPLAINT_NO
    ,MANAGER_NO
    ,MEMBER_NO
    ,TITLE
    ,CONTENT
)
VALUES
(
    SEQ_COMPLAINT_NO.NEXTVAL
    ,1
    ,6
    ,'666 테스트 민원 글 제목입니다.'
    ,'666 민원 테스트 본문 글입니다.'
)
;
-- 민원에 첨부된 사진 삽입
INSERT ALL
INTO
    COMPLAINT_IMG
(
    IMG_NO
    ,COMPLAINT_NO
    ,IMG_NAME
    ,PATH
    ,ORIGIN_NAME
)
VALUES
(
    (SELECT GET_COMPLAINT_IMG_SEQ() FROM DUAL )
    ,SEQ_COMPLAINT_NO.CURRVAL
    ,'complaint_img_' || TO_CHAR(SYSDATE,'YYYYMMDDHH24miSS')
    ,'resources/uplode/'
    ,'img06'
)
INTO
    COMPLAINT_IMG
(
    IMG_NO
    ,COMPLAINT_NO
    ,IMG_NAME
    ,PATH
    ,ORIGIN_NAME
)
VALUES
(
    (SELECT GET_COMPLAINT_IMG_SEQ() FROM DUAL )
    ,SEQ_COMPLAINT_NO.CURRVAL
    ,'complaint_img_' || TO_CHAR(SYSDATE,'YYYYMMDDHH24miSS')
    ,'resources/uplode/'
    ,'img07'
)
SELECT * FROM DUAL
;


-------------------------------------mySumitDetail // 내 민원 상세 조회
SELECT 
    C.COMPLAINT_NO
    ,MEMBER_NO
    ,TITLE
    ,CONTENT
    ,ENROLL_DATE
    ,STATUS
    ,REPLY
    ,REPLY_DATE 
    ,IMG_NO
    ,IMG_NAME
    ,PATH
FROM
    COMPLAINT C
LEFT JOIN
    COMPLAINT_IMG I
ON
    C.COMPLAINT_NO = I.COMPLAINT_NO
WHERE
    DEL_YN = 'N'
AND
    C.MEMBER_NO = 6
AND
    C.COMPLAINT_NO = 6
;
--------------------------------------list // 전체 게시글 조회
SELECT 
    C.COMPLAINT_NO
    ,MANAGER_NO
    ,MEMBER_NO
    ,TITLE
    ,CONTENT
    ,ENROLL_DATE
    ,DEL_YN
    ,STATUS
    ,REPLY
    ,REPLY_DATE 
    ,IMG_NO
    ,IMG_NAME
    ,PATH
    ,ORIGIN_NAME
FROM
    COMPLAINT C
JOIN
    COMPLAINT_IMG I
ON
    C.COMPLAINT_NO = I.COMPLAINT_NO
ORDER BY
    COMPLAINT_NO DESC
;
-------------------------------------detail //게시글 상세 조회
SELECT 
    C.COMPLAINT_NO
    ,MANAGER_NO
    ,MEMBER_NO
    ,TITLE
    ,CONTENT
    ,ENROLL_DATE
    ,DEL_YN
    ,STATUS
    ,REPLY
    ,REPLY_DATE 
    ,IMG_NO
    ,IMG_NAME
    ,PATH
    ,ORIGIN_NAME
FROM
    COMPLAINT C
JOIN
    COMPLAINT_IMG I
ON
    C.COMPLAINT_NO = I.COMPLAINT_NO
WHERE
    C.COMPLAINT_NO = 6
;
-- clear // 민원 해결 글 작성
UPDATE 
    COMPLAINT
SET
    MANAGER_NO = 1
    ,STATUS = 'Y'
    ,REPLY = '테스트 답변 입니다.'
    ,REPLY_DATE = SYSDATE
WHERE
    COMPLAINT_NO = 6
;
--------------------------------------여기부터
--select // 전체 민원 검색
SELECT  
    C.COMPLAINT_NO
    ,MANAGER_NO
    ,MEMBER_NO
    ,TITLE
    ,CONTENT
    ,ENROLL_DATE
    ,DEL_YN
    ,STATUS
    ,REPLY
    ,REPLY_DATE
    ,IMG_NO
    ,IMG_NAME
    ,PATH
    ,ORIGIN_NAME
FROM
    COMPLAINT C
JOIN
    COMPLAINT_IMG I
ON
    C.COMPLAINT_NO = I.COMPLAINT_NO
WHERE
    TITLE LIKE '%%'
AND
    CONTENT LIKE '%%'
AND
    DEL_YN LIKE '%%'
--OR
--    REPLY LIKE '%%'
--AND
--    REPLY IS NULL
--AND
--    STATUS  LIKE '%%' 
--OR STATUS LIKE IS NULL
AND
    (ENROLL_DATE BETWEEN TO_DATE('20230101') AND TO_DATE('20241231'))
--AND
--    (REPLY_DATE BETWEEN TO_DATE('20230101') AND TO_DATE('20241231'))
ORDER BY
    C.COMPLAINT_NO DESC
;
