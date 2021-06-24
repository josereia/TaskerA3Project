package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;
import dto.EmpresaDTO;

public class EmpresaDAO implements IDAO{

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object read(int id) {
		// TODO Auto-generated method stub
		return null;
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
				empresadto.setId(rs.getInt("idempresa"));
				empresadto.setNomeFantasia(rs.getString("nomefantasia"));
				empresadto.setCnpj(rs.getString("cnpj"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}
		return empresadto;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
}
