package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.NewsFileDTO;
import ge.economy.involve.core.api.request.AddNewsRequest;
import ge.economy.involve.core.dao.NewsDAO;
import ge.economy.involve.core.api.dto.NewsDTO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.NewsFileRecord;
import ge.economy.involve.database.database.tables.records.NewsRecord;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NewsService {
    @Autowired
    private NewsDAO newsDAO;
    @Autowired
    private FileService fileService;
    @Autowired
    private DSLContext dslContext;
    private Logger logger = Logger.getLogger(NewsService.class);

    public NewsDTO saveNews(AddNewsRequest request) {
        boolean newRecord = false;
        NewsRecord record = null;
        if (request.getId() != 0) {
            record = this.newsDAO.getNewsObjectById(request.getId());
        }
        if (record == null) {
            record = (NewsRecord) this.dslContext.newRecord(Tables.NEWS);
            newRecord = true;
        }
        record.setTypeId(Integer.valueOf(request.getTypeId()));
        record.setTitle(request.getTitle());
        record.setShortDescription(request.getShortDescription());
        record.setText(request.getText());
        record.setCreateDate(new Date());
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }
        return null;
    }

    public HashMap<String, Object> getNewsByType(int typeId, int start, int limit) {
        HashMap<String, Object> map = this.newsDAO.getNewsByType(typeId, start, limit);
        List<NewsDTO> news = NewsDTO.translateArray((List) map.get("list"));
        for (NewsDTO n : news) {
            n.setFiles(getNewsFiles(n.getId().intValue()));
        }
        map.put("list", news);
        return map;
    }

    public NewsDTO getNewsById(int newsId) {
        NewsDTO item = NewsDTO.translate(this.newsDAO.getNewsById(newsId));
        item.setFiles(getNewsFiles(item.getId().intValue()));
        return item;
    }

    public void deleteNews(int itemId) {
        List<NewsFileDTO> files = getNewsFiles(itemId);
        for (NewsFileDTO item : files) {
            deleteNewsFile(item.getId());
        }
        this.newsDAO.deleteNews(itemId);
    }

    public void addNewsFile(int itemId, int fileTypeId, MultipartFile file) {
        try {
            String fileName = this.fileService.saveFile(file, itemId + "_2");
            if ((fileName != null) && (!fileName.isEmpty())) {
                NewsFileRecord record = (NewsFileRecord) this.dslContext.newRecord(Tables.NEWS_FILE);
                record.setNewsId(Integer.valueOf(itemId));
                record.setFileTypeId(Integer.valueOf(fileTypeId));
                record.setFileName(fileName);
                record.setIsMain(Boolean.valueOf(false));
                record.store();
            }
        } catch (Exception ex) {
            this.logger.error(ex);
        }
    }

    public List<NewsFileDTO> getNewsFiles(int itemId) {
        return NewsFileDTO.translateArray(this.newsDAO.getNewsFiles(itemId));
    }

    public void deleteNewsFile(int itemId) {
        NewsFileRecord record = this.newsDAO.getNewsFileObjectById(itemId);
        if (record != null) {
            this.fileService.deleteFile(record.getFileName());
        }
        this.newsDAO.deleteNewsFile(itemId);
    }

    public void setMainFile(int itemId) {
        NewsFileRecord record = this.newsDAO.getNewsFileObjectById(itemId);
        if (record != null) {
            List<Record> files = this.newsDAO.getNewsFiles(record.getNewsId().intValue());
            for (Record s : files) {
                NewsFileRecord rec = this.newsDAO.getNewsFileObjectById(((Integer) s.getValue(Tables.NEWS_FILE.ID)).intValue());
                rec.setIsMain(Boolean.valueOf(false));
                rec.update();
            }
            record.setIsMain(Boolean.valueOf(true));
            record.update();
        }
    }
}
