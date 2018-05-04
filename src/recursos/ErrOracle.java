package recursos;

public enum ErrOracle {

	ORACLE_DUPLICATE_PK(1),
	ORACLE_DELETE_FK(2292),
	ORACLE_FAIL_FK(2291);

	private int cod_err;
	
	private ErrOracle(int cod_err){
		this.cod_err=cod_err;
	}

	public int getCod_err() {
		return cod_err;
	}

	public void setCod_err(int cod_err) {
		this.cod_err = cod_err;
	}
	
	
}
