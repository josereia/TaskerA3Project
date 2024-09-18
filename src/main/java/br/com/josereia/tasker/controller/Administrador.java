package br.com.josereia.tasker.controller;

import javax.swing.JOptionPane;

import br.com.josereia.tasker.dao.EmpresaDAO;

import br.com.josereia.tasker.dao.NcsDAO;
import br.com.josereia.tasker.dao.UsuarioDAO;
import br.com.josereia.tasker.dto.EmpresaDTO;
import br.com.josereia.tasker.dto.UsuarioDTO;

public class Administrador extends Funcionario {
	private int acesso;

	public Administrador(UsuarioDTO usuariodto) {
		super(usuariodto);
		this.acesso = usuariodto.getAcesso();
	}

	public void excluirNC(int idnc) {
		if (this.getAcesso() > 1) {
			new NcsDAO().delete(idnc);
		} else {
			JOptionPane.showMessageDialog(null, "Você não tem permissão para isso!");
		}
	}

	public void cadastrarUsuario(UsuarioDTO usuariodto) {
	
	usuariodto.setEmpresa(this.getEmpresa());
		new UsuarioDAO().create(usuariodto);
	}
	public void atualizarUsuario(UsuarioDTO usuariodto) {
		new UsuarioDAO().update(usuariodto);
	}

	public void cadastrarEmpresa(EmpresaDTO empresadto) {
		new EmpresaDAO().create(empresadto);
	}

	public void excluirFuncionario(int idFuncionario) {
		new UsuarioDAO().delete(idFuncionario);
	}

	public int getAcesso() {
		return this.acesso;
	}

	public void setAcesso(int acesso) {
		this.acesso = acesso;
	}
}
