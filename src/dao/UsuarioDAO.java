package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import connection.ConnectionFactory;
import dto.NcsDTO;
import dto.UsuarioDTO;
import net.proteanit.sql.DbUtils;

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
				usuariodto.setAcesso(rs.getInt("acesso"));
			} else {
				throw new SQLException("Login ou senha incorretos.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}

		return usuariodto;
	}

	public void create(UsuarioDTO usuariodto) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(
					"INSERT INTO usuarios(nome, sobrenome, email, acesso, login, senha, empresa_idempresa) VALUES(?,?,?,?,?,?,?)");

			stmt.setString(1, usuariodto.getNome());
			stmt.setString(2, usuariodto.getSobrenome());
			stmt.setString(3, usuariodto.getEmail());
			stmt.setInt(4, usuariodto.getAcesso());
			stmt.setString(5, usuariodto.getLogin());
			stmt.setString(6, usuariodto.getSenha());
			stmt.setInt(7, new EmpresaDAO().read(usuariodto.getEmpresa()).getIdEmpresa());

			if (stmt.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
			} else {
				throw new SQLException("Usuário não cadastrado.");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt);
		}
	}

	// João
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
				usuariodto.setAcesso(rs.getInt("acesso"));
			} else {
				throw new SQLException("Falha ao obter dados do usuário.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
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
				usuariodto.setAcesso(rs.getInt("acesso"));
			} else {
				throw new SQLException("Usuário não encontrado.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}

		return usuariodto;
	}
	
	public TableModel read(String empresa) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT usuario.idusuario, usuario.nome as `Nome`, usuario.sobrenome as `Sobrenome`, usuario.email as `Email`, usuario.login as `Login`, usuario.senha as `Senha`, empresa.nomeFantasia as `Empresa`, acesso.acesso as `Acesso` FROM usuarios AS usuario inner join niveisacesso as acesso on usuario.acesso = acesso.id inner join empresas as empresa on usuario.empresa_idempresa = empresa.idempresa where usuario.empresa_idempresa = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, new EmpresaDAO().read(empresa).getIdEmpresa());

			rs = stmt.executeQuery();
			return DbUtils.resultSetToTableModel(rs);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}

		return null;
	}

	public ArrayList<String> listNiveisAcesso() {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<String> niveisAcesso = new ArrayList<String>();

		try {
			String sql = "SELECT * FROM niveisAcesso WHERE 1";
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			while(rs.next()) {
				niveisAcesso.add(rs.getString("acesso"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}

		return niveisAcesso;
	}
	
	public int readNivelAcesso(String acesso) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM niveisAcesso WHERE acesso = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, acesso);

			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}
		return 0;
	}

	@Override
	public void delete(int id) {

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	public void update(UsuarioDTO usuariodto) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE usuarios SET `nome`=?, `sobrenome`=?, `email`=?, `empresa_idempresa`=?, `login`=?, `senha`=?, `acesso`=? WHERE idusuario = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuariodto.getNome());
			stmt.setString(2, usuariodto.getSobrenome());
			stmt.setString(3, usuariodto.getEmail());
			stmt.setInt(4, new EmpresaDAO().read(usuariodto.getEmpresa()).getIdEmpresa());
			stmt.setString(5, usuariodto.getLogin());
			stmt.setString(6, usuariodto.getSenha());
			stmt.setInt(7, usuariodto.getAcesso());
			stmt.setInt(8, usuariodto.getIdUsuario());

			if (stmt.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "Dados alterados!");
			} else {
				throw new SQLException("Falha ao alterar.");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn, stmt);
		}
	}
}
