package gzz_lin.dna.api.tile;

/**
 * 电源接口
 */
public interface IElectricityStorager extends IElectricity{
	//使用电量
	/**
	 * 获取电能
	 * @param need 需要的电量
	 * @return 实际得到的电量
	 */
	int useElectricity(int need);
	void setElectricity(int e);
}
