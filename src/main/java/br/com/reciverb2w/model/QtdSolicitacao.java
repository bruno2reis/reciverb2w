package br.com.reciverb2w.model;

public class QtdSolicitacao {

	private Integer qtdSuccess;
	private Integer qtdStatus400;
	private Integer qtdStatus500;
	private String dataAtual;
	
	 public QtdSolicitacao(String dataAtual, Integer qtdSuccess, Integer qtdStatus400, Integer qtdStatus500) {
	        this.qtdStatus400 = qtdStatus400;
	        this.qtdStatus500 = qtdStatus500;
	        this.qtdSuccess = qtdSuccess;
	        this.dataAtual = dataAtual;
	    }

	public Integer getQtdSuccess() {
		return qtdSuccess;
	}

	public void setQtdSuccess(Integer qtdSuccess) {
		this.qtdSuccess = qtdSuccess;
	}

	public Integer getQtdStatus400() {
		return qtdStatus400;
	}

	public void setQtdStatus400(Integer qtdStatus400) {
		this.qtdStatus400 = qtdStatus400;
	}

	public Integer getQtdStatus500() {
		return qtdStatus500;
	}

	public void setQtdStatus500(Integer qtdStatus500) {
		this.qtdStatus500 = qtdStatus500;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}
	 
	 
}
