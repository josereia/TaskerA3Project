package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import dto.EmpresaDTO;

public class EmpresaDAO {

	public void create(EmpresaDTO empresadto) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(
					"INSERT INTO empresas(nomeFantasia, cnpj) VALUES(?,?)");

			stmt.setString(1, empresadto.getNomeFantasia());
			stmt.setString(2, empresadto.getCnpj());

			if (stmt.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso!");
			} else {
				throw new SQLException("Empresa não cadastrado.");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			
		} finally {
			ConnectionFactory.closeConnection(conn, stmt);
		}
	}

	public EmpresaDTO read(int id) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		EmpresaDTO empresadto = new EmpresaDTO();

		try {
			stmt = conn.prepareStatement("SELECT * FROM empresas WHERE idempresa=?");
			stmt.setInt(1, id);

			rs = stmt.executeQuery();
			if (rs.next()) {
				empresadto.setIdEmpresa(rs.getInt("idempresa"));
				empresadto.setNomeFantasia(rs.getString("nomefantasia"));
				empresadto.setCnpj(rs.getString("cnpj"));
			}else {
				throw new SQLException("Falha ao encontrar empresa.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}
		
		return empresadto;
	}

	public EmpresaDTO read(String nomeFantasia) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		EmpresaDTO empresadto = new EmpresaDTO();

		try {
			stmt = conn.prepareStatement("SELECT * FROM empresas WHERE nomeFantasia=?");
			stmt.setString(1, nomeFantasia);

			rs = stmt.executeQuery();
			if (rs.next()) {
				empresadto.setIdEmpresa(rs.getInt("idempresa"));
				empresadto.setNomeFantasia(rs.getString("nomefantasia"));
				empresadto.setCnpj(rs.getString("cnpj"));
			}else {
				throw new SQLException("Falha ao encontrar empresa.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}

		return empresadto;
	}

	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
