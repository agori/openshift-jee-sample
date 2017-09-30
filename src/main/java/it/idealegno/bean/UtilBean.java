package it.idealegno.bean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.ocpsoft.pretty.PrettyContext;

@ApplicationScoped
@Named
public class UtilBean
{

	@Named
	@Produces
	public String getCpath()
	{
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}

	public String getCurrentMappingId() {
		return PrettyContext.getCurrentInstance().getCurrentMapping().getId();
	}

}
