package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.graphql.GraphClient;
import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.BrigadeOrder;
import com.cashmanagement.vitalyevich.client.model.Order;
import com.cashmanagement.vitalyevich.client.model.PlanAtm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlTransportException;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class PlanningServiceImpl implements PlanningService {

    @Autowired
    private GraphClient graphClient;

    @Override
    public Iterable<PlanAtm> getPlans() {
        String document = """
                      query {
                          plans {
                              id,
                              atm {
                              id,
                              atmUid,
                              cashState,
                              atmState,
                              address,
                              homeNum,
                                companies {
                                    id,
                                    companyName
                                }
                              },
                              planMethod,
                              status,
                              planPeriod,
                              cassettes {
                                  id,
                                  cassetteNum,
                                  banknote,
                                  currency,
                                  amount
                              },
                              user {
                              id,
                              firstName,
                              lastName
                              }
                          }
                      }
                """;

        try {
            Iterable<PlanAtm> planAtms = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("plans")
                    .toEntity(PlanAtm[].class).block()));
            return planAtms;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public PlanAtm getPlan(Integer id) {
        String document = "query {\n" +
                "    plan (id: "+id+") {\n" +
                "            id,\n" +
                "            atm {\n" +
                "                id,\n" +
                "                atmUid,\n" +
                "                cassettes {\n" +
                "                id,\n" +
                "                cassetteNum,\n" +
                "                banknote,\n" +
                "                currency,\n" +
                "                amount,\n" +
                "            }\n" +
                "            },\n" +
                "            planMethod,\n" +
                "            status,\n" +
                "            planPeriod,\n" +
                "            user {\n" +
                "                id,\n" +
                "            }\n" +
                "            cassettes {\n" +
                "                id,\n" +
                "                cassetteNum,\n" +
                "                banknote,\n" +
                "                currency,\n" +
                "                amount,\n" +
                "            }\n" +
                "        }\n" +
                "}";

        try {
            PlanAtm planAtm = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("plan")
                    .toEntity(PlanAtm.class).block());
            return planAtm;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public PlanAtm updatePlanAtm(PlanAtm planAtm) {
        String document = "mutation {\n" +
                "    updatePlan(plan: {\n" +
                "        id: "+planAtm.getId()+",\n" +
                "        planMethod: \""+planAtm.getPlanMethod()+"\",\n" +
                "        status: \""+planAtm.getStatus()+"\",\n" +
                "        planPeriod: "+planAtm.getPlanPeriod()+",\n" +
                "    },\n" +
                "    atm : {\n" +
                "        id: "+planAtm.getAtm().getId()+",\n" +
                "    },\n" +
                "    user: {\n" +
                "        id: "+planAtm.getUser().getId()+",\n" +
                "    }) {\n" +
                "        id,\n" +
                "        planMethod\n" +
                "    }\n" +
                "}";

        try {
            PlanAtm planAtm1 = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("updatePlan")
                    .toEntity(PlanAtm.class).block());
            return planAtm1;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }
}
