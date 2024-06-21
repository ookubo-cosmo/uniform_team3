package util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bean.Item;
import bean.Order;
import dao.ItemDAO;

public class SendMail {

	public void sendMail(Order order, int mail_status) {
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
			
			String title;
			StringBuilder text = new StringBuilder();
			
			ItemDAO itemDao = new ItemDAO();
			Item item = itemDao.selectByItemid(order.getItemid());
			
			if(mail_status == 1) {
				
				title = "[株式会社神田ユニフォーム]ご注文ありがとうございます";
				
				
				text.append(order.getName());
				text.append("様\n\n");
				
				text.append("この度はご注文していただき、誠にありがとうございます。\n\n");
				
				text.append( "早速ご注文いただきました件について\n");
				text.append( "ご案内させていただきたいと思います。\n");
				text.append("まずは今回ご注文いただいた内容につきまして\n");
				text.append("間違いがないかご確認ください。\n\n");
				text.append("今回のご注文内容は下記の通りです。\n\n");
				
				text.append("[受注番号]" + order.getOrderid() + "\n");
				text.append("[注文日時]" + order.getOrder_date() + "\n");
				text.append("[ご注文者]" + order.getName() + "\n\n");
				
				text.append("[商品名]" + item.getItemName() + "\n");
				text.append("[注文個数]" + order.getQuantity() + "\n");
				text.append("[合計]" + item.getPrice()*order.getQuantity() + "円\n\n");
				
				text.append("指定日の無い方は最短で出荷をさせていただきます。\n");
				text.append("発送いたしましたら、再度ご連絡差し上げますので今しばらくお待ち下さい。\n\n");
				
				text.append("下記口座に合計金額をお振込みください。\n");
				text.append("ご入金確認後、発送手配いたします。\n\n");
				
				text.append("[振込口座先銀行]  ○○銀行\n");
				text.append("[支店名]  ○○○支店\n");
				text.append("[口座種別・番号]  普通 12345678");
				text.append("[口座名]  株式会社神田ユニフォーム");
				
				
			}else if (mail_status == 2) {
				//入金確認のメール
				title = "[株式会社神田ユニフォーム]ご入金ありがとうございます";
				
				
				text.append(order.getName());
				text.append("様\n\n");
				
				text.append("ご注文の代金をお振込みいただき、誠にありがとうございます。");
				text.append("ご入金を確認いたしましたので、発送手続きを開始させていただきます。");
				text.append("発送いたしましたら、再度ご連絡差し上げますので今しばらくお待ち下さい。\n\n");
				
				text.append("ご注文内容詳細");
				
				text.append("[受注番号]" + order.getOrderid() + "\n");
				text.append("[注文日時]" + order.getOrder_date() + "\n");
				text.append("[ご注文者]" + order.getName() + "\n\n");
				
				text.append("[商品名]" + item.getItemName() + "\n");
				text.append("[注文個数]" + order.getQuantity() + "\n");
				text.append("[合計]" + item.getPrice()*order.getQuantity() + "円\n\n");
				
				
			}else {
				//発送完了のメール
				title = "[株式会社神田ユニフォーム]商品を発送いたしました";
				
				
				text.append(order.getName());
				text.append("様\n\n");
				
				text.append("ご注文の商品を発送いたしました。");
				
				text.append("配送情報");
				
				text.append("[配送業者]  〇〇配送");
				text.append("[追跡番号]  1234567890");
				
				text.append("ご注文内容詳細");
				
				text.append("[受注番号]" + order.getOrderid() + "\n");
				text.append("[注文日時]" + order.getOrder_date() + "\n");
				text.append("[ご注文者]" + order.getName() + "\n\n");
				
				text.append("[商品名]" + item.getItemName() + "\n");
				text.append("[注文個数]" + order.getQuantity() + "\n");
				text.append("[合計]" + item.getPrice()*order.getQuantity() + "円\n\n");
				
				
			}

			// 送信元メールアドレスと送信者名を指定
			mimeMessage.setFrom(new InternetAddress("test.sender@kanda-it-school-system.com", "神田IT School", "iso-2022-jp"));

			// 送信先メールアドレスを指定（ご自分のメールアドレスに変更）
			mimeMessage.setRecipients(Message.RecipientType.TO, order.getEmail());

			// メールのタイトルを指定
			mimeMessage.setSubject(title, "iso-2022-jp");

			// メールの内容を指定
			mimeMessage.setText(text.toString(), "iso-2022-jp");

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
