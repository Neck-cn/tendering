package cn.ych.tendering.processor;

import cn.ych.tendering.mapper.EnterpriseMapper;
import cn.ych.tendering.mapper.TenderingMapper;
import cn.ych.tendering.pojo.Enterprise;
import cn.ych.tendering.pojo.Tendering;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class TenderingProcessor implements PageProcessor {
    private TenderingMapper tenderingMapper;
    private EnterpriseMapper enterpriseMapper;

    // 抓取网站的相关配置，可以包括编码、抓取间隔1s、重试次数等
    private Site site = Site.me()
            .setCharset("utf-8")
            .setRetryTimes(5)
            .setTimeOut(10000)
            .setUseGzip(true)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");

    public TenderingProcessor(TenderingMapper tenderingMapper, EnterpriseMapper enterpriseMapper) {
        this.tenderingMapper = tenderingMapper;
        this.enterpriseMapper = enterpriseMapper;
    }

    @Override
    public void process(Page page) {
        List<String> all = page.getHtml().css(".sevenday_list").all();
        if (all.size() != 0) {
            List<String> dt = page.getHtml().css(".sevenday_list").css("dt").css("a", "href").all();
            for (int i = 1; i < dt.size(); i += 2) {
                page.addTargetRequest(dt.get(i));
            }
        } else {
            List<String> all1 = page.getHtml().css("#wen").all();
            all1.forEach((s) -> {
                Tendering tendering = new Tendering();
                int e_id = (int) (Math.random() * 6) + 1;
                tendering.setE_id(e_id);
                Enterprise enterprise = enterpriseMapper.selectById(e_id);
                List<String> all2 = page.getHtml().css(".wenshang").css("h2", "text").all();
                tendering.setTitle(all2.get(0));
                tendering.setName(enterprise.getName());
                tendering.setContent(s);
                List<Date> date = getDate((int) (Math.random() * 3) + 1);
                tendering.setStart_time(date.get(0));
                tendering.setEnd_time(date.get(1));
                tendering.setSrc("http://file.erya.ychstudy.cn/group1/M00/00/06/rBAABV6ymwKAcGAZAAdgADvRkOw654.doc");
                page.putField("tendering", tendering);
            });
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    private List<Date> getDate(int param) {
        List<Date> list = new ArrayList<>();
        if (param == 1) {
            list.add(new Date(System.currentTimeMillis() + 100000000));
            list.add(new Date(System.currentTimeMillis() + 200000000));
        }
        if (param == 2) {
            list.add(new Date());
            list.add(new Date(System.currentTimeMillis() + 100000000));
        }
        if (param == 3) {
            list.add(new Date(System.currentTimeMillis() - 100000000));
            list.add(new Date());
        }
        return list;
    }

    public void start(String url, int thread, int sleep) {
        site.setSleepTime(sleep);
        Spider.create(this)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000000)))
                .addPipeline((resultItems, task) -> {
                    Tendering tendering = resultItems.get("tendering");
                    if (tendering != null) {
                        tenderingMapper.insert(tendering);
                    }
                })
                .addUrl(url)
                .thread(thread)
                .run();
    }
}
