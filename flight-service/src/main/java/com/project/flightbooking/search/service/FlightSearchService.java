@Service
public class FlightSearchService {

    // ES Flight Repository
    @Autowired
    private EsFlightRepository EsFlightRepository;

    /**
     * 
     * Search function 
     */
    @Override
    public List<Flight> search(final MultiValueMap<String, String> queryParams) throws InvalidSearchParamException {
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        for (Map.Entry<String, List<String>> param : queryParams.entrySet()) {
            boolQueryBuilder.must(SearchParameters.build(param));
        }

        return Lists.newArrayList(EsFlightRepository.search(boolQueryBuilder));
    }
}