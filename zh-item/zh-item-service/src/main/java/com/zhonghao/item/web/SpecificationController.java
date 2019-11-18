package com.zhonghao.item.web;

import com.zhonghao.item.pojo.SpecGroup;
import com.zhonghao.item.pojo.SpecParam;
import com.zhonghao.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * 根据商品分类查询规格组
     */
    @GetMapping("/groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(specificationService.queryGroupByCid(cid));
    }

    /**
     * 查询规格参数
     */
    @GetMapping("/params")
    public ResponseEntity<List<SpecParam>> queryParamList(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value="searching", required = false) Boolean searching) {
        return ResponseEntity.ok(specificationService.queryParamList(gid, cid, searching));
    }
}
