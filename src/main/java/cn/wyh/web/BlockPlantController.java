package cn.wyh.web;

import cn.wyh.common.Global;
import cn.wyh.common.response.ResponseSuccess;
import cn.wyh.dto.BlockPlantDto;
import cn.wyh.dto.BlockPlantSearch;
import cn.wyh.dto.TillageDto;
import cn.wyh.service.BlockPlantService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by WYH on 2018/4/15.
 */
@RestController
@RequestMapping("/plant")
public class BlockPlantController {
    @Autowired
    private BlockPlantService blockPlantService;

    @RequestMapping("/app/loadGreenList")
    public String loadGreenList() {
        return new ResponseSuccess(blockPlantService.loadGreenList()).toString();
    }

    @RequestMapping("/app/addPlantOrder")
    public String addPlantOrder(@RequestParam Integer blockId, @RequestParam Integer greenId) {
        return new ResponseSuccess(blockPlantService.addPlantOrder(blockId, greenId)).toString();
    }

    @RequestMapping("/app/currentPlantStatus")
    public String currentPlantStatus(@RequestParam Integer blockId) {
        return new ResponseSuccess(blockPlantService.currentPlantStatus(blockId)).toString();
    }

    @RequestMapping("/app/loadPlantStatusList")
    public String loadPlantStatusList(@RequestParam Integer userId, @RequestParam Integer status) {
        return new ResponseSuccess(blockPlantService.loadBlockPlantStatus(userId, status)).toString();
    }

    @RequestMapping("/web/loadPlantList")
    public String loadPlantInfoWebList(@ModelAttribute BlockPlantSearch param) {
        return new ResponseSuccess(blockPlantService.loadPlantListForWeb(param)).toString();
    }

    @RequestMapping("/web/processPlant")
    public String processPlant(@ModelAttribute BlockPlantDto dto) {
        return new ResponseSuccess(blockPlantService.processPlant(dto)).toString();
    }

    @RequestMapping("/web/plantStatus")
    public String plantStatus(@ModelAttribute BlockPlantSearch search) {
        return new ResponseSuccess(blockPlantService.loadStatusForWeb(search)).toString();
    }

    @RequestMapping("/web/uploadImgList")
    public String saveImg(@RequestParam("files") MultipartFile[] fileList) throws IOException {
        long id = new Date().getTime();
        List<String> list = new ArrayList<String>(3);
        for (MultipartFile file : fileList) {
            File baseUri = new File(Global.baseUri + "tillageImg");
            if (!baseUri.exists()) {
                baseUri.mkdir();
            }
            id++;
            file.transferTo(new File(Global.baseUri + "tillageImg" + File.separator + id + file.getOriginalFilename()));
            list.add("/tillageImg/" + id + file.getOriginalFilename());
        }
        return JSONObject.toJSONString(list);
    }

    @RequestMapping("/web/uploadVideoList")
    public String saveVideo(@RequestParam("files") MultipartFile[] fileList) throws IOException {
        long id = new Date().getTime();
        MultipartFile file = fileList[0];
        File baseUri = new File(Global.baseUri + "tillageVideo");
        if (!baseUri.exists()) {
            baseUri.mkdir();
        }
        file.transferTo(new File(Global.baseUri + "tillageVideo" + File.separator + id + file.getOriginalFilename()));
        return "/tillageVideo/" + id + file.getOriginalFilename();
    }

    @RequestMapping("/web/insertTillage")
    public String addTillage(@RequestParam String json) {
        TillageDto dto = JSONObject.parseObject(json, TillageDto.class);
        return new ResponseSuccess(blockPlantService.addTillageInfo(dto)).toString();
    }

    @RequestMapping("/app/loadRecordList")
    public String loadTillageRecord(@RequestParam Integer plantId) {
        return new ResponseSuccess(blockPlantService.loadTillageList(plantId)).toString();
    }

    @RequestMapping("/app/loadTillageImgList")
    public String loadTillageImgList(@RequestParam Integer plantId) {
        return new ResponseSuccess(blockPlantService.loadTillageImgList(plantId)).toString();
    }
}
