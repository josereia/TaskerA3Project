package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;

public class NcsDAO implements IDAO {

	//Lucas
	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	//Jo�o
	@Override
	public ResultSet read(int idempresa) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(
					"SELECT nc.idnc as `ID`, nc.titulo as `T�tulo`, nc.descricao `Descri��o`, responsavel.nome as `Responsavel`, nc.prazo as `Prazo`, nc.dataCadastro as `Dada de Cadastro`, usuario.nome as `Usu�rio`, ncStatus.status as `Status` FROM ncs AS nc inner join usuarios as `responsavel` on nc.responsavel_idusuario = responsavel.idusuario inner join usuarios as usuario on nc.usuario_idusuario = usuario.idusuario inner join empresas as empresa on usuario.empresa_idempresa = empresa.idempresa inner join ncstatus as ncStatus on nc.ncStatus = ncStatus.idncStatus where empresa.idempresa = ?");
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

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

}
