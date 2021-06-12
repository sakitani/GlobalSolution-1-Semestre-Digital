package br.com.fiap.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.fiap.model.User;

public class AuthorizationListener implements PhaseListener {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		String viewId = context.getViewRoot().getViewId();
		if (viewId.equals("/form-user.xhtml")) return;
		if (viewId.equals("/login.xhtml")) return;
		
		User user = (User) context.getExternalContext().getSessionMap().get("user");
		if(user != null) return;
		
		NavigationHandler navigator = 	context.getApplication().getNavigationHandler();
		navigator.handleNavigation(context, null, "login?faces-redirect=true");
		System.out.println("AFTER - " +event.getPhaseId());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		System.out.println("BEFORE - " +event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
