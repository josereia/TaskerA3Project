package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;

public class NcsDAO implements IDAO {

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultSet read(int idempresa) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		//NcsDTO ncsdao = new NcsDTO();
		//List<NcsDTO> ncsList = new ArrayList<NcsDTO>();

		try {
			stmt = conn.prepareStatement(
					"SELECT nc.idnc as `ID`, nc.titulo as `Título`, nc.descricao `Descrição`, responsavel.nome as `Responsavel`, nc.prazo as `Prazo`, nc.dataCadastro as `Dada de Cadastro`, usuario.nome as `Usuário`, ncStatus.status as `Status` FROM ncs AS nc inner join usuarios as `responsavel` on nc.responsavel_idusuario = responsavel.idusuario inner join usuarios as usuario on nc.usuario_idusuario = usuario.idusuario inner join empresas as empresa on usuario.empresa_idempresa = empresa.idempresa inner join ncstatus as ncStatus on nc.ncStatus = ncStatus.idncStatus where empresa.idempresa = ?");
			stmt.setInt(1, idempresa);

			rs = stmt.executeQuery();
			/*while (rs.next()) {
				ncsdao.setId(rs.getInt("idnc"));
				ncsdao.setTitulo(rs.getString("titulo"));
				ncsdao.setDescricao(rs.getString("descricao"));
				ncsdao.setResponsavel(rs.getString("responsavel"));
				ncsdao.setPrazo(rs.getString("prazo"));
				ncsdao.setDataCadastro(rs.getString("dataCadastro"));
				ncsdao.setUsuario(rs.getString("usuario"));
				ncsdao.setStatus(rs.getString("status"));
				
				ncsList.add(ncsdao);
			}*/
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
