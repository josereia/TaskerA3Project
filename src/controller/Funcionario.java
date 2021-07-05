package controller;

import dao.NcsDAO;
import dto.NcsDTO;
import dto.UsuarioDTO;

public class Funcionario {
	private int idusuario;
	private String nome;
	private String sobrenome;
	private String email;
	private String empresa;
	private String login;
	private String senha;

	public Funcionario(UsuarioDTO usuariodto) {
		this.idusuario = usuariodto.getIdUsuario();
		this.nome = usuariodto.getNome();
		this.sobrenome = usuariodto.getSobrenome();
		this.email = usuariodto.getEmail();
		this.empresa = usuariodto.getEmpresa();
		this.login = usuariodto.getLogin();
		this.senha = usuariodto.getSenha();
	}

	public void cadastrarNC(NcsDTO ncsdto) {
		ncsdto.setUsuario(this.getNome());
		ncsdto.setUsuarioEmpresa(this.getEmpresa());
		new NcsDAO().create(ncsdto);
	}

	public void alterarNC(NcsDTO ncsdto) {
		ncsdto.setUsuario(this.getNome());
		ncsdto.setUsuarioEmpresa(this.getEmpresa());
		new NcsDAO().create(ncsdto);
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
