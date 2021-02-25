package eci.arsw.covidanalyzer;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.service.ICovidAggregateService;


@RestController
public class CovidAggregateController {
    @Autowired
    @Qualifier("ICovidAggregateServiceStub")
    ICovidAggregateService covidAggregateService;

    //Métodos POST

    @RequestMapping(value = "/covid/result/true-positive", method = RequestMethod.POST)
    public ResponseEntity<?> addTruePositiveResult(@RequestBody Result result) {
        covidAggregateService.aggregateResult(result, ResultType.TRUE_POSITIVE);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/covid/result/true-negative", method = RequestMethod.POST)
    public ResponseEntity<?> addTrueNegativeResult(@RequestBody Result result) {
        covidAggregateService.aggregateResult(result, ResultType.TRUE_NEGATIVE);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/covid/result/false-positive", method = RequestMethod.POST)
    public ResponseEntity<?> addFalsePositiveResult(@RequestBody Result result) {
        covidAggregateService.aggregateResult(result, ResultType.FALSE_POSITIVE);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/covid/result/false-negative", method = RequestMethod.POST)
    public ResponseEntity<?> addFalseNegativeResult(@RequestBody Result result) {
        covidAggregateService.aggregateResult(result, ResultType.FALSE_NEGATIVE);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    ////Métodos GET

    @RequestMapping(value = "/covid/result/true-positive", method = RequestMethod.GET)
    public ResponseEntity<?> getTruePositiveResult() {
        covidAggregateService.getResult(ResultType.TRUE_POSITIVE);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/covid/result/true-negative", method = RequestMethod.GET)
    public ResponseEntity<?> getTrueNegativeResult() {
        covidAggregateService.getResult(ResultType.TRUE_NEGATIVE);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/covid/result/false-positive", method = RequestMethod.GET)
    public ResponseEntity<?> getFalsePositiveResult() {
        covidAggregateService.getResult(ResultType.FALSE_POSITIVE);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/covid/result/false-negative", method = RequestMethod.GET)
    public ResponseEntity<?> getFalseNegativeResult() {
    	covidAggregateService.getResult(ResultType.FALSE_NEGATIVE);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    //Métodos PUT

    @RequestMapping(value = "/covid/result/persona/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> savePersonaWithMultipleTests(@RequestBody UUID id, @RequestBody ResultType resultType) {
        covidAggregateService.upsertPersonWithMultipleTests(id, resultType);
        return  new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}