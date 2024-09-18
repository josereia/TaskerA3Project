package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import connection.ConnectionFactory;
import dto.NcsDTO;
import net.proteanit.sql.DbUtils;

public class NcsDAO {

	// Lucas
	public void create(NcsDTO ncsdto) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(
					"INSERT INTO ncs(titulo, descricao, responsavel_idusuario, prazo, dataCadastro, usuario_idusuario, empresa_idempresa, ncStatus_idncStatus) VALUES(?,?,?,?,?,?,?,?)");

			stmt.setString(1, ncsdto.getTitulo());
			stmt.setString(2, ncsdto.getDescricao());
			stmt.setInt(3, new UsuarioDAO().read(ncsdto.getResponsavel(), ncsdto.getUsuarioEmpresa()).getIdUsuario());

			Date date = Date.valueOf(ncsdto.getPrazo());
			stmt.setDate(4, date);

			stmt.setDate(5, new Date(new java.util.Date().getTime()));
			stmt.setInt(6, new UsuarioDAO().read(ncsdto.getUsuario(), ncsdto.getUsuarioEmpresa()).getIdUsuario());
			stmt.setInt(7, new EmpresaDAO().read(ncsdto.getUsuarioEmpresa()).getIdEmpresa());
			stmt.setInt(8, 4);

			if (stmt.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "NC cadastrada com sucesso!");
			} else {
				throw new SQLException("NC n�o cadastrada.");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			
		} finally {
			ConnectionFactory.closeConnection(conn, stmt);
		}
	}

	// João Sereia
	public NcsDTO read(int idnc) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		NcsDTO ncsdto = new NcsDTO();

		try {
			stmt = conn.prepareStatement(
					"SELECT nc.idncs as `Id`, nc.titulo as `T�tulo`, nc.descricao `Descri��o`, responsavel.nome as `Respons�vel`, nc.prazo as `Prazo`, nc.dataCadastro as `Dada de Cadastro`, usuario.nome as `Usu�rio`, ncStatus.status as `Status` FROM ncs AS nc inner join usuarios as `responsavel` on nc.responsavel_idusuario = responsavel.idusuario inner join usuarios as usuario on nc.usuario_idusuario = usuario.idusuario inner join empresas as empresa on nc.empresa_idempresa = empresa.idempresa inner join ncstatus as ncStatus on nc.ncStatus_idncStatus = ncStatus.idncStatus where nc.idncs = ?");
			stmt.setInt(1, idnc);

			rs = stmt.executeQuery();
			if (rs.next()) {
				ncsdto.setId(rs.getInt("Id"));
				ncsdto.setTitulo(rs.getString("T�tulo"));
				ncsdto.setDescricao(rs.getString("Descri��o"));
				ncsdto.setResponsavel(rs.getString("Respons�vel"));
				ncsdto.setPrazo(rs.getString("Prazo"));
				ncsdto.setDataCadastro(rs.getString("Dada de Cadastro"));
				ncsdto.setUsuario(rs.getString("Usu�rio"));
				ncsdto.setStatus(rs.getString("Status"));
			} else {
				throw new SQLException("Falha ao obter NC.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}

		return ncsdto;
	}

	public TableModel read(String nomeFantasia) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(
					"SELECT nc.idncs as `Id`, nc.titulo as `T�tulo`, nc.descricao `Descri��o`, responsavel.nome as `Respons�vel`, nc.prazo as `Prazo`, nc.dataCadastro as `Dada de Cadastro`, usuario.nome as `Usu�rio`, ncStatus.status as `Status` FROM ncs AS nc inner join usuarios as `responsavel` on nc.responsavel_idusuario = responsavel.idusuario inner join usuarios as usuario on nc.usuario_idusuario = usuario.idusuario inner join empresas as empresa on nc.empresa_idempresa = empresa.idempresa inner join ncstatus as ncStatus on nc.ncStatus_idncStatus = ncStatus.idncStatus where nc.empresa_idempresa = ? ORDER BY nc.prazo");
			stmt.setInt(1, new EmpresaDAO().read(nomeFantasia).getIdEmpresa());

			rs = stmt.executeQuery();

			return DbUtils.resultSetToTableModel(rs);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			
		} finally {
			ConnectionFactory.closeConnection(conn, stmt);
		}

		return null;
	}

	public void update(NcsDTO ncsdto) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		UsuarioDAO usuariodao = new UsuarioDAO();

		try {
			String sql = "UPDATE ncs SET `titulo`=?, `descricao`=?, `responsavel_idusuario`=?, `prazo`=?, `usuario_idusuario`=?, `ncStatus_idncStatus`=? WHERE idncs = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ncsdto.getTitulo());
			stmt.setString(2, ncsdto.getDescricao());
			stmt.setInt(3, usuariodao.read(ncsdto.getResponsavel(), ncsdto.getUsuarioEmpresa()).getIdUsuario());

			Date date = Date.valueOf(ncsdto.getPrazo());
			stmt.setDate(4, date);

			stmt.setInt(5, usuariodao.read(ncsdto.getUsuario(), ncsdto.getUsuarioEmpresa()).getIdUsuario());
			stmt.setInt(6, this.readNcStatus(ncsdto.getStatus()));
			stmt.setInt(7, ncsdto.getId());

			if (stmt.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "NC alterada!");
			} else {
				throw new SQLException("Falha ao alterar NC.");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			
		} finally {
			ConnectionFactory.closeConnection(conn, stmt);
		}
	}

	// Christopher
	public void delete(int idnc) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "DELETE FROM ncs WHERE idncs=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idnc);

			if (stmt.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "NC exclu�da!");
			} else {
				throw new SQLException("Falha ao exluir NC.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			
		} finally {
			ConnectionFactory.closeConnection(conn, stmt);
		}
	}

	public int readNcStatus(String status) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement("SELECT * FROM ncstatus WHERE status=?");
			stmt.setString(1, status);

			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("idncStatus");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}

		return 0;
	}
}
