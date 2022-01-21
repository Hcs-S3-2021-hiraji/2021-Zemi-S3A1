/* 開発用にデータ削除を追加 : リリース時は消す
DROP TABLE m_user;
DROP TABLE card;
*/

/* ユーザマスタ */
CREATE TABLE IF NOT EXISTS m_user (
    user_id VARCHAR(50) PRIMARY KEY,						--ユーザID
    encrypted_password VARCHAR(100) NOT NULL,				--暗号化されたパスワード
    user_name VARCHAR(50) NOT NULL,						--ユーザ名
    user_role VARCHAR(50) ,									--ユーザ権限
    enabled BOOLEAN											--有効・無効
);

/*カードテーブル*/
CREATE TABLE IF NOT EXISTS card (
	card_id INT  PRIMARY KEY,						        --カードID
	card_title VARCHAR(100),								--タイトル
	/*card_detail VARCHAR(254),								--詳細*/
	user_id VARCHAR(60) REFERENCES m_user (user_id)		--ユーザID(外部キー)
	/*user_name VARCHAR(60) ,									--ユーザの名前
	card_date TIMESTAMP,									--日付
	card_check CHAR(5),										--チェックリスト
	card_description VARCHAR(254),							--説明
	card_detail_description VARCHAR(254)					--詳細説明*/
);

/*リストテーブル*/
CREATE TABLE IF NOT EXISTS list (
	list_id VARCHAR(254) PRIMARY KEY,						--リストID
	list_title VARCHAR(100),								--タイトル
	user_id VARCHAR(60) REFERENCES m_user (user_id),		--ユーザID(外部キー)
	card_id VARCHAR(254) REFERENCES card (card_id)			--カードID(外部キー)
);

/* ボードテーブル*/
CREATE TABLE IF NOT EXISTS board (
	borad_id VARCHAR(254) PRIMARY KEY,						--ボードID
	board_name VARCHAR(60),									--ボード名
	list_id VARCHAR(254) REFERENCES list (list_id)			--リストID(外部キー)
);

