package eci.arsw.covidanalyzer.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;

@Component("ICovidAggregateServiceStub")
public class ICovidAggregateServiceStub implements ICovidAggregateService {

	@Override
	public boolean aggregateResult(Result result, ResultType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getResult(ResultType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void upsertPersonWithMultipleTests(UUID id, ResultType type) {
		// TODO Auto-generated method stub
		
	}

}
