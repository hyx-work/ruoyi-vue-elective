package com.ruoyi.project.elective.record.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.elective.record.mapper.ElectiveSelectRecordMapper;
import com.ruoyi.project.elective.record.domain.ElectiveSelectRecord;
import com.ruoyi.project.elective.record.service.IElectiveSelectRecordService;

/**
 * selectService业务层处理
 * 
 * @author Sunss
 * @date 2020-02-11
 */
@Service
public class ElectiveSelectRecordServiceImpl implements IElectiveSelectRecordService 
{
    @Autowired
    private ElectiveSelectRecordMapper electiveSelectRecordMapper;

    /**
     * 查询select
     * 
     * @param id selectID
     * @return select
     */
    @Override
    public ElectiveSelectRecord selectElectiveSelectRecordById(Long id)
    {
        return electiveSelectRecordMapper.selectElectiveSelectRecordById(id);
    }

    /**
     * 查询select列表
     * 
     * @param electiveSelectRecord select
     * @return select
     */
    @Override
    public List<ElectiveSelectRecord> selectElectiveSelectRecordList(ElectiveSelectRecord electiveSelectRecord)
    {
        return electiveSelectRecordMapper.selectElectiveSelectRecordList(electiveSelectRecord);
    }

    /**
     * 新增select
     * 
     * @param electiveSelectRecord select
     * @return 结果
     */
    @Override
    public int insertElectiveSelectRecord(ElectiveSelectRecord electiveSelectRecord)
    {
        electiveSelectRecord.setCreateTime(DateUtils.getNowDate());
        return electiveSelectRecordMapper.insertElectiveSelectRecord(electiveSelectRecord);
    }

    /**
     * 修改select
     * 
     * @param electiveSelectRecord select
     * @return 结果
     */
    @Override
    public int updateElectiveSelectRecord(ElectiveSelectRecord electiveSelectRecord)
    {
        electiveSelectRecord.setUpdateTime(DateUtils.getNowDate());
        return electiveSelectRecordMapper.updateElectiveSelectRecord(electiveSelectRecord);
    }

    /**
     * 批量删除select
     * 
     * @param ids 需要删除的selectID
     * @return 结果
     */
    @Override
    public int deleteElectiveSelectRecordByIds(Long[] ids)
    {
        return electiveSelectRecordMapper.deleteElectiveSelectRecordByIds(ids);
    }

    /**
     * 删除select信息
     * 
     * @param id selectID
     * @return 结果
     */
    @Override
    public int deleteElectiveSelectRecordById(Long id)
    {
        return electiveSelectRecordMapper.deleteElectiveSelectRecordById(id);
    }
}