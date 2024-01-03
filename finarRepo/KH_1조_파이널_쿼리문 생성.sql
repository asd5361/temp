
-- 허가여부는 추후에 안 쓰면 수정 예정
-- 질문 ) 투표의 유형이 어떻게 되는가?
-----------------
INSERT INTO MEMBER (MEMBER_NO,UNIT_NO,PHONE,PWD,NAME,GENDER,BIRTH) 
VALUES (SEQ_MEMBER_NO.NEXTVAL,1,010-1111-1111,1234,'강현묵묵','F','960703');
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
--------------------------insert //게시글 작성 + 투표 항목 선정
INSERT INTO 
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
    ,1
    ,'01번 테스트 전자 투표'
    ,'테스트용 투표 진행합니다.'
);
INSERT INTO 
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
    SEQ_VOTE_ITEM_NO.NEXTVAL
    ,1
    ,'TEST1'
    ,1
    ,'일반투표'
);
INSERT INTO 
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
    SEQ_VOTE_ITEM_NO.NEXTVAL
    ,1
    ,'TEST2'
    ,2
    ,'일반투표'
);
INSERT INTO 
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
    SEQ_VOTE_ITEM_NO.NEXTVAL
    ,1
    ,'TEST3'
    ,3
    ,'일반투표'
);
COMMIT;
----------------------------------edit //게시글 수정 (글제목, 글내용)
UPDATE 
    VOTE_BOARD 
SET
    TITLE = '변경, 게시글 수정 테스트 투표 제목'
    ,CONTENT = '변경, 게시글 수정 테스트 투표 설명글'
WHERE 
    VOTE_NO = 1
;
---------------------------------delete // 게시글 삭제
UPDATE 
    VOTE_BOARD 
SET
    DEL_YN = 'Y'
WHERE 
    VOTE_NO = 1
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
    ID = 'admin'
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
    VOTE_NO = 1
;
-- 투표 조회
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
    VOTE_NO = 1
GROUP BY
    ITEM_NO, ITEM_NAME, VOTE_ORDER
;
-- 투표 결과 테이블 삽입
INSERT INTO
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
    SEQ_VOTE_HISTORY_NO.NEXTVAL
    ,1
    ,'01 테스트 투표 결과'
    ,3
    ,5
    ,1
);
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
    VOTE_NO = 1
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
SELECT * FROM VOTE_BOARD;
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
---------여기부터 JOIN하기
-------------------------------------------------- history // 투표 결과 이력 조회
SELECT 
    VOTE_HISTORY_NO
    ,VOTE_NO
    ,VOTE_NAME
    ,VOTE_COUNT
    ,VOTE_ORDER
FROM
    VOTE_HISTORY
;


