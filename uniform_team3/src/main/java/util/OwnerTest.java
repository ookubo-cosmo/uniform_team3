package util;

/*
 * プログラム名		:ユニフォーム受注システム
 * プログラムの説明	:ユニフォームの注文を管理するプログラム
 * 作成者：大久保嵩琉
 * 作成日：20240621
 */
import bean.Owner;
import dao.OwnerDAO;



public class OwnerTest {
	public static void main(String[] args) {
		OwnerDAO ownerObj = new OwnerDAO();
		Owner owner = ownerObj.selectByOwneridAndPassword("test","test");
		if(owner==null) {
			System.out.println("fail");
		}
	}
}
