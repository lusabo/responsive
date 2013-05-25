package persistence;


public interface GenericDAO<T> {

	public void insert(T object) throws Exception;

//	public void Update(T objeto) throws Exception;
//
//	public void Delete(T objeto) throws Exception;
//
//	public T FindById(int id) throws Exception;
//
//	public List<T> FindAll() throws Exception;

}
