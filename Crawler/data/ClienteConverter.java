1
https://raw.githubusercontent.com/emanoel2712/MeusPedidos/master/src/main/java/br/senac/sc/meuspedidos/converter/ClienteConverter.java
package br.senac.sc.meuspedidos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.senac.sc.meuspedidos.dao.ClienteDao;
import br.senac.sc.meuspedidos.model.Cliente;


	@FacesConverter(value = "converterCliente", forClass = Cliente.class)
	public class ClienteConverter implements Converter {

		private ClienteDao dao;

		public ClienteConverter() {
			dao = new ClienteDao();
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

			Cliente cliente = (Cliente) value;
			if (cliente.getId() == null) {
				return null;
			}

	// ta dizendo q a categoria é representada pelo id
			return cliente.getId().toString();
		}

	}


