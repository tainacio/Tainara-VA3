package br.unincor.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class Usuario {
	
	
	public void exibeMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void exibeMsgErro(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erro!", JOptionPane.ERROR_MESSAGE);
	}
	
	public void exibeMsgDebug(String msg) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		System.out.println(sdf.format(new Date()) + " [DEBUG] - " + msg);
	}
	
	public Integer exibeMenuPrincipal() {
		Object[] opcoes = {"Inserir", "Alterar"};
		Integer op = JOptionPane.showOptionDialog(null,
					"Escolha uma opção?",
					"Menu Principal",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE,
					null,
					opcoes, -1);

		return op;
	}
	
	public Long recebeLong(String texto) {
		try {
			return Long.parseLong(JOptionPane.showInputDialog(texto));	
		} catch (Exception e) {
			this.exibeMsgErro("Erro ao ler o Long");
		}
		return null;
	}
	
	public String recebeTexto(String texto) {
		return JOptionPane.showInputDialog(texto);
	}
}