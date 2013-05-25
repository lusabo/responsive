package business;

import javax.inject.Inject;

import persistence.CidadeDAO;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import entity.Cidade;

@BusinessController
public class CidadeBC {

	@Inject
	CidadeDAO cidadeDAO;

	public void insert(Cidade cidade) throws Exception {
		cidadeDAO.insert(cidade);
	}

	@Startup
	@Transactional
	public void load() throws Exception {
		insert(new Cidade("Salvador"));
		insert(new Cidade("Brasília"));
	}

}
