package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;
import dto.UsuarioDTO;

public class UsuarioDAO implements IDAO {

	public UsuarioDTO checkLogin(UsuarioDTO usuariodto) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement("SELECT * FROM usuario WHERE login =? AND senha =?");
			stmt.setString(1, usuariodto.getLogin());
			stmt.setString(2, usuariodto.getSenha());

			rs = stmt.executeQuery();
			if (rs.next()) {
				usuariodto.setId(rs.getInt("idusuario"));
				usuariodto.setNome(rs.getString("nome"));
				usuariodto.setSobrenome(rs.getString("sobrenome"));
				usuariodto.setEmail(rs.getString("email"));
				usuariodto.setEmpresa(rs.getInt("empresa_idempresa"));
				usuariodto.setAcesso(rs.getBoolean("acesso"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}
		return usuariodto;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

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
				usuariodto.setId(rs.getInt("idusuario"));
				usuariodto.setNome(rs.getString("nome"));
				usuariodto.setSobrenome(rs.getString("sobrenome"));
				usuariodto.setEmail(rs.getString("email"));
				usuariodto.setEmpresa(rs.getInt("empresa_idempresa"));
				usuariodto.setAcesso(rs.getBoolean("acesso"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}
		return usuariodto;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

}
