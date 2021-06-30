package dto;

public class EmpresaDTO {
	private int idempresa;
	private String nomeFantasia;
	private String cnpj;

	public int getIdEmpresa() {
		return idempresa;
	}

	public void setIdEmpresa(int id) {
		this.idempresa = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
