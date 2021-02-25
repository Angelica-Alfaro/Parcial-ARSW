package eci.arsw.covidanalyzer.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;

@Component("ICovidAggregateServiceStub")
public class ICovidAggregateServiceStub implements ICovidAggregateService {
	private List<Result> listCovidResult = new CopyOnWriteArrayList<>();
	@Override
	public void aggregateResult(Result result, ResultType type) {
        for (Result results : listCovidResult) {
            if (results.equals(result)) {
            	results.setResultType(type);
                results.setNumberTest(results.getNumberTest() + 1);
            }
        }
	}

	@Override
	public List<Result> getResult(ResultType type) {
	List<Result> resultsByType = new CopyOnWriteArrayList<>();
	    for (Result results : listCovidResult) {
	        if (results.getResultType().equals(type)) {
	            resultsByType.add(results);
	            results.setNumberTest(results.getNumberTest() + 1);
	        }
		}
	    return resultsByType;
	}

	@Override
	public void upsertPersonWithMultipleTests(UUID id, ResultType type) {
		for (Result results : listCovidResult) {
            if (results.getId().equals(id)) {
                results.setResultType(type);
                results.setNumberTest(results.getNumberTest() + 1);
            }
		}
	}
}
