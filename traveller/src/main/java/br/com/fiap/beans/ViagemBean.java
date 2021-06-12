package br.com.fiap.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.ViagemDao;
import br.com.fiap.model.Viagem;

@Named
@RequestScoped
public class ViagemBean {
	
	private Viagem viagem = new Viagem();
	
	public void save() {
		new ViagemDao().save(this.viagem);
		this.viagem = new Viagem();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Viagem cadastrada com sucesso!"));
	}
	
	public List<Viagem> getViagens(){
		return new ViagemDao().getAll();
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}
	
	
}
