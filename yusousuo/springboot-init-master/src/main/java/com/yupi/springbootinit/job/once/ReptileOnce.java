package com.yupi.springbootinit.job.once;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yupi.springbootinit.esdao.PostEsDao;
import com.yupi.springbootinit.model.dto.post.PostEsDTO;
import com.yupi.springbootinit.model.entity.Post;
import com.yupi.springbootinit.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.Assertions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 全量同步帖子到 es
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
// todo 取消注释开启任务
//@Component
@Slf4j
public class ReptileOnce implements CommandLineRunner {

    @Resource
    private PostService postService;

    @Resource
    private PostEsDao postEsDao;

    @Override
    public void run(String... args) {
        String json = "{\"sortField\":\"createTime\",\"sortOrder\":\"descend\",\"reviewStatus\":1,\"current\":1}";;
        String url="https://www.code-nav.cn/api/post/list/page/vo";
        String result2 = HttpRequest.post(url)
                .body(json)
                .execute().body();
        Map map = JSONUtil.toBean(result2, Map.class);
        Integer code = (Integer) map.get("code");
        if (!(code.intValue() ==0)){
            return;
        }
        JSONObject data = (JSONObject) map.get("data");
        JSONArray records = (JSONArray) data.get("records");
        List<Post> postList=new ArrayList<>();
        for (Object record : records) {
            JSONObject jsonObject= (JSONObject) record;
            Post post=new Post();
            post.setTitle(jsonObject.getStr("title"));
            post.setContent(jsonObject.getStr("content"));
            JSONArray tags = (JSONArray) jsonObject.get("tags");
            List<String > list=tags.toList(String.class);
            post.setTags(JSONUtil.toJsonStr(list));
            post.setUserId(1L);
            postList.add(post);
            System.out.println(post);

        }
        System.out.println(records);
        boolean b = postService.saveBatch(postList);
        if (postList.size()>0){
            log.info("录入信息成功总条数"+postList.size());
        }else {
            log.info("录入信息错误");
        }
    }
}
