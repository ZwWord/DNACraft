package gzz_lin.dna.api.tile;

import java.util.Set;

/**
 * 电线接口
 */
public interface IElectricityWire extends IElectricity{
    /**
     * 电线的获取电量方法，当电器调用时会传入一个set用于存储已经被调用过的电线，避免电线之间重复读取获取电量
     * @param need  需求的电量
     * @param wireSet   途径电线
     * @return 实际得到的电量
     */
    int useElectricity(int need,Set<IElectricityWire> wireSet);
}
