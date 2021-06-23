package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import dto.NcsDTO;

public class NcsDAO implements IDAO {

	//João S.
	
	
	//Lucas
	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	//João
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
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			//ConnectionFactory.closeConnection(conn, stmt, rs);
		}
		return rs;
	}
	// BIELZIN GAMEPLAYS XD
	@Override
	public NcsDTO update(NcsDTO ncsdto) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE ncs SET salario=? WHERE cod_cargo = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, 2000);
			stmt.setInt(2, 3);
			if (stmt.executeUpdate() > 0) {
				System.out.println("Dados alterados");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			ConnectionFactory.closeConnection(conn, stmt);
		}
		return ncsdto;
	}

	@Override
	public void delete(int idnc) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "DELETE FROM ncs WHERE idnc=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,idnc);
			if (stmt.executeUpdate()>0) {
					JOptionPane.showMessageDialog(null,"Dados excluídos");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);;
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Object update(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
