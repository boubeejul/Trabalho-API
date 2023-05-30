package com.trabalhoFinal.apiEcommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.trabalhoFinal.apiEcommerce.dto.PedidoEmailDTO;
import com.trabalhoFinal.apiEcommerce.dto.ProdutoEmailDTO;
import com.trabalhoFinal.apiEcommerce.repositories.ProdutoRepository;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
    public JavaMailSender emailSender;
	
	@Autowired
    public ProdutoRepository produtoRepository;

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private String mailPort;

    @Value("${spring.mail.username}")
    private String mailName;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${mail.from}")
    private String mailFrom;

    public EmailService(JavaMailSender javaMailSender) {
        this.emailSender = javaMailSender;
    }

    public void enviarEmail (String destinatario, String assunto, PedidoEmailDTO pedido) {
    	MimeMessage message = emailSender.createMimeMessage();
    	
    	try {
	    	message.setRecipients(MimeMessage.RecipientType.TO, destinatario);
	    	message.setSubject(assunto);
	    	message.setFrom(mailFrom);

	    	String htmlContent = "<html style=\"font-family: sans-serif;\">\r\n"
	    			+ "  <div>\r\n"
	    			+ "    <div style=\"background-color: #181818;display: flex;color: #fff;width: 650px;gap: 20px;padding: 20px;justify-content: space-between;\">\r\n"
	    			+ "      <div>\r\n"
	    			+ "        <span style=\"font-weight: bold;\">DATA DO PEDIDO</span>\r\n"
	    			+ "        <br>\r\n"
	    			+ "        <span>" + pedido.getData_pedido() + "</span>\r\n"
	    			+ "      </div>\r\n"
	    			+ "\r\n"
	    			+ "      <div>\r\n"
	    			+ "        <span style=\"font-weight: bold;\">TOTAL</span>\r\n"
	    			+ "        <br>\r\n"
	    			+ "        <span>R$ " + pedido.getValor_total()+ "</span>\r\n"
	    			+ "      </div>\r\n"
	    			+ "\r\n"
	    			+ "      <div>\r\n"
	    			+ "        <span style=\"font-weight: bold;\">CLIENTE</span>\r\n"
	    			+ "        <br>\r\n"
	    			+ "        <span>" + pedido.getId_cliente()+ "</span>\r\n"
	    			+ "      </div>\r\n"
	    			+ "\r\n"
	    			+ "      <div>\r\n"
	    			+ "        <span style=\"font-weight: bold;\">Nº DO PEDIDO</span>\r\n"
	    			+ "        <br>\r\n"
	    			+ "        <span>" + pedido.getId_pedido() +"</span>\r\n"
	    			+ "      </div>\r\n"
	    			+ "\r\n"
	    			+ "      <div>\r\n"
	    			+ "        <span style=\"font-weight: bold;\">STATUS</span>\r\n"
	    			+ "        <br>\r\n"
	    			+ "        <span>"+ pedido.getStatus() +"</span>\r\n"
	    			+ "      </div>\r\n"
	    			+ "    </div>\r\n"
	    			+ "  </div>"
	    			+ "<div style=\"width: 668px;padding-left: 20px;padding-top: 20px;padding-bottom: 10px;border: 1px solid #181818;\">\r\n";
	    	
	    	for(ProdutoEmailDTO produto: pedido.getProdutos()) {
	    		
	    		htmlContent += "    <div style=\"display: flex; gap: 20px; padding-bottom: 20px;\">\r\n"
	    				+ " 	<img src=\" " + produto.getUrl_imagem() + "\" style=\"width: 150px\"/>"
	    				+ "      <div style=\"display: flex; justify-content: center; flex-direction: column;\">\r\n"
	    				+ "        <div style=\"font-weight: bold;\">" + produto.getNome() +"</div>\r\n"
	    				+ "        <div style=\"font-size: 13px\">Código do produto: " + produto.getId_produto() + "</div>\r\n"
	    				+ "        <div style=\"font-size: 13px; padding-top: 10px\">Preço: R$ 200.50</div>\r\n"
	    				+ "        <div style=\"font-size: 13px\">Quantidade: " + produto.getQuantidade() + "</div>\r\n"
	    				+ "        <div style=\"font-size: 13px\">Valor bruto: R$ " + produto.getValor_bruto() + "</div>\r\n"
	    				+ "        <div style=\"font-size: 13px\">Percentual de desconto: "+ produto.getPercentual_desconto() +"%</div>\r\n"
	    				+ "        <div style=\"font-size: 13px\">Valor líquido: R$ "+ produto.getValor_liquido() +"</div>\r\n"
	    				+ "      </div>\r\n"
	    				+ "    </div>";
	    	}
	    	
	    		htmlContent += "</div></html>";
	    			
	    	message.setContent(htmlContent, "text/html; charset=utf-8");
	    			
            emailSender.send(message);
	    	
        } catch(Exception ex) {
            System.out.println("Ocorreu um erro ao tentar enviar o e-mail: " + ex.getMessage());
        }
    }

}

