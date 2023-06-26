package com.learncode.Entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailInfo {

	// Class data members
	String from;
	String to;
	String[] cc;
	String[] bcc;
	String subject;
	String body;
	String[] attachments;
	public MailInfo(String to, String subject, String body) {
		this.from = "lamhtpk02207@fpt.edu.vn";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}


}
