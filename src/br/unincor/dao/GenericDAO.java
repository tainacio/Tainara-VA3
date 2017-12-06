package br.unincor.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.unincor.connection.DBConnection;
import br.unincor.model.Musica;

public class GenericDAO {

	private DBConnection dbConnection = new DBConnection();
	private PreparedStatement pStatement;

	
	public void updateMusica(int id, Musica m) {
		String sql = "UPDATE musica SET titulo = '" + m.getTitulo() +
				"', artista='" + m.getArtista() +
				"', album='" + m.getAlbum() + 
				"', duracao=" + m.getDuracao() + 
				" WHERE id = " + id + ";";
		try {
			dbConnection.openDBConnection();
			PreparedStatement pStatement;
			pStatement = dbConnection.CON.prepareStatement(sql);
			
			pStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertMusica(Musica m){
		String sql = "INSERT INTO musica(titulo, artista, album, duracao) VALUES("
				+ "'" + m.getTitulo() + "', "
				+ "'" + m.getArtista() + "', "
				+ "'" + m.getAlbum() + "', "
				+ m.getDuracao() + ")";
		
		PreparedStatement pStatement;
		
		try {
			dbConnection.openDBConnection();
			pStatement = dbConnection.CON.prepareStatement(sql);
			pStatement.executeUpdate();
			
			dbConnection.closeDBConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int buscaUltimoId(){
		int id = 0;
		
		try{
			dbConnection.openDBConnection();
			pStatement = dbConnection.CON.prepareStatement("SELECT id FROM musica ORDER BY id DESC");
			ResultSet rs = pStatement.executeQuery();
			
			while(rs.next()){
				id = rs.getInt("id");
				break;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return id;
	}
	
	public String buscaUltimaAlterada(int id){
		String dados = "";
		
		try{
			dbConnection.openDBConnection();
			pStatement = dbConnection.CON.prepareStatement("SELECT * FROM musica WHERE id = " + id);
			ResultSet rs = pStatement.executeQuery();
			
			while(rs.next()){
				dados = rs.getInt("id") + ";" + 
						rs.getString("titulo") + ";" +
						rs.getString("artista") + ";" +
						rs.getString("album") + ";" +
						rs.getLong("duracao") + "\n";
				break;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return dados;
	}
	
}
