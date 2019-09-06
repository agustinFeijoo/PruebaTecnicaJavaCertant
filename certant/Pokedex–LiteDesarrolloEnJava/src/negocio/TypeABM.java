package negocio;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import dao.TypeDao;
import datos.Type;

public class TypeABM {
	TypeDao dao = TypeDao.getInstanciaTypeDao();

	public Type traerType(int idType) {
		Type c = dao.traerType(idType);
		return c;
	}
	public Type traerType(long dni) {
		Type c = dao.traerType(dni);
		return c;
	}
	



	public void eliminar(int idType) {
		Type c = dao.traerType(idType);
		dao.eliminar(c);
	}

	public List<Type> traerType() {
		return dao.traer();
	}
	public void mostrarFilas(HttpServletResponse response) throws IOException{
		TypeDao TypeDao=TypeDao.getInstanciaTypeDao(); 
		List<Type> lstTypes=TypeDao.traer();
		for(Type Type:lstTypes){
			response.getWriter().append("<tr><td id='c1f"+Type.getIdType()+"'>"+Type.getDatosPersonales().getDni()
				+"</td> <td id='c2f"+Type.getIdType()+"'>"+ Type.getDatosPersonales().getNombre()+
				"</td><td id='c3f"+Type.getIdType()+"'>"+Type.getDatosPersonales().getApellido()
				+"</td><td id='c4f"+Type.getIdType()+"'> "+ Type.getDatosPersonales().getTelefono()
				+"</td><td id='c5f"+Type.getIdType()+"'><button onclick='modificarType("+Type.getIdType()+")'id='boton"+Type.getIdType()+"'>Modificar</button><button onclick='eliminarType("+Type.getIdType()+")'>Eliminar</button></tr>");
			
	}
}
}
