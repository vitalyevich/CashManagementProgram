package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.firebase.model.WorkTime;
import com.cashmanagement.vitalyevich.client.graphql.GraphClient;
import com.cashmanagement.vitalyevich.client.model.*;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlTransportException;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private GraphClient graphClient;

    @Override
    public Iterable<User> getUsers() {
        String document = """
                     query {
                             users {
                             id
                             firstName
                             lastName
                             phone
                             email
                             roles {
                             id,
                             roleName
                             }
                             }
                         }
                """;

        try {
            Iterable<User> users = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("users")
                    .toEntity(User[].class).block()));
            return users;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!");
        }
        return null;
    }

    @Override
    public User getUser(Integer id) {
        String document = "query {\n" +
                "                              user(id: " + id + ") {\n" +
                "                                  id,\n" +
                "                                  firstName,\n" +
                "                                  lastName,\n" +
                "                                  phone,\n" +
                "                                  email\n" +
                "                                  roles {\n" +
                "                                  id,\n" +
                "                                  roleName\n" +
                "                                  }\n" +
                "                              }\n" +
                "                          }";

        try {
            User user = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("user")
                    .toEntity(User.class).block());
            return user;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public User saveUser(User user, Integer roleId, Integer companyId) {

        String document = "mutation {\n" +
                "                          createUser(user: {\n" +
                "                                  firstName: \"" + user.getFirstName() + "\"," +
                "                                  lastName: \"" + user.getLastName() + "\"," +
                "                                  phone: \"" + user.getPhone() + "\"," +
                "                                  email: \"" + user.getEmail() + "\"" +
                "                          }, role: {\n" +
                "                         id: " + roleId + "\n" +
                "                     }, company: {\n" +
                "                       id: " + companyId + "\n" +
                "}) {" +
                "                              id\n" +
                "                          }\n" +
                "                      }";

        try {
            User user1 = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("createUser")
                    .toEntity(User.class).block());
            return user1;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public User updateUser(User user, Integer roleId, Integer companyId) {
        String document = "mutation {\n" +
                "                          updateUser(user: {\n" +
                "                                  id:" + user.getId() + "," +
                "                                  firstName: \"" + user.getFirstName() + "\"," +
                "                                  lastName: \"" + user.getLastName() + "\"," +
                "                                  phone: \"" + user.getPhone() + "\"," +
                "                                  email: \"" + user.getEmail() + "\"" +
                "                          }, role: {\n" +
                "         id: " + roleId + "\n" +
                "     }, company: {\n" +
                "                       id: " + companyId + "\n" +
                "}) {" +
                "                              id\n" +
                "                          }\n" +
                "                      }";

        try {
            User user1 = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("updateUser")
                    .toEntity(User.class).block());
            return user1;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        String document = "mutation {\n" +
                "        deleteUser(id: " + id + ")\n" +
                "    }";

        try {
            graphClient.httpGraphQlClient().document(document)
                    .retrieve("deleteUser").toEntity(User.class).block();
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
    }

    @Override
    public Iterable<Role> getRoles() {
        String document = """
                     query {
                             roles {
                             id
                             roleName
                             }
                         }
                """;

        try {
            Iterable<Role> roles = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("roles")
                    .toEntity(Role[].class).block()));
            return roles;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public void saveWork(WorkTime workTime) {
        String document = "mutation {\n" +
                "                          createWork(work: {\n" +
                "                                  firstName: \"" + workTime.getFirstName() + "\"," +
                "                                  lastName: \"" + workTime.getLastName() + "\"," +
                "                                  description: \"" + workTime.getDescription() + "\"," +
                "                          }) " +
                "                      }";

        try {
            graphClient.httpGraphQlClient().document(document)
                    .retrieve("createWork")
                    .toEntity(WorkTime.class).block();
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
    }

    @Override
    public Iterable<Access> getAccesses() {
        String document = """
                     query {
                            accesses {
                            id,
                            user {
                                id,
                                firstName,
                                lastName,
                                phone,
                                email
                                roles {
                                    roleName
                                }
                            },
                            login,
                            userPassword,
                            active
                            }
                        }
                """;

        try {
            Iterable<Access> accesses = List.of(Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("accesses")
                    .toEntity(Access[].class).block()));
            return accesses;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Access getAccess(Integer id) {
        String document = "query {\n" +
                "    access(id:" + id + ") {\n" +
                "        id,\n" +
                "         login,\n" +
                "        userPassword,\n" +
                "        active\n" +
                "        user {\n" +
                "            id,\n" +
                "            firstName,\n" +
                "            lastName,\n" +
                "            phone,\n" +
                "            email,\n" +
                "                roles {\n" +
                "                id,\n" +
                "                roleName\n" +
                "                }\n" +
                "                companies {\n" +
                "                id,\n" +
                "                companyName\n" +
                "                }\n" +
                "            }\n" +
                "}\n" +
                "}";

        try {
            Access access = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("access")
                    .toEntity(Access.class).block());
            return access;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Access getAccessByLogin(String login) {
        String document = "query {\n" +
                "    accessByLogin(login: \"" + login + "\") {\n" +
                "    userPassword,    " +
                "user {\n" +
                "            id,\n" +
                "            firstName,\n" +
                "            lastName,\n" +
                "            roles {\n" +
                "                id,\n" +
                "                roleName\n" +
                "            }\n" +
                "            companies {\n" +
                "            id,\n" +
                "            companyName,\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        try {
            Access access = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("accessByLogin")
                    .toEntity(Access.class).block());
            return access;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Access updateAccess(Access access, Integer userId) {
        String document = "mutation {\n" +
                "                          updateAccess(access: {\n" +
                "                                  id:" + access.getId() + "," +
                "                                  login: \"" + access.getLogin() + "\"," +
                "                                  userPassword: \"" + access.getUserPassword() + "\"," +
                "                                  active: " + access.getActive() + "," +
                "                          }, user: {\n" +
                "         id: " + userId + "\n" +
                "     }){\n" +
                "                              id\n" +
                "                          }\n" +
                "                      }";

        try {
            Access access1 = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("updateAccess")
                    .toEntity(Access.class).block());
            return access1;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Access saveAccess(Access access, Integer userId) {
        String document = "mutation {\n" +
                "                          createAccess(access: {\n" +
                "                                  login: \"" + access.getLogin() + "\"," +
                "                                  userPassword: \"" + access.getUserPassword() + "\"," +
                "                                  active: " + access.getActive() + "," +
                "                          }, user: {\n" +
                "         id: " + userId + "\n" +
                "     }){\n" +
                "                              id\n" +
                "                          }\n" +
                "                      }";

        try {
            Access access1 = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("createAccess")
                    .toEntity(Access.class).block());
            return access1;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Iterable<Brigade> getBrigades() {
        String document = """
                     query {
                         brigades {
                         id,
                         brigadeName,
                         active,
                             users {
                             id,
                             firstName,
                             lastName
                             }
                         }
                     }
                """;

        try {
            Iterable<Brigade> brigades = List.of(graphClient.httpGraphQlClient().document(document)
                    .retrieve("brigades")
                    .toEntity(Brigade[].class).block());
            return brigades;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public Brigade saveBrigade(Brigade brigade, Integer companyId) {

        String document = "mutation {\n" +
                "    createBrigade(\n" +
                "        brigade: {\n" +
                "            brigadeName: \"" + brigade.getBrigadeName() + "\",\n" +
                "            active: " + brigade.getActive() + "\n" +
                "        },\n" +
                "        company: {\n" +
                "            id: " + companyId + "\n" +
                "        },\n" +
                "        users: [";

        for (User user : brigade.getUsers()) {
            document += "{\n" +
                    "    id: " + user.getId() + "\n" +
                    "},";
        }

        document += "]\n" +
                "    ) {\n" +
                "        brigadeName,\n" +
                "        id\n" +
                "    }\n" +
                "}";

        try {
            Brigade brigade1 = Objects.requireNonNull(graphClient.httpGraphQlClient().document(document)
                    .retrieve("createBrigade")
                    .toEntity(Brigade.class).block());
            return brigade1;
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
        return null;
    }

    @Override
    public void deleteBrigade(Integer id) {
        String document = "mutation {\n" +
                "        deleteBrigade(id: " + id + ")\n" +
                "    }";

        try {
            graphClient.httpGraphQlClient().document(document)
                    .retrieve("deleteBrigade").toEntity(Brigade.class).block();
        } catch (GraphQlTransportException ex) {
            System.out.println("Ошибка соединения!"); // test
        }
    }

    private Seance seance = Seance.getInstance();

    @Override
    public Access authorization(String login, String password) {

        // Создание объекта RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Установка заголовков запроса, включая Content-Type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonString  = "{\n" +
                "    \"userName\": \"" + login + "\",\n" +
                "    \"password\": \"" + password + "\"\n" +
                "}";

        // Создание объекта, содержащего JSON строку в теле запроса
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonString, headers);

        // Отправка POST запроса на указанную ссылку и получение ответа
        String url = "http://localhost:9191/login";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Обработка ответа
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            seance.setToken(responseBody);

            Access access = getAccessByLogin(login);

            return access;

        } else {
            System.out.println("Ошибка при выполнении запроса: " + responseEntity.getStatusCode());
        }

    return null;
    }
}
