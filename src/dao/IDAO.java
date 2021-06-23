package dao;

import dto.NcsDTO;

public interface IDAO {
	public void create();
	public Object read(int id);
	public Object update(Object obj);
	public void delete(int id);
	NcsDTO update(NcsDTO ncsdto);
	void update();
}