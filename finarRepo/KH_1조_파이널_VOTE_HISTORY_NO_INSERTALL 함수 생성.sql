--
CREATE OR REPLACE 
FUNCTION GET_HISTORY_SEQ
RETURN NUMBER 
IS 
    X NUMBER;
BEGIN
    SELECT SEQ_VOTE_HISTORY_NO.NEXTVAL
    INTO X
    FROM DUAL;
    RETURN X;
    END;