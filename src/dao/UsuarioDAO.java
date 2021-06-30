package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import dto.UsuarioDTO;

public class UsuarioDAO implements IDAO {

	public UsuarioDTO checkLogin(UsuarioDTO usuariodto) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE login =? AND senha =?");
			stmt.setString(1, usuariodto.getLogin());
			stmt.setString(2, usuariodto.getSenha());

			rs = stmt.executeQuery();
			if (rs.next()) {
				usuariodto.setIdUsuario(rs.getInt("idusuario"));
				usuariodto.setNome(rs.getString("nome"));
				usuariodto.setSobrenome(rs.getString("sobrenome"));
				usuariodto.setEmail(rs.getString("email"));
				usuariodto.setEmpresa(new EmpresaDAO().read(rs.getInt("empresa_idempresa")).getNomeFantasia());
				usuariodto.setAcesso(rs.getBoolean("acesso"));
			} else {
				throw new SQLException("Login ou senha incorretos.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,  e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}

		return usuariodto;
	}

	@Override
	public void create() {

	}

	// Jo�o
	@Override
	public UsuarioDTO read(int id) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		UsuarioDTO usuariodto = new UsuarioDTO();

		try {
			stmt = conn.prepareStatement("SELECT * FROM usuario WHERE id=?");
			stmt.setInt(1, id);

			rs = stmt.executeQuery();
			if (rs.next()) {
				usuariodto.setIdUsuario(rs.getInt("idusuario"));
				usuariodto.setNome(rs.getString("nome"));
				usuariodto.setSobrenome(rs.getString("sobrenome"));
				usuariodto.setEmail(rs.getString("email"));
				usuariodto.setEmpresa(new EmpresaDAO().read(rs.getInt("empresa_idempresa")).getNomeFantasia());
				usuariodto.setAcesso(rs.getBoolean("acesso"));
			} else {
				throw new SQLException("Falha ao obter dados do usu�rio.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,  e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}

		return usuariodto;
	}

	public UsuarioDTO read(String nome, String empresa) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		UsuarioDTO usuariodto = new UsuarioDTO();

		try {
			String sql = "SELECT * FROM usuarios WHERE nome =? AND empresa_idempresa =?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setInt(2, new EmpresaDAO().read(empresa).getIdEmpresa());

			rs = stmt.executeQuery();
			if (rs.next()) {
				usuariodto.setIdUsuario(rs.getInt("idusuario"));
				usuariodto.setNome(rs.getString("nome"));
				usuariodto.setSobrenome(rs.getString("sobrenome"));
				usuariodto.setEmail(rs.getString("email"));
				usuariodto.setEmpresa(new EmpresaDAO().read(rs.getInt("empresa_idempresa")).getNomeFantasia());
				usuariodto.setAcesso(rs.getBoolean("acesso"));
			}else {
				throw new SQLException("Usu�rio n�o encontrado.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,  e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}

		return usuariodto;
	}

	@Override
	public void delete(int id) {

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
