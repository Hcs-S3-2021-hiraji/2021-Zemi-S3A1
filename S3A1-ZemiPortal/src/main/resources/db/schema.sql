/* 開発用にデータ削除を追加 : リリース時は消す
DROP TABLE m_user;
DROP TABLE card;
DROP TABLE list;
*/

/* ユーザマスタ */
CREATE TABLE IF NOT EXISTS m_user (
    user_id VARCHAR(50) PRIMARY KEY,
    encrypted_password VARCHAR(100) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    user_role VARCHAR(50) NOT NULL,
    user_status VARCHAR(7) NOT NULL,
    created_at TIMESTAMP,
    registeredperson_id VARCHAR(254),
    updated_at TIMESTAMP,
    changer_id VARCHAR(254),
    enabled BOOLEAN
);

/*カードテーブル*/
CREATE TABLE IF NOT EXISTS card (
 card_id VARCHAR(254) PRIMARY KEY,
 card_title VARCHAR(100),
 card_detail VARCHAR(254),
 user_id VARCHAR(60) REFERENCES m_user (user_id),
 list_id VARCHAR(254) REFERENCES list (list_id),
 card_date TIMESTAMP,
 card_check CHAR(5),
 card_description VARCHAR(254),
 card_detail_description VARCHAR(254)
);

/*リストテーブル*/
CREATE TABLE IF NOT EXISTS list (
 list_id VARCHAR(254) PRIMARY KEY,
 list_title VARCHAR(100),
 user_id VARCHAR(60) REFERENCES m_user (user_id),
 card_id VARCHAR(254) REFERENCES card (card_id)
);
