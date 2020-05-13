2
https://raw.githubusercontent.com/marcoseduardoss/mini-mvc/master/mini-mvc/src/main/java/br/me/mvc/controle/acoes/Action.java
package br.me.mvc.controle.acoes;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.me.mvc.excecoes.ActionMvcException;
import br.me.mvc.util.JavaReflectionUtil;

/**
 *
 * @author marcos.eduardo
 */
public abstract class Action implements Serializable{

    public enum TipoDeRedirecionamento{REDIRECT, DISPATCHER}
    
    private TipoDeRedirecionamento tipoDeRedirecionamento = TipoDeRedirecionamento.DISPATCHER;
    /**
     * Ação que executa o método da classe que herda de Action
     * @param request
     * @param response
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException 
     */
    public String executa(HttpServletRequest request, 
    HttpServletResponse response) throws ActionMvcException, IllegalArgumentException, IllegalAccessException, SecurityException, InvocationTargetException {
        
        //parametro identificador da operacao que serah executada 				
        String metodoExecutavel = request.getParameter("operacao");

        if (metodoExecutavel == null)
            throw new ActionMvcException("Operação não localizada - Verifique (no 'link' ou 'action do formulario' html) se o nome do método foi digitado corretamente.");
        else
            return executarMetodo(metodoExecutavel, request, response); 
    }
    
    /**
     * executa via "reflexao java" o metodo solicitado
     * @param operacao
     * @param retorno
     * @param request
     * @param response
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws InvocationTargetException 
     */
    private String executarMetodo(String operacao, HttpServletRequest request, HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, SecurityException, InvocationTargetException, ActionMvcException {
        
    	String retorno = null;
        
        //executa via "reflexao java" o metodo solicitado
        Method[] lista = this.getClass().getDeclaredMethods();
        for (int i = 0; i < lista.length; i++) {
           Method metodo = lista[i];            
           
           //seleciona o método solicitada na url 
           if (metodo!=null && metodo.getName().equals(operacao)){
                //executa o metodo solicitado na URL
                retorno = (String)metodo.invoke(this, request, response);
           }
        }
        if (retorno == null)
            throw new ActionMvcException("Operação não localizada - Verifique (no 'link' ou 'action do formulario' html) se o nome do método foi digitado corretamente.");        
        
        return retorno;
    }
    
    /**
	 * @param className
	 * @param realPath
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	public static Action newInstance(String className, String realPath)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		//System.out.println(dirToList);
        
		Map<String, String> map = JavaReflectionUtil.INSTANCE.getClassesMap(realPath);
        
        String classAndPackage = map.get(className);
	   
	    Class classeExecutavel = Class.forName(classAndPackage);
	   
        if(classeExecutavel.getSuperclass().getName().endsWith(".Action")) { 
        	return (Action) classeExecutavel.newInstance();
        }else {
        	return null;
        }
	}

    public TipoDeRedirecionamento getTipoDeRedirecionamento() {
        return tipoDeRedirecionamento;
    }

    public void setTipoDeRedirecionamento(TipoDeRedirecionamento tipoDeRedirecionamento) {
        this.tipoDeRedirecionamento = tipoDeRedirecionamento;
    }
    
    public boolean isRedirect() {
        return tipoDeRedirecionamento.equals(TipoDeRedirecionamento.REDIRECT);
    }

    public boolean isDispatcher() {
        return tipoDeRedirecionamento.equals(TipoDeRedirecionamento.DISPATCHER);
    }
    
    
}