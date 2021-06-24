package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import dto.NcsDTO;

public class NcsDAO implements IDAO {

	// Lucas
	
	public void create(NcsDTO ncsdto) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		UsuarioDAO usuariodao = new UsuarioDAO();
		try {
			stmt = conn.prepareStatement(
					"INSERT INTO ncs(titulo, descricao, responsavel_idusuario, prazo, dataCadastro, usuario_idusuario, ncStatus) VALUES(?,?,?,?,?,?,?)");
			stmt.setString(1, ncsdto.getTitulo());
			stmt.setString(2, ncsdto.getDescricao());
			stmt.setInt(3, usuariodao.read(ncsdto.getResponsavel()).getId());
			Date date = Date.valueOf(ncsdto.getPrazo());
			stmt.setDate(4, date);
			stmt.setDate(5, new Date(new java.util.Date().getTime()));
			stmt.setInt(6, ncsdto.getId());
			stmt.setInt(7, 4);
			if(stmt.executeUpdate() > 0){
				JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso");
			}else {
				JOptionPane.showMessageDialog(null, "Dados inseridos sem sucesso");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt);
		}
	}

	// João
	@Override
	public ResultSet read(int idempresa) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(
					"SELECT nc.idnc as `ID`, nc.titulo as `Título`, nc.descricao `Descrição`, responsavel.nome as `Responsavel`, nc.prazo as `Prazo`, nc.dataCadastro as `Dada de Cadastro`, usuario.nome as `Usuário`, ncStatus.status as `Status` FROM ncs AS nc inner join usuarios as `responsavel` on nc.responsavel_idusuario = responsavel.idusuario inner join usuarios as usuario on nc.usuario_idusuario = usuario.idusuario inner join empresas as empresa on usuario.empresa_idempresa = empresa.idempresa inner join ncstatus as ncStatus on nc.ncStatus = ncStatus.idncStatus where empresa.idempresa = ?");
			stmt.setInt(1, idempresa);

			rs = stmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// ConnectionFactory.closeConnection(conn, stmt, rs);
		}
		return rs;
	}

	// Gabriel
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public boolean update(NcsDTO ncsdto) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		UsuarioDAO usuariodao = new UsuarioDAO();

		try {
			String sql = "UPDATE ncs SET `titulo`=?, `descricao`=?, `responsavel_idusuario`=?, `prazo`=?, `usuario_idusuario`=?, `ncStatus`=? WHERE idnc = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ncsdto.getTitulo());
			stmt.setString(2, ncsdto.getDescricao());
			stmt.setInt(3, usuariodao.read(ncsdto.getResponsavel()).getId());

			Date date = Date.valueOf(ncsdto.getPrazo());
			stmt.setDate(4, date);
			
			stmt.setInt(5, usuariodao.read(ncsdto.getUsuario()).getId());
			stmt.setInt(6, 2);
			stmt.setInt(7, ncsdto.getId());

			if (stmt.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt);
		}

		return false;
	}

	@Override
	public void delete(int idnc) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "DELETE FROM ncs WHERE idnc=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idnc);
			if (stmt.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "Dados excluídos");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
			;
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}
}
