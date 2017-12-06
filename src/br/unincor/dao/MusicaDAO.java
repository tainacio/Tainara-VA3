package br.unincor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unincor.model.Musica;

public class MusicaDAO extends GenericDAO {
	
	public Musica inserirMusica(Musica musica) {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into musica (titulo, artista, album, duracao) values (");
		sb.append("'"+musica.getTitulo()+"',");
		sb.append("'"+musica.getArtista()+"',");
		sb.append("'"+musica.getAlbum()+"', ");
		sb.append(""+musica.getDuracao()+");");
		
		try {
			super.conexaoBD.dbOpenConnection();
			
			Long id = super.executarInsert(sb.toString());
			
			super.conexaoBD.dbCloseConnection();
			
			musica.setId(id);
			return musica;
		} catch (Exception e) {
			super.usuario.exibeMsgErro(e.getMessage());
		}
		return null;
	}
	
	public Musica atualizarMusica(Musica musica) {
		StringBuilder sb = new StringBuilder();
		sb.append("update musica set "); 
		sb.append("titulo = '"+musica.getTitulo()+"', ");
		sb.append("artista = '"+musica.getArtista()+"', ");
		sb.append("album = '"+musica.getAlbum()+"', ");
		sb.append("duracao = "+musica.getDuracao()+" ");
		sb.append("where id = "+musica.getId());
		
		try {
			super.conexaoBD.dbOpenConnection();
			
			Integer linhas = super.executarUpdate(sb.toString());
			
			super.conexaoBD.dbCloseConnection();
			
			if(linhas == 1)
				return musica;
		} catch (Exception e) {
			super.usuario.exibeMsgErro(e.getMessage());
		}
		return null;
	}

	public List<Musica> listarMusica() {
		List<Musica> listaMusica = new ArrayList<Musica>();
		String strQuery = "select id, titulo, artista, album, duracao from musica order by id;";
		
		try {
			super.conexaoBD.dbOpenConnection();
			
			ResultSet rs = super.executarSelect(strQuery);
			
			while (rs.next()) {
				listaMusica.add(new Musica(rs.getLong(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getLong(5)));
			}
			
			super.conexaoBD.dbCloseConnection();
			
			return listaMusica;
		} catch (SQLException e) {
			super.usuario.exibeMsgErro(e.getMessage());
		}
		
		return null;
	}
	
}
