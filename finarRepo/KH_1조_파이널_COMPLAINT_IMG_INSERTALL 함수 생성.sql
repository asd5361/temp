--
CREATE OR REPLACE 
FUNCTION GET_COMPLAINT_IMG_SEQ
RETURN NUMBER 
IS 
    X NUMBER;
BEGIN
    SELECT SEQ_COMPLAINT_IMG_NO.NEXTVAL
    INTO X
    FROM DUAL;
    RETURN X;
    END;