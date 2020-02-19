package com.ruoyi.project.elective.record.controller;

import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.elective.record.domain.ElectiveSelectStatistic;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.elective.record.domain.ElectiveSelectRecord;
import com.ruoyi.project.elective.record.service.IElectiveSelectRecordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * selectController
 *
 * @author Sunss
 * @date 2020-02-11
 */
@RestController
@RequestMapping("/elective/select")
public class ElectiveSelectRecordController extends BaseController {
    @Autowired
    private IElectiveSelectRecordService electiveSelectRecordService;

    /**
     * 查询选课记录列表
     */
    @PreAuthorize("@ss.hasPermi('elective:select:list')")
    @GetMapping("/list")
    public TableDataInfo list(ElectiveSelectRecord electiveSelectRecord) {
        startPage();
        List<ElectiveSelectRecord> list = electiveSelectRecordService.selectElectiveSelectRecordList(electiveSelectRecord);
        return getDataTable(list);
    }

    /**
     * 查询学生可选课程
     */
    @PreAuthorize("@ss.hasPermi('elective:select:list')")
    @GetMapping("/student")
    public TableDataInfo listCanSelect(ElectiveSelectRecord electiveSelectRecord) {
        startPage();
        List<ElectiveSelectRecord> list = electiveSelectRecordService.listCanSelect(electiveSelectRecord);
        return getDataTable(list);
    }

    /**
     * 统计选课结果
     */
    @PreAuthorize("@ss.hasPermi('elective:select:list')")
    @GetMapping("/statistic")
    public TableDataInfo statistic(ElectiveSelectStatistic electiveSelectStatistic) {
        startPage();
        List<ElectiveSelectStatistic> list = electiveSelectRecordService.listStatistic(electiveSelectStatistic);
        return getDataTable(list);
    }

    /**
     * 导出选课记录列表
     */
    @PreAuthorize("@ss.hasPermi('elective:select:export')")
    @Log(title = "选课记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ElectiveSelectRecord electiveSelectRecord) {
        List<ElectiveSelectRecord> list = electiveSelectRecordService.selectElectiveSelectRecordList(electiveSelectRecord);
        ExcelUtil<ElectiveSelectRecord> util = new ExcelUtil<ElectiveSelectRecord>(ElectiveSelectRecord.class);
        return util.exportExcel(list, "select");
    }

    /**
     * 获取选课记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('elective:select:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(electiveSelectRecordService.selectElectiveSelectRecordById(id));
    }

    /**
     * 新增选课记录
     */
    @PreAuthorize("@ss.hasPermi('elective:select:add')")
    @Log(title = "选课记录", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult add(@RequestBody ElectiveSelectRecord electiveSelectRecord) {
        // 检查是否已经选择过课程
        Long studentId = electiveSelectRecord.getStudentId();
        if (studentId == null) studentId = SecurityUtils.getStudentId();
        electiveSelectRecord.setStudentId(studentId);
        ElectiveSelectRecord query = new ElectiveSelectRecord();
        query.setStudentId(studentId);
        query.setOpenId(electiveSelectRecord.getOpenId());
        List<ElectiveSelectRecord> list = electiveSelectRecordService.selectElectiveSelectRecordList(query);
        if (list == null || list.size() == 0) {
            // 检查课程是否有余量
            list = electiveSelectRecordService.listCanSelect(electiveSelectRecord);
            if (list == null || list.size() == 0) {
                throw new CustomException("抱歉，无可选余量");
            }
            return toAjax(electiveSelectRecordService.insertElectiveSelectRecord(electiveSelectRecord));
        } else {
            throw new CustomException("抱歉，您已经选课");
        }
    }

    /**
     * 修改选课记录
     */
    @PreAuthorize("@ss.hasPermi('elective:select:edit')")
    @Log(title = "选课记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ElectiveSelectRecord electiveSelectRecord) {
        return toAjax(electiveSelectRecordService.updateElectiveSelectRecord(electiveSelectRecord));
    }

    /**
     * 删除select
     */
    @PreAuthorize("@ss.hasPermi('elective:select:remove')")
    @Log(title = "选课记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(electiveSelectRecordService.deleteElectiveSelectRecordByIds(ids));
    }
}
