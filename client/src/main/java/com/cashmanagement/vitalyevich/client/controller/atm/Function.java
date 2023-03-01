package com.cashmanagement.vitalyevich.client.controller.atm;

import com.cashmanagement.vitalyevich.client.forecast.insights.timeseries.arima.Arima;
import com.cashmanagement.vitalyevich.client.forecast.insights.timeseries.arima.struct.ArimaParams;
import com.cashmanagement.vitalyevich.client.forecast.insights.timeseries.arima.struct.ForecastResult;
import com.cashmanagement.vitalyevich.client.model.Cassette;
import com.cashmanagement.vitalyevich.client.model.PlanAtm;
import com.cashmanagement.vitalyevich.client.model.WithdrawalCash;
import com.cashmanagement.vitalyevich.client.service.PlanningServiceImpl;
import com.cashmanagement.vitalyevich.client.service.WithdrawalCashService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class Function {

    @Autowired
    private WithdrawalCashService withdrawalCashService;

    @Autowired
    private PlanningServiceImpl planningService;

    public List<Cassette> staticForecast (String date, Integer period, Integer planId) {

        double[] dataArray = new double[] {2, 1, 2, 5, 2, 1, 2, 5, 2, 1, 2, 5, 2, 1, 2, 5};
        //List<Integer> dataArray = new LinkedList<>();

        List<WithdrawalCash> withdrawalCashes = (List<WithdrawalCash>) withdrawalCashService.getCashes();
        PlanAtm planAtm = planningService.getPlan(planId);

        int i =0;
        for (WithdrawalCash withdrawalCash: withdrawalCashes) {
            if (withdrawalCash.getAtm().getId().equals(planAtm.getAtm().getId())) {
                dataArray[i] = (double) withdrawalCash.getAmount();
                i++;
            }
        }

// Set ARIMA model parameters.
        int p = 3;
        int d = 0;
        int q = 3;
        int P = 1;
        int D = 1;
        int Q = 0;
        int m = 0;
        int forecastSize = 1;

        ArimaParams params = new ArimaParams(3,0,3,1,1,0,0);
        ForecastResult forecastResult = Arima.forecast_arima(dataArray, forecastSize, params);

        double[] forecastData = forecastResult.getForecast(); // in this example, it will return { 2 }
        double[] uppers = forecastResult.getForecastUpperConf();
        double[] lowers = forecastResult.getForecastLowerConf();

        double rmse = forecastResult.getRMSE();

        double maxNormalizedVariance = forecastResult.getMaxNormalizedVariance();

        String log = forecastResult.getLog();
        return null;
    }

    public List<Cassette> userForecast (String date, Integer period, Integer planId) {

        List<WithdrawalCash> withdrawalCashes = (List<WithdrawalCash>) withdrawalCashService.getCashes();
        PlanAtm planAtm = planningService.getPlan(planId);

        List<WithdrawalCash> withdrawalCashes1 = new LinkedList<>();
        for (WithdrawalCash withdrawalCash: withdrawalCashes) {
            if (withdrawalCash.getAtm().getId().equals(planAtm.getAtm().getId())) {
                withdrawalCashes1.add(withdrawalCash);
            }
        }
        return (List<Cassette>) planAtm.getCassettes();
    }
}
