package com.trkj.xuxianwen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.xuxianwen.common.Result;
import com.trkj.xuxianwen.xuxianwen.entity.Admission;
import com.trkj.xuxianwen.xuxianwen.mapper.AdmissionMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/admission")
public class XuxianwenController {
    @Resource
    AdmissionMapper admissionMapper;
    @PostMapping("/save")
    public Result save(@RequestBody Admission admission){
        if(admission.getDonmtime()==null){
            admission.setDonmtime(new Date());
        }
              admissionMapper.insert(admission);
              return Result.success();
    }
    @GetMapping("/select")
    public Result select(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Admission> wrapper = Wrappers.<Admission>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Admission::getName,search);
        }
        Page<Admission> admissionPage = admissionMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(admissionPage);
    }
}
