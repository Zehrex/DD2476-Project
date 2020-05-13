1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/converter/CategoriaConverter.java
package br.senac.sc.meuspedidos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.senac.sc.meuspedidos.dao.CategoriaDao;
import br.senac.sc.meuspedidos.model.Categoria;

//smp q o jsf precisar converter de string pra objeto ou objeto pra string tem q usar esse @FacesConvert
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

	private CategoriaDao dao;

	public CategoriaConverter() {
		dao = new CategoriaDao();
	}

	// convertendo de string pra objeto
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		}

		return dao.buscarPorId(new Long(value));
	}

	// convertendo de objeto pra string
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value == null) {
			return null;
		}

		Categoria categoria = (Categoria) value;
		if (categoria.getId() == null) {
			return null;
		}

// ta dizendo q a categoria é representada pelo id
		return categoria.getId().toString();
	}

}
