package daos.interfaces;

import java.util.List;

import domain.Statistics_index;

public interface Statistics_indexDAO {

	public List<Statistics_index> readStatisticsUser();

	public List<Statistics_index> readStatistics(String module);
}
