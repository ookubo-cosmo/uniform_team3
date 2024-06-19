package util;
/*
 * プログラム名：書籍管理プログラムWeb版 Ver2.0
 * プログラムの説明：	書籍情報、購入情報を管理するプログラム
 * 						メール送信機能
 * 作成者：大久保嵩琉
 * 作成日：20240517
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bean.Book;

public class SendMail {

	public void sendMail(String username, String userEmail,ArrayList<Book> list) {
		try {
			Properties props = System.getProperties();

			// SMTPサーバのアドレスを指定（今回はxserverのSMTPサーバを利用）
			props.put("mail.smtp.host", "sv5215.xserver.jp");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.debug", "true");

			Session session = Session.getInstance(
				props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						//メールサーバにログインするメールアドレスとパスワードを設定
						return new PasswordAuthentication("test.sender@kanda-it-school-system.com", "kandaSender202208");
					}
				}
			);

			MimeMessage mimeMessage = new MimeMessage(session);

			// 送信元メールアドレスと送信者名を指定
			mimeMessage.setFrom(new InternetAddress("test.sender@kanda-it-school-system.com", "神田IT School", "iso-2022-jp"));

			// 送信先メールアドレスを指定
			mimeMessage.setRecipients(Message.RecipientType.TO, userEmail);

			// メールのタイトルを指定
			mimeMessage.setSubject("Hello World", "iso-2022-jp");

			String message=	username+"様"+"\n\n"+
							"本のご購入ありがとうございます。\n"+
							"以下内容でご注文を受け付けましたので、ご連絡致します。\n\n";
			
			String[] tmp = new String[3];
							int sumPrice = 0;
							for(int i=0;i<list.size();i++) {
								tmp[0] = list.get(i).getIsbn();
								tmp[1] = list.get(i).getTitle();
								sumPrice += list.get(i).getPrice();
								tmp[2] = String.valueOf(list.get(i).getPrice()) + "円";
								message = message + tmp[0]+"\t" + tmp[1]+"\t" + tmp[2] + "\n";
							}
							
							message = message + "合計" + "\t"+sumPrice + "円\n\n";
							message = message + "またのご利用よろしくお願いします。";
							
							
			
			// メールの内容を指定
			mimeMessage.setText(message, "iso-2022-jp");

			// メールの形式を指定
			mimeMessage.setHeader("Content-Type", "text/plain; charset=iso-2022-jp");

			// 送信日付を指定
			mimeMessage.setSentDate(new Date());

			// 送信します
			Transport.send(mimeMessage);

			// 送信成功
			System.out.println("送信に成功しました。");

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("送信に失敗しました。\n" + e);
		}
	}
}
