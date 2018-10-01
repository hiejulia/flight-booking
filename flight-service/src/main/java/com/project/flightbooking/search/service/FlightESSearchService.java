
import com.tangzq.model.Book;
import com.tangzq.repository.BookESRepository;
import com.tangzq.service.BookESService;
import com.tangzq.util.CommonUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * 
 * Flight search with ElasticSearch index 
 */
@Service
public class FlightESSearchService  {

    private static final Logger logger = LoggerFactory.getLogger(FlightESSearchService.class);

    @Autowired
    private FlightESRepository FlightESRepository;

    // 
    public void resyncBooksToEs(List<Book> bookList) {
        logger.info("开始同步书籍索引到ES,总共{}本", bookList.size());
        for(Book book:bookList){
            //先刪除已有索引
            if(null!=findOne(book.getBookNo())){
                this.deleteByBookNo(book.getBookNo());
                logger.info("该本书{}({})的索引已经存在,将删除历史索引",book.getBookNo(),book.getBookName());
            }
            //再同步
            this.syncOne(book);
            logger.info("书{}({})的索引创建成功",book.getBookNo(),book.getBookName());
        }
        logger.info("同步书籍到ES完成");

    }

    // Sync one flight
    // save one flight to ES database - after save to main database
    public void syncOne(Fligth b) {
        bookESRepository.save(b);
    }

    // find one 
    public Book findOne(Long bookNo) {
        return bookESRepository.findOne(bookNo);
    }

    public Optional<Flight>


    @Override
    public Iterable<Book> findAll() {
        return bookESRepository.findAll();
    }

    @Override
    public void deleteByBookNo(Long bookNo) {
        bookESRepository.delete(bookNo);
    }

    @Override
    public void deleteAll() {
        bookESRepository.deleteAll();
    }

    @Override
    public Page<Book> findAllByPage(int pageSize) {
        PageRequest pageable = new PageRequest(0, pageSize);
        return bookESRepository.findAll(pageable);
    }

    @Override
    public Page<Book> search(String nameOrNumberOrISBN) {
        PageRequest pageable = new PageRequest(0, 10);
        BoolQueryBuilder query=buildBookSearchQuery(Optional.ofNullable(nameOrNumberOrISBN));
        return bookESRepository.search(query,pageable);
    }

    protected BoolQueryBuilder buildBookSearchQuery(Optional<String> nameOrNumberOrISBN) {
        BoolQueryBuilder query = null;
        if (nameOrNumberOrISBN.isPresent()) {
            BoolQueryBuilder search = QueryBuilders.boolQuery();
            if (CommonUtil.isLong(nameOrNumberOrISBN.get())) {
                search.should(QueryBuilders.termQuery("bookNo", Long.valueOf(nameOrNumberOrISBN.get())));
            }
            search.should(QueryBuilders.termQuery("bookIsbn", nameOrNumberOrISBN.get()));
            search.should(QueryBuilders.matchQuery("bookName", nameOrNumberOrISBN.get()));
            if (query == null) {
                query = QueryBuilders.boolQuery();
            }
            query.must(search);
        }
        return query;
    }
}