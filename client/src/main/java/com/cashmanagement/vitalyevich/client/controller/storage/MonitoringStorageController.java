package com.cashmanagement.vitalyevich.client.controller.storage;

import com.cashmanagement.vitalyevich.client.config.Seance;
import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.Storage;
import com.cashmanagement.vitalyevich.client.model.StorageOperation;
import com.cashmanagement.vitalyevich.client.service.StorageService;
import com.cashmanagement.vitalyevich.client.service.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@Controller
public class MonitoringStorageController {

    private Seance seance = Seance.getInstance();

    @Autowired
    private StorageServiceImpl storageService;

    private List<Storage> storageList = new ArrayList<>();

    @GetMapping("/monitoring-storage")
    public String monitoring(Model model) {

        Iterable<Storage> storages = storageService.getStorages();

        storageList = new ArrayList<>();

        if (storages != null) {
            String companyName = "";
            String currency = "";

            Integer balance = 0;

            for (Storage storage : storages) {

                storage.setAmountOperationPlus(0);
                storage.setAmountOperationMinus(0);
                storage.setBalanceMorning(0);

                if (storage.getCompanyName().equals(companyName)) {
                    storage.setCompanies(null);

                    if (storage.getCurrency().equals(currency)) {
                        for (StorageOperation storageOperation: storageService.getStorageOperations(storage.getId())) {
                            if (storageOperation.getAmountOperation() >= 0) {
                                storageList.get(storageList.size() - 1).setAmountOperationPlus((storageList.get(storageList.size() - 1).getAmountOperationPlus() + (storageOperation.getAmountOperation() * Integer.parseInt(storage.getBanknote()))));
                            } else {
                                storageList.get(storageList.size() - 1).setAmountOperationMinus((storageList.get(storageList.size() - 1).getAmountOperationMinus() + ((storageOperation.getAmountOperation() * -1) * Integer.parseInt(storage.getBanknote()))));
                            }
                        }
                        storageList.get(storageList.size() - 1).setSumAmount(storageList.get(storageList.size() - 1).getSumAmount() + ((int) (storage.getAmount() * Integer.parseInt(storage.getBanknote()))));
                        storageList.get(storageList.size() - 1).setBalanceMorning(storageList.get(storageList.size() - 1).getSumAmount() - storageList.get(storageList.size() - 1).getAmountOperationPlus() + storageList.get(storageList.size() - 1).getAmountOperationMinus());

                    } else {
                        for (StorageOperation storageOperation: storageService.getStorageOperations(storage.getId())) {
                            if (storageOperation.getAmountOperation() >= 0) {
                                storage.setAmountOperationPlus(storage.getAmountOperationPlus() + (storageOperation.getAmountOperation() * Integer.parseInt(storage.getBanknote())));
                            } else {
                                storage.setAmountOperationMinus(storage.getAmountOperationMinus() + ((storageOperation.getAmountOperation() * -1) * Integer.parseInt(storage.getBanknote())));
                            }
                        }
                        storage.setSumAmount((int) (storage.getAmount() * Integer.parseInt(storage.getBanknote())));
                        storage.setBalanceMorning(storage.getSumAmount() - storage.getAmountOperationPlus() + storage.getAmountOperationMinus());

                        storageList.add(storage);
                    }
                } else {
                    companyName = storage.getCompanyName();
                    currency = storage.getCurrency();

                    for (StorageOperation storageOperation: storageService.getStorageOperations(storage.getId())) {
                        if (storageOperation.getAmountOperation() >= 0) {
                            storage.setAmountOperationPlus(storage.getAmountOperationPlus() + (storageOperation.getAmountOperation() * Integer.parseInt(storage.getBanknote())));
                        } else {
                            storage.setAmountOperationMinus(storage.getAmountOperationMinus() + ((storageOperation.getAmountOperation() * -1) * Integer.parseInt(storage.getBanknote())));
                        }
                    }

                    storage.setSumAmount((int) (storage.getAmount() * Integer.parseInt(storage.getBanknote())));
                    storage.setBalanceMorning(storage.getSumAmount() - storage.getAmountOperationPlus() + storage.getAmountOperationMinus());

                    storageList.add(storage);
                }
            }
        }

        if (storageId != 0) {
            Storage storage = storageService.getStorage(storageId);
            model.addAttribute("textWindow", storage.getCompanies().iterator().next().getCompanyName());
        }

        if (this.id != null) {
            storageList.get(this.id).setMarked("marked");
            model.addAttribute("marked", "marked");
            model.addAttribute("id", this.id);
            model.addAttribute("disabled", false);
        } else {
            model.addAttribute("disabled", true);
        }

        model.addAttribute("storages", storageList);

        model.addAttribute("storage", storageArrayList);

        model.addAttribute("headerText", "Мониторинг");
        model.addAttribute("headerPost", "Старший кассир хранилища " + seance.getUser().getFirstName());
        return "monitoring-storage";
    }

    @GetMapping("/monitoring-storages")
    public String monitoringStorage(Model model) {

        Iterable<Storage> storages = storageService.getStorages();

        storageList = new ArrayList<>();

        if (storages != null) {
            String companyName = "";
            String currency = "";

            Integer balance = 0;

            for (Storage storage : storages) {

                storage.setAmountOperationPlus(0);
                storage.setAmountOperationMinus(0);
                storage.setBalanceMorning(0);

                if (storage.getCompanyName().equals(companyName)) {
                    storage.setCompanies(null);

                    if (storage.getCurrency().equals(currency)) {
                        for (StorageOperation storageOperation: storageService.getStorageOperations(storage.getId())) {
                            if (storageOperation.getAmountOperation() >= 0) {
                                storageList.get(storageList.size() - 1).setAmountOperationPlus((storageList.get(storageList.size() - 1).getAmountOperationPlus() + (storageOperation.getAmountOperation() * Integer.parseInt(storage.getBanknote()))));
                            } else {
                                storageList.get(storageList.size() - 1).setAmountOperationMinus((storageList.get(storageList.size() - 1).getAmountOperationMinus() + ((storageOperation.getAmountOperation() * -1) * Integer.parseInt(storage.getBanknote()))));
                            }
                        }
                        storageList.get(storageList.size() - 1).setSumAmount(storageList.get(storageList.size() - 1).getSumAmount() + ((int) (storage.getAmount() * Integer.parseInt(storage.getBanknote()))));
                        storageList.get(storageList.size() - 1).setBalanceMorning(storageList.get(storageList.size() - 1).getSumAmount() - storageList.get(storageList.size() - 1).getAmountOperationPlus() + storageList.get(storageList.size() - 1).getAmountOperationMinus());

                    } else {
                        for (StorageOperation storageOperation: storageService.getStorageOperations(storage.getId())) {
                            if (storageOperation.getAmountOperation() >= 0) {
                                storage.setAmountOperationPlus(storage.getAmountOperationPlus() + (storageOperation.getAmountOperation() * Integer.parseInt(storage.getBanknote())));
                            } else {
                                storage.setAmountOperationMinus(storage.getAmountOperationMinus() + ((storageOperation.getAmountOperation() * -1) * Integer.parseInt(storage.getBanknote())));
                            }
                        }
                        storage.setSumAmount((int) (storage.getAmount() * Integer.parseInt(storage.getBanknote())));
                        storage.setBalanceMorning(storage.getSumAmount() - storage.getAmountOperationPlus() + storage.getAmountOperationMinus());

                        storageList.add(storage);
                    }
                } else {
                    companyName = storage.getCompanyName();
                    currency = storage.getCurrency();

                    for (StorageOperation storageOperation: storageService.getStorageOperations(storage.getId())) {
                        if (storageOperation.getAmountOperation() >= 0) {
                            storage.setAmountOperationPlus(storage.getAmountOperationPlus() + (storageOperation.getAmountOperation() * Integer.parseInt(storage.getBanknote())));
                        } else {
                            storage.setAmountOperationMinus(storage.getAmountOperationMinus() + ((storageOperation.getAmountOperation() * -1) * Integer.parseInt(storage.getBanknote())));
                        }
                    }

                    storage.setSumAmount((int) (storage.getAmount() * Integer.parseInt(storage.getBanknote())));
                    storage.setBalanceMorning(storage.getSumAmount() - storage.getAmountOperationPlus() + storage.getAmountOperationMinus());

                    storageList.add(storage);
                }
            }
        }

        model.addAttribute("storages", storageList);
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));

        if (this.id != null) {
            storageList.get(this.id).setMarked("marked");
            model.addAttribute("text", text);
        } else {
            model.addAttribute("disabled", true);
        }

        model.addAttribute("balanceNow", balanceNow);
        model.addAttribute("balanceAdd", balanceAdd);
        model.addAttribute("balanceMin", balanceMin);
        model.addAttribute("balanceEnd", balanceEnd);

        model.addAttribute("storage", storageArrayList);
        model.addAttribute("storage1", storageArrayList1);
        model.addAttribute("storage2", storageArrayList2);
        model.addAttribute("storage3", storageArrayList3);



        model.addAttribute("headerText", "Мониторинг");
        model.addAttribute("headerPost", "Старший кассир хранилища " + seance.getUser().getFirstName());
        return "monitoring-storages";
    }

    private String text = "";

    private Integer storageId = 0;

    @PostMapping("/monitoring-storage/balance-storage")
    public String balanceStorage(@RequestParam Integer rowId, RedirectAttributes rm) {
        storageId = rowId;

        Storage serviceStorage = storageList.get(storageId);
        serviceStorage = storageService.getStorage(serviceStorage.getId());

        rm.addFlashAttribute("textWindow", serviceStorage.getCompanies().iterator().next().getCompanyName());
        return "redirect:/monitoring-storage#blackout-balance";
    }

    private List<Storage> storageArrayList = new LinkedList<>();
    private List<Storage> storageArrayList1 = new LinkedList<>();
    private List<Storage> storageArrayList2 = new LinkedList<>();
    private List<Storage> storageArrayList3 = new LinkedList<>();

    private Integer id = null;

    @GetMapping("/monitoring-storage/{id}")
    public String banknote(Model model, @PathVariable Integer id) {
        storageArrayList.clear();

        this.id = id;

        Iterable<Storage> storages = storageService.getStorages();

        Storage serviceStorage = storageList.get(id);
        serviceStorage = storageService.getStorage(serviceStorage.getId());

        int sumAmount = 0;

        for (Storage storage: storages) {
            if (storage.getCompanyName().equals(serviceStorage.getCompanyName())) {
                if (serviceStorage.getCurrency().equals(storage.getCurrency())) {
                    storageArrayList.add(storage);
                    sumAmount = sumAmount + (storage.getAmount() * Integer.parseInt(storage.getBanknote()));
                }
            }
        }

        storageArrayList.get(storageArrayList.size() - 1).setSumAmount(sumAmount);

        return "redirect:/monitoring-storage";
    }

    private Integer balanceNow = 0;
    private Integer balanceAdd = 0;
    private Integer balanceMin = 0;
    private Integer balanceEnd = 0;

    @GetMapping("/monitoring-storages/{id}")
    public String banknotes(Model model, @PathVariable Integer id) {
        storageArrayList.clear();
        storageArrayList1.clear();
        storageArrayList2.clear();
        storageArrayList3.clear();

        balanceNow = 0;
        balanceAdd = 0;
        balanceMin = 0;
        balanceEnd = 0;

        this.id = id;

        Iterable<Storage> storages = storageService.getStorages();

        Storage serviceStorage = storageList.get(id);
        serviceStorage = storageService.getStorage(serviceStorage.getId());

        text = serviceStorage.getCompanyName() + ": " + serviceStorage.getCurrency();

        for (Storage storage: storages) {

            if (storage.getCompanyName().equals(serviceStorage.getCompanyName())) {
                if (serviceStorage.getCurrency().equals(storage.getCurrency())) {

                    storageArrayList.add(storage);
                    balanceNow = balanceNow + (storage.getAmount() * Integer.parseInt(storage.getBanknote()));
                }
            }
        }

        storages = storageService.getStorages();

        serviceStorage = storageList.get(id);
        serviceStorage = storageService.getStorage(serviceStorage.getId());

        for (Storage storage: storages) {

            if (storage.getCompanyName().equals(serviceStorage.getCompanyName())) {
                if (serviceStorage.getCurrency().equals(storage.getCurrency())) {

                    storage.setAmount(0);
                    for (StorageOperation storageOperation: storageService.getStorageOperations(storage.getId())) {
                        if (storageOperation.getAmountOperation() >= 0) {

                            balanceAdd = balanceAdd + (storageOperation.getAmountOperation() * Integer.parseInt(storage.getBanknote()));
                            storage.setAmount(storage.getAmount() + storageOperation.getAmountOperation());
                        }
                    }

                    storageArrayList1.add(storage);
                }
            }
        }

        storages = storageService.getStorages();

        serviceStorage = storageList.get(id);
        serviceStorage = storageService.getStorage(serviceStorage.getId());

        for (Storage storage: storages) {

            if (storage.getCompanyName().equals(serviceStorage.getCompanyName())) {
                if (serviceStorage.getCurrency().equals(storage.getCurrency())) {

                    storage.setAmount(0);
                    for (StorageOperation storageOperation: storageService.getStorageOperations(storage.getId())) {
                        if (storageOperation.getAmountOperation() < 0) {
                            balanceMin = balanceMin + ((storageOperation.getAmountOperation() * - 1) * Integer.parseInt(storage.getBanknote()));
                            storage.setAmount(storage.getAmount() + (storageOperation.getAmountOperation() * - 1));
                        }
                    }

                    storageArrayList2.add(storage);
                }
            }
        }

        storages = storageService.getStorages();

        serviceStorage = storageList.get(id);
        serviceStorage = storageService.getStorage(serviceStorage.getId());

        int check = 0;
        for (Storage storage: storages) {

            if (storage.getCompanyName().equals(serviceStorage.getCompanyName())) {
                if (serviceStorage.getCurrency().equals(storage.getCurrency())) {

                    storage.setAmount(storage.getAmount() - storageArrayList1.get(check).getAmount() + storageArrayList2.get(check).getAmount());

                    storageArrayList3.add(storage);
                    balanceEnd = balanceEnd + (storage.getAmount() * Integer.parseInt(storage.getBanknote()));
                    check++;
                }
            }
        }


        return "redirect:/monitoring-storages";
    }
}


