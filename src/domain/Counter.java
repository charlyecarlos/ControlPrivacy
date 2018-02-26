package domain;

import exceptions.DomainException;
import util.Validator;

public class Counter {
	
	private static final String ERM_id_count = "invalid id_count";
	private static final int url_id_count_MIN = 1;
	private static final int url_id_count_MAX = 10;

	private static final String ERM_table_count = "invalid table_count";
	private static final int url_table_count_MIN = 1;
	private static final int url_table_count_MAX = 6;

	private static final String ERM_count = "invalid count";
	private static final int count_DECIMAL = 10;
	private static final int count_ESCALE = 1;
	
	private String id_count;
	private String table_count;
	private int count;
	
	public Counter(String id_count) {
		this.id_count = id_count;
	}

	public Counter(String id_count, String table_count, int count) {
		this.id_count = id_count;
		this.table_count = table_count;
		this.count = count;
	}

	public String getId_count() {
		return id_count;
	}

	public void setId_count(String id_count){
		if (Validator.length(id_count, url_id_count_MIN, url_id_count_MAX))
			this.id_count = id_count;
		else
			throw new DomainException(ERM_id_count);
	}

	public String getTable_count() {
		return table_count;
	}
	
	public void setTable_count(String table_count) {
		if (Validator.length(table_count, url_table_count_MIN, url_table_count_MAX))
			this.table_count = table_count;
		else
			throw new DomainException(ERM_table_count);
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		if (Validator.lengthDecimal(count, count_DECIMAL, count_ESCALE))
			this.count = count;
		else
			throw new DomainException(ERM_count);
	}
}
