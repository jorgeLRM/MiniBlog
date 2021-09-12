package com.mitocode.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;

@Named
@ViewScoped
public class IndexBean implements Serializable {
	
	private Usuario us;
	@Inject
	private IUsuarioService service;
	
	@PostConstruct
	public void init() {
		us = new Usuario();
	}

	public String login() {
		String redireccion = "";
		
		try {
			Usuario usuario = service.login(us);
			if (usuario != null && usuario.getEstado().equalsIgnoreCase("A")) {
				// Almacenar en la sesi�n de JSF y proseguir con la navegaci�n
				// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Correcto."));
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
				redireccion = "/protegido/roles?faces-redirect=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales incorrectas."));
			}
		} catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", e.getMessage()));
		}
		return redireccion;
	}
	
	public Usuario getUs() {
		return us;
	}

	public void setUs(Usuario us) {
		this.us = us;
	}
	
	
}
