

import microsoft.exchange.webservices.data.autodiscover.IAutodiscoverRedirectionUrl;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

public class HrMailService {
	
	public static void sendMail(byte[] fileBytes, String fileName) throws Exception {
		
		ExchangeService service = getService();
		EmailMessage msg = new EmailMessage(service);
		
		msg.setSubject("Test Subject");
		msg.setBody(MessageBody.getMessageBodyFromText("Test Body"));
		msg.getToRecipients().add("to@to.com");
		msg.getAttachments().addFileAttachment(fileName, fileBytes);
		msg.send();

	}


	private static ExchangeService getService() throws Exception {
		ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		ExchangeCredentials credentials = new WebCredentials("mail@mail.com", "pass");
		service.setCredentials(credentials);
		service.autodiscoverUrl("mail@mail.com", new RedirectionUrlCallback());
		return service;
	}
	
	static class RedirectionUrlCallback implements IAutodiscoverRedirectionUrl {
		public boolean autodiscoverRedirectionUrlValidationCallback(String redirectionUrl) {
			return redirectionUrl.toLowerCase().startsWith("https://");
		}
	}
}
