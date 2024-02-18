package br.com.tacuem.componete;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import br.com.tacuem.model.Usuario;
import br.com.tacuem.servicos.TokenServicos;
import br.com.tacuem.utilidades.Eventos;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
public class EventosListener implements ApplicationListener<Eventos> {

	@Autowired
	private TokenServicos tokenServicos;

	@Autowired
	private JavaMailSender mailSender;
	private Usuario usuario;

	@Override
	public void onApplicationEvent(Eventos event) {
		usuario = event.getUsuario();
		String vToken = UUID.randomUUID().toString();
		tokenServicos.salvarTokenForUsuario(usuario, vToken);
		String url = event.getConfirmaUrl() + "/email?token=" + vToken;
		try {
			sendVerificationEmail(url);
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
		String subject = "Confirmação de cadastro";
		String senderName = "Tacaém - Cadastro de usuários";
		String mailContent = "<p> Olá, " + usuario.getNome() + ", </p>" + "  Comunicamos que seu cadastro na classe de Obreiros da EBD na Igreja Evangélica Assembleia de Deus, congregação Matriz em Tracunhaem - PE, foi realizado com sucesso. "
				+ "<p> Meditação: 2Tm 2:15 - Procura apresentar-te a Deus aprovado, como obreiro que não tem de que se envergonhar, que bmaneja bem a palavra da verdade.</p>";
		emailMessage(subject, senderName, mailContent, mailSender, usuario);
	}

	private static void emailMessage(String subject, String senderName, String mailContent, JavaMailSender mailSender,
			Usuario emailUsuario) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		var messageHelper = new MimeMessageHelper(message);
		messageHelper.setFrom("petacuem@gmail.com", senderName);
		messageHelper.setTo(emailUsuario.getEmail());
		messageHelper.setSubject(subject);
		messageHelper.setText(mailContent, true);
		mailSender.send(message);
	}

}
