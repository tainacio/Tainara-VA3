package br.unincor.control;

import br.unincor.dao.GenericDAO;
import br.unincor.model.Musica;
import br.unincor.view.ViewGUI;

public class Main {
	
	public static void main(String[] args){
		GenericDAO gd = new GenericDAO();
		ViewGUI gui = new ViewGUI();
		
		int opcao = -2;
		
		while(opcao != -1){
			
			opcao = gui.exibeMenuPrincipal();
			
			if(opcao == 0){
				Musica music = new Musica(0,
					gui.recebeTexto("Entre com o nome da música"),
					gui.recebeTexto("Entre com o nome do artista"),
					gui.recebeTexto("Entre com o nome do álbum"), 
					gui.recebeLong("Entre com a duração")
				);
				
				try{
					gd.insertMusica(music);
					
					music.setId(gd.buscaUltimoId());
					
					gui.exibeMsg(music.toString());
				}catch(Exception e){
					gui.exibeMsgErro("Erro ao inserir música");;
				}
			}
			else if(opcao == 1){
				int id = gui.recebeInt("ID da música a ser alterada");
				
				if(id <= gd.buscaUltimoId()){
					try{
						Musica music = new Musica(0,
							gui.recebeTexto("Entre com o nome da música:"),
							gui.recebeTexto("Entre com o nome do artista:"),
							gui.recebeTexto("Entre com o nome do álbum:"), 
							gui.recebeLong("Entre com a duração")
						);
						
						gd.updateMusica(id, music);
						
						gui.exibeMsg(gd.buscaUltimaAlterada(id));
						
					}catch(Exception e){
						gui.exibeMsgErro("Erro ao alterar música!");
					}
				}	
				else{
					gui.exibeMsgErro("ID inserido não existe");;
				}
			}
		}
	}
}