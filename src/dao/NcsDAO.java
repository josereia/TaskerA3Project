package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;
import dto.UsuarioDTO;

public class NcsDAO implements IDAO {

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public NcsDAO read(int id) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		NcsDAO ncsdao = new NcsDAO();

		try {
			stmt = conn.prepareStatement("SELECT * FROM usuario WHERE id=?");

			rs = stmt.executeQuery();
			if (rs.next()) {
				/*ncsdao.setId(rs.getInt("idusuario"));
				ncsdao.setNome(rs.getString("nome"));
				ncsdao.setSobrenome(rs.getString("sobrenome"));
				ncsdao.setEmail(rs.getString("email"));
				ncsdao.setEmpresa(rs.getInt("empresa_idempresa"));
				ncsdao.setAcesso(rs.getBoolean("acesso"));*/
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
		return null;
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
