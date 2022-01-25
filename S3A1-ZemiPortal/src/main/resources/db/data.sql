/* 開発用にデータ削除を追加 : リリース時は消す */
DELETE FROM m_user;

/* ユーザマスタのデータ（ADMIN権限） PASS:pasword */
INSERT INTO m_user (user_id, encrypted_password, user_name, user_role, enabled)
VALUES('isida@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '石田悠介', 'VALID', true);
/* ユーザマスタのデータ（一般権限） PASS:pasword */
INSERT INTO m_user (user_id, encrypted_password, user_name, user_role, enabled)
VALUES('abe@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '安部華奈', 'INVALID', true);
--INSERT INTO m_user (user_id, encrypted_password, user_name , user_role, enabled)
--VALUES('sano@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '佐野翼', 'INVALID', false);

/* カードテーブルのデータ*/
INSERT INTO card (card_id, card_title, user_id)
VALUES (1, 'aaa', 'isida@xxx.co.jp');

/* リストテーブルのデータ*/
INSERT INTO list (list_id, list_title, user_id, card_id) VALUES ('1', 'list', 'isida@xxx.co.jp', '1');

/* ボードテーブルのデータ */
INSERT INTO board (borad_id, board_name, list_id) VALUES ('1', 'avkd', '1');

