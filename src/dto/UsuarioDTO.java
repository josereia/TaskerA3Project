package dto;

public class UsuarioDTO {
	private int idusuario;
	private String nome;
	private String sobrenome;
	private String email;
	private String empresa;
	private boolean acesso;
	private String login;
	private String senha;

	public UsuarioDTO() {

	}

	public UsuarioDTO(int id, String nome, String sobrenome, String email, String empresa, boolean acesso, String login,
			String senha) {
		this.idusuario = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.empresa = empresa;
		this.acesso = acesso;
		this.login = login;
		this.senha = senha;
	}

	public int getIdUsuario() {
		return idusuario;
	}

	public void setIdUsuario(int id) {
		this.idusuario = id;
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

	public boolean isAcesso() {
		return acesso;
	}

	public void setAcesso(boolean acesso) {
		this.acesso = acesso;
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
